package com.dc.springTest.dependencysource.reource;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * Description: 动态 更新资源
 * 1. 定位资源
 * 2. 装载资源
 * 3. 转换成Properties 资源
 * 4. 利用nio  WatchService 监听文件资源
 * 5. 开启线程进行监听文件
 * 6. 重新装载Properties 资源
 *
 * Author: duancong
 * Date: 2023/10/22 16:54
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

	private static final String filename = "msg.properties";

	private static final String path = "META-INF/" + filename;

	private static final String encode = "UTF-8";

	private ResourceLoader resourceLoader;

	private Properties properties;

	private Resource resource;

	private ExecutorService executorService;

	public DynamicResourceMessageSource() {
		this.resource = getResource(path);
		Reader reader = getReader();
		this.properties = getProperties(reader);
		this.executorService = Executors.newSingleThreadExecutor();
		// 监听文件变化, java nio 2 WatchService
		OnPropertiesChanges();
	}


	private Reader getReader() {
		EncodedResource encodedResource = new EncodedResource(resource, encode);
		try {
			return encodedResource.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void OnPropertiesChanges() {
		if (resource.isFile()) {
			try {
				File file = resource.getFile();
				//获取监听目录
				Path path = file.toPath();
				//返回上级目录
				final Path parent = path.getParent();
				FileSystem defaultfis = FileSystems.getDefault();
				//新建一个WatchService
				final WatchService watchService = defaultfis.newWatchService();
				//注册到WatchService
				parent.register(watchService, ENTRY_MODIFY);
				doPropertiesChanges(watchService);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doPropertiesChanges(WatchService watchService) {
		//开启线程处理事件.
		executorService.submit(() -> {
			while (true) {
				WatchKey take = null;
				try {
					take = watchService.take();
					if (take.isValid()) {
						for (WatchEvent<?> pollEvent : take.pollEvents()) {
							//监听路径
							Path watchable = (Path) take.watchable();
							System.out.println(watchable);
							// 事件发生 的文件路径 相对路径
							final Path context = (Path) pollEvent.context();
							if (filename.equals(context.getFileName().toString())) {
								// 处理为绝对路径
								Path resolve = watchable.resolve(context);
								System.out.println("修改后的文件 : " + resolve);
								File file = resolve.toFile();
								FileReader fileReader = new FileReader(file);
								Properties properties = getProperties(fileReader);
								synchronized (this.properties) {
									this.properties.clear();
									this.properties.putAll(properties);
								}
							}
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					if (take!=null){
						take.reset(); //重置watchkey
					}
				}
			}
		});
	}

	private Resource getResource(String path) {
		ResourceLoader resourceLoader = getResourceLoader();
		return resourceLoader.getResource(path);
	}

	public ResourceLoader getResourceLoader() {
		return this.resourceLoader != null ? resourceLoader : new DefaultResourceLoader();
	}

	public Properties getProperties(Reader reader) {
		Properties properties = new Properties();
		try {
			properties.load(reader);
		} catch (IOException e) {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return properties;
	}


	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		synchronized (this.properties) {
			String o = properties.getProperty(code);
			if (StringUtils.hasText(o)) {
				return new MessageFormat(o, locale);
			}
		}
		return new MessageFormat("",locale);
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public static void main(String[] args) throws InterruptedException {
		final DynamicResourceMessageSource dynamicResourceMessageSource = new DynamicResourceMessageSource();
		for (int i = 0; i < 100; i++) {
			System.out.println(dynamicResourceMessageSource.getMessage("name", new Object[]{}, Locale.CHINA));

			Thread.sleep(1000l);
		}
	}
}

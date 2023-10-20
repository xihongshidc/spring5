package com.dc.springTest.dependencysource.reource;

import org.springframework.core.io.ContextResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Description: {@link FileSystemResourceLoader}  文件系统资源加载器   测试
 * Author: duancong
 * Date: 2023/10/20 16:29
 */
public class FileSystemResourceLoaderDemo {
	public static void main(String[] args) throws IOException {

		String x ="/"+ System.getProperty("user.dir") + "\\spring-mytest\\src\\main\\java\\com\\dc\\springTest\\dependencysource\\reource\\FileSystemResourceLoaderDemo.java";
		System.out.println(x);
		FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();

		ContextResource resource = (ContextResource) fileSystemResourceLoader.getResource(x);
		System.out.println(resource.getPathWithinContext());
		System.out.println(resource.getFile().getPath());
//		FileSystemResource fileSystemResource = new FileSystemResource(x);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-16");
		try(InputStream inputStream = encodedResource.getInputStream()){
			System.out.println(StreamUtils.copyToString(inputStream, Charset.defaultCharset()));
		};
	}
}

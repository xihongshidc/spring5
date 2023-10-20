package com.dc.springTest.dependencysource.reource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Description: {@link FileSystemResource}文件系统资源  测试
 * Author: duancong
 * Date: 2023/10/20 16:29
 */
public class FileSystemResourceDemo {
	public static void main(String[] args) throws IOException {
		String x = System.getProperty("user.dir") + "\\spring-mytest\\src\\main\\java\\com\\dc\\springTest\\dependencysource\\reource\\FileSystemResourceDemo.java";
		System.out.println(x);
		FileSystemResource fileSystemResource = new FileSystemResource(x);
		EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
		try(InputStream inputStream = encodedResource.getInputStream()){
			System.out.println(StreamUtils.copyToString(inputStream, Charset.defaultCharset()));
		};



	}
}

package com.dc.springTest.demo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Description: 自定义类加载， 重写findclass 方法，
 * Author: duancong
 * Date: 2024/1/16 17:45
 */
public class MyClassLoader extends ClassLoader {

	private String fileUrl;

	public MyClassLoader(ClassLoader parent, String fileUrl) {
		super(parent);
		this.fileUrl = fileUrl;
	}

	public MyClassLoader(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		FileInputStream fileInputStream =null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			fileInputStream = new FileInputStream(fileUrl+"\\" + name + ".class");

			byte[] bytes = new byte[1024];
			int read;
			byteArrayOutputStream = new ByteArrayOutputStream();

			while ((read = fileInputStream.read(bytes))!= -1){
				byteArrayOutputStream.write(bytes, 0, read);
			}
			byte[] bytes1 = byteArrayOutputStream.toByteArray();
			Class<?> aClass = defineClass(null, bytes1, 0, bytes1.length);
			return aClass;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				byteArrayOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}

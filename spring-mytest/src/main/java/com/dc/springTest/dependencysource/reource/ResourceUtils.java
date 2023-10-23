package com.dc.springTest.dependencysource.reource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public interface ResourceUtils {

	static String toString(Resource resource) {
		try (InputStream inputStream = resource.getInputStream()) {
			return StreamUtils.copyToString(inputStream, Charset.defaultCharset());
		} catch (IOException e) {
			return null;
		}

	}
}

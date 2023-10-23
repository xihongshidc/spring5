package com.dc.springTest.dependencysource.reource;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Map;

/**
 * Description:{@link PathMatchingResourcePatternResolver }
 * Author: duancong
 * Date: 2023/10/21 10:53
 */
public class CutsomizedResourcePathPatternDemo {

	public static void main(String[] args) throws IOException {
		String path1= "spring-mytest\\src\\main\\java\\com\\dc\\springTest\\dependencysource\\reource\\"+"*.java";
//		String x = System.getProperty("user.dir") + "\\spring-mytest\\src\\main\\java\\com\\dc\\springTest\\dependencysource\\reource\\CutsomizedResourcePathPatternDemo.java";

		String path = System.getProperty("user.dir")+ "\\spring-mytest\\src\\main\\java\\com\\dc\\springTest\\dependencysource\\reource\\";
		String matchpath=path+"*.java";
//		String match ="classpath*:*.java";
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

//		pathMatchingResourcePatternResolver.setPathMatcher(new JavaFilePathMatch());
		Resource[] resources = pathMatchingResourcePatternResolver.getResources(path1);
		for (Resource resource : resources) {
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
			System.out.println(StreamUtils.copyToString(encodedResource.getInputStream(), Charset.defaultCharset()));
		}

	}
	static class JavaFilePathMatch implements PathMatcher{

		@Override
		public boolean isPattern(String path) {
			return path.endsWith(".java");
		}

		@Override
		public boolean match(String pattern, String path) {
			return path.endsWith(".java");
		}

		@Override
		public boolean matchStart(String pattern, String path) {
			return false;
		}

		@Override
		public String extractPathWithinPattern(String pattern, String path) {
			return null;
		}

		@Override
		public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
			return null;
		}

		@Override
		public Comparator<String> getPatternComparator(String path) {
			return null;
		}

		@Override
		public String combine(String pattern1, String pattern2) {
			return null;
		}
	}

}

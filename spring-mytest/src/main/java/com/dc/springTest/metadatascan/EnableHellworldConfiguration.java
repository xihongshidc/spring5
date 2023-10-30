package com.dc.springTest.metadatascan;

import org.springframework.context.annotation.Bean;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/29 13:38
 */
//@Configuration
public class EnableHellworldConfiguration {

	@Bean
	public String helloworld(){
		return "Hello,world";
	}
}

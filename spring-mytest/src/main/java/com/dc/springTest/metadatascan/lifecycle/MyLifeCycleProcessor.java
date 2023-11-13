package com.dc.springTest.metadatascan.lifecycle;

import org.springframework.context.Lifecycle;

/**
 * Description:
 * Author: duancong
 * Date: 2023/11/3 11:44
 */
public class MyLifeCycleProcessor implements Lifecycle {
	private Boolean start =false;

	@Override
	public void start() {
		this.start = true;
		System.out.println("启动 ...");
	}

	@Override
	public void stop() {
		this.start = false;
		System.out.println("停止 ...");

	}

	@Override
	public boolean isRunning() {
		return start;
	}
}

package com.han.lanshan.task.utils;

import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask {
	public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
		System.out.println("多参构造");
	}

	public void ryParams(String params) {
		System.out.println("执行有参方法：" + params);
	}

	public void ryNoParams() {
		System.out.println("执行无参方法");
	}
}

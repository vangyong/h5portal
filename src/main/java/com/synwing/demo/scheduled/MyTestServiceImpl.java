package com.synwing.demo.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTestServiceImpl implements IMyTestService {
	@Scheduled(cron = "0 2 12  * * ? ")
	@Override
	public void myTest() {
		System.out.println("进入测试");
	}
}

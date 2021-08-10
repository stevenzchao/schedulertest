package com.example.schedulertest.scheduler;

import java.util.Date;

import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {
	
	
	/**
	 * 
	 * @return
	 */
	
	//顯示現在時間的方法
	@SuppressWarnings("deprecation")
	public String CurrentTime() {
		Date nowDate = new Date();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" "+ nowDate.getHours() + "點");
		stringBuilder.append(" "+nowDate.getMinutes()+ "分");
		stringBuilder.append(" "+nowDate.getSeconds()+ "秒");
		return stringBuilder.toString();
	}

	// 以毫秒為單位 1秒=1000毫秒(milisecond)
	@Scheduled(fixedDelay = 2000, initialDelay = 3000)
	public void testScheduler() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("我在" + CurrentTime() + " 說 Hello!!!!!");
		System.out.println(stringBuilder.toString());
	}

}

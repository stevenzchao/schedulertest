package com.example.schedulertest.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {

	/**
	 * 1.annotation
	 * @EnableScheduling 要在啟動類別裡面加入,
	 * @Component 要在 Scheduler 類別裡面加入
	 * @Scheduled 要在想要設定循環的方法內加入
	 * 
	 * 2.規則與使用參數
	 * 以毫秒為單位 1秒=1000毫秒(milisecond)
	 * cron: cron表示式，指定任務在特定時間執行； fixedDelay:
	 * 表示上一次任務執行完成後多久再次執行，引數型別為long，單位ms； fixedDelayString:
	 * 與fixedDelay含義一樣，只是引數型別變為String； fixedRate:
	 * 表示按一定的頻率執行任務，引數型別為long，單位ms； fixedRateString:
	 * 與fixedRate的含義一樣，只是將引數型別變為String； initialDelay:
	 * 表示延遲多久再第一次執行任務，引數型別為long，單位ms； initialDelayString:
	 * 與initialDelay的含義一樣，只是將引數型別變為String； zone: 時區，預設為當前時區，一般沒有用到
	 * 
	 * 3.cron 表示式 用法
	 *          
	 *      ┌───────────── second (0-59)
	 *	    │ ┌───────────── minute (0 - 59)
	 *		│ │ ┌───────────── hour (0 - 23)
	 *		│ │ │ ┌───────────── day of the month (1 - 31)
	 *		│ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
	 *		│ │ │ │ │ ┌───────────── day of the week (0 - 7)
	 *		│ │ │ │ │ │          (0 or 7 , or MON-SUN 有效範圍為1-7的整數或SUN-SAT兩個範圍。1表示星期天，2表示星期一)
	 *		│ │ │ │ │ │
	 *		* * * * * *
	 *  秒：可出現: ”, – * /” 左列的四個字元，有效範圍為0-59的整數
	 *  分：可出現: ”, – * /” 左列的四個字元，有效範圍為0-59的整數
	 *  時：可出現: ”, – * /” 左列的四個字元，有效範圍為0-23的整數
	 *  每月第幾天：可出現: ”, – * / ? L W C” 左列的八個字元，有效範圍為0-31的整數
	 *  月：可出現: ”, – * /” 左列的四個字元，有效範圍為1-12的整數或JAN-DEc
	 *  星期：可出現: ”, – * / ? L C #” 左列的八個字元，有效範圍為1-7的整數或SUN-SAT兩個範圍。1表示星期天，2表示星期一， 依次類推          
	 * 
	 * * : 表示匹配該域的任意值，比如在秒*, 就表示每秒都會觸發事件。；
	 * ? : 只能用在每月第幾天和星期兩個域。表示不指定值，當2個子表示式其中之一被指定了值以後，為了避免衝突，需要將另一個子表示式的值設為“?”；
	 * – : 表示範圍，例如在分域使用5-20，表示從5分到20分鐘每分鐘觸發一次
	 * / : 表示起始時間開始觸發，然後每隔固定時間觸發一次，例如在分域使用5/20,則意味著5分，25分，45分，分別觸發一次.
	 * , : 表示列出列舉值。例如：在分域使用5,20，則意味著在5和20分時觸發一次。
	 * L : 表示最後，只能出現在星期和每月第幾天域，如果在星期域使用1L,意味著在最後的一個星期日觸發。
	 * W : 表示有效工作日(週一到週五),只能出現在每月第幾日域，系統將在離指定日期的最近的有效工作日觸發事件。注意一點，W的最近尋找不會跨過月份 
	 * LW : 這兩個字元可以連用，表示在某個月最後一個工作日，即最後一個星期五。
	 * # : 用於確定每個月第幾個星期幾，只能出現在每月第幾天域。例如在1#3，表示某月的第三個星期日。
	 * 
	 * 
	 * 
	 * 4. Marco
	 * 	@yearly (or @annually)   once a year (0 0 0 1 1 *)
	 *	@monthly                once a month (0 0 0 1 * *)
	 *	@weekly	                once a week (0 0 0 * * 0)
	 *	@daily (or @midnight)   once a day (0 0 0 * * *), 
	 *	@hourly                 once an hour, (0 0 * * * *)
	 * 
	 * 5.小記：所有的定時任務都是通過一個執行緒來處理的，可另外實現SchedulingConfigurer介面，重寫configureTasks方法就可以達到多執行序~~
	 * 
	 * 參考：https://polinwei.com/spring-boot-scheduling-tasks/
	 * 
	 */

	// 設定時間format
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	/**
	 * 延遲2秒，一開始暫停3秒才執行 fixedDelay = 2000 表示當前方法執行完畢2秒後，Spring scheduling會再次呼叫該方法
	 */
	@Scheduled(fixedDelay = 2000, initialDelay = 3000)
	public void testFixedDelayAndInitialDelay() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("1::我在 testFixedDelayAndInitialDelay " + dateFormat.format(new Date()) + " 說 Hello!!!!!");
		System.out.println(stringBuilder.toString());
	}

	/**
	 * 延遲2秒，一開始暫停3秒才執行 fixedRateString = "2000" 表示當前方法執行完畢2秒後，Spring
	 * scheduling會再次呼叫該方法
	 */
	@Scheduled(fixedRateString = "2000", initialDelayString = "3000")
	public void testFixedRateStringAndInitialDelayString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("2::我在 testFixedRateStringAndInitialDelayString " + dateFormat.format(new Date()) + " 說 Hello!!!!!");
		System.out.println(stringBuilder.toString());
	}

	// fixedRate = 2000 表示當前方法開始執行 2秒 後，Spring scheduling會再次呼叫該方法
	 @Scheduled(fixedRate = 2000) 
	 public void testFixedRate() throws InterruptedException {
			System.out.println("3::testFixedRate==開始" + dateFormat.format(new Date()));
			Thread.sleep(3000);
			System.out.println("testFixedRate==結束" + dateFormat.format(new Date()));			
	 }

	// fixedRate = 2000 表示上一次任務執行完成 2秒 後，Spring scheduling會再次呼叫該方法
//	 @Scheduled(fixedDelayString = "2000") 
//	 public void testFixedDelay() throws InterruptedException {
//			System.out.println("testFixedDelay==開始 " + dateFormat.format(new Date()));	
//			Thread.sleep(3000);
//			System.out.println("testFixedDelay==結束" + dateFormat.format(new Date()));	
//
//	 }

	// 接受cron表示式，根據cron表示式確定定時規則
//	@Scheduled(cron ="${my.cron.expression}" ) 可使用properties設定cron值，綁定特定用途的mail的發送時間
	@Scheduled(cron = "5/5 * * * * ?")
	public void testCron() {
		System.out.println("4::testCron==開始" + dateFormat.format(new Date()));
	}

}

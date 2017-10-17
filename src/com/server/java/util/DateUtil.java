package com.server.java.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;

import com.server.java.log.LoggerProvider;

public class DateUtil {
	private static Logger logger = LoggerProvider.getLogger(DateUtil.class);

	/**
	 * 验证日期是否是在今天
	 * */
	public static boolean isInToday(Date time) {
		String timeStr = parseDate(time);
		String nowStr = parseDate(new Date());
		return timeStr.equals(nowStr);
	}

	/**
	 * 转换字符串为日期格式
	 * 
	 * @param time
	 *            格式yyyy-MM-dd HH:mm:ss
	 * */
	public static Date parseTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(time);
			return date;
		} catch (Exception e) {
			logger.error("转换时间失败", e);
		}
		return null;
	}

	/**
	 * 转换日期为字符串格式
	 * 
	 * @return 格式yyyy-MM-dd HH:mm:ss
	 * */
	public static String parseTime(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.format(time);
		} catch (Exception e) {
			logger.error("转换时间失败", e);
		}
		return null;
	}

	/**
	 * 转换字符串为日期格式
	 * 
	 * @param time
	 *            格式yyyy-MM-dd
	 * */
	public static Date parseDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(time);
			return date;
		} catch (Exception e) {
			logger.error("转换日期失败", e);
		}
		return null;
	}

	/**
	 * 转换日期为字符串格式
	 * 
	 * @return 格式yyyy-MM-dd
	 * */
	public static String parseDate(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.format(time);
		} catch (Exception e) {
			logger.error("转换日期失败", e);
		}
		return null;
	}

	/**
	 * 查看相差的天数
	 * */
	public static int betweenDate(long currTime, long oldTime) {
//		int intervalDay = 0;
//		if (currTime < oldTime) {
//			return 0;
//		}
//		Calendar currCal = Calendar.getInstance();
//		currCal.setTimeInMillis(currTime);
//		int currIntervalDay = currCal.get(Calendar.DAY_OF_YEAR);
//
//		Calendar oldCal = Calendar.getInstance();
//		oldCal.setTimeInMillis(oldTime);
//		int oldIntervalDay = oldCal.get(Calendar.DAY_OF_YEAR);
//
//		intervalDay = currIntervalDay - oldIntervalDay;
//		if (intervalDay < 0) {
//			// 跨年了
//			intervalDay = intervalDay + oldCal.getMaximum(Calendar.DAY_OF_YEAR);
//		}
//		return intervalDay;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String begin = sdf.format(new Date(oldTime)); 
		String end = sdf.format(new Date(currTime));
		try {
			long day = (sdf.parse(end).getTime() - sdf.parse(begin).getTime()) / (24 * 3600 * 1000L);
			return (int) (day < 0 ? 0 : day);
		} catch (Exception e) {}
		return 0;
	}
	
	/**
	 * 查看相差的天数2
	 * */
	public static int betweenDate(Date d1, Date d2) {
		return betweenDate(d1.getTime(), d2.getTime());
	}

	/**
	 * 获取本周X的日期
	 * 
	 * @param time
	 *            时间锚
	 * @param weekDay
	 *            周X 推荐使用Calendar的星期常量表示 例：周一即Calendar.MONDAY
	 * */
	public static Date weekday(long time, int weekDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		calendar.set(Calendar.DAY_OF_WEEK, weekDay);
		return calendar.getTime();
	}

	/**
	 * 获取今天是周几
	 */
	public static int getWeekDay() {
		int arr[] = { 7, 1, 2, 3, 4, 5, 6 };// 字符串数组
		Calendar rightNow = Calendar.getInstance();
		int day = rightNow.get(Calendar.DAY_OF_WEEK);// 获取时间
		return arr[day - 1];
	}

	/**
	 * 判断两个日期是否在同一月
	 * */
	public static boolean isInSameMonth(Date d1, Date d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(d1).equals(sdf.format(d2));
	}
}

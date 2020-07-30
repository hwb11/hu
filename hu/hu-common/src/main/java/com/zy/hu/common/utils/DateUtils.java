package com.zy.intelligentdevice.common.utils;


import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
	
	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final SimpleDateFormat sdf_noYear = new SimpleDateFormat("MM-dd HH:mm");
	private static final SimpleDateFormat sdf_year = new SimpleDateFormat("yyyy");
	public static final String formateStr_yyyy_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String formateStr_yyyy_MM = "yyyy-MM-dd";
	public static final String formateStr_yyyy_MM_1 = "yyyy/MM/dd";
	public static final String formateStr_HH_mm_ss = "HH:mm:ss";
	public static final String formateStr_yyyy_MM_DD_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String formateStr_yyyy_MM_DD_HH_SS_1 = "yyyy/MM/dd HH:mm:ss";

	public static   Boolean isControl(Date startTime, Date endTime){
		Boolean isControl = false;//是否可以操作
		try{
			Date stime = startTime;
			Date etime = endTime;
			Date nowTime = new Date();
			if(nowTime.compareTo(stime)==1&&nowTime.compareTo(etime)==-1){
				isControl = true;
			}else{
				isControl = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return isControl;
	}
	/**
	 * 获取时间  如果是今年得，去掉年
	 * @param date
	 * @param time
	 */
	public static String getTime(Date date, Date time){
		String s_time = "";
		try{
			String year = sdf_year.format(time);
			String nowYear = sdf_year.format(new Date());
			if(year.equals(nowYear)){
				s_time = sdf_noYear.format(time);
			}else{
				s_time = sdf.format(time);
			}
		}catch(Exception e){
			return s_time;
		}
		return s_time;
	}

	/**
	 * 返回上一个小时
	 * @param date
	 */
	public static String getLastHour(Date date){
		String time = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		time = df.format(calendar.getTime());
		return time;
	}

	/**
	 * 返回昨天
	 */
	public static String getLastDay(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,-1);
		Date time=cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * 返回明天
	 * @param date
	 * @return
	 */
	public static String getNextDayString(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,1);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * 返回明天
	 * @param date
	 * @return
	 */
	public static Date getNextDayDate(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,1);
		Date time = cal.getTime();
		return time;
	}

	/**
	 * 获取上周一
	 * @param date
	 * @return
	 */
	public static Date geLastWeekMonday(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}

	/**
	 * 获取本周一
	 * @param date
	 * @return
	 */
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	/**
	 * 获取指定星期几
	 * @param date
	 * @return
	 */
	public static Date getThisWeekOtherday(Date date,Integer day) {
		if(day > 7 || day < 0 ){
			return null;
		}
		date = getThisWeekMonday(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,day-1);
		return cal.getTime();
	}

	/**
	 * 获取下周一
	 * @param date
	 * @return
	 */
	public static Date getNextWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 7);
		return cal.getTime();
	}

	/**
	 * 返回某月的最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(int year,int month){
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		return cal.getTime();
	}

	/**
	 * 返回某月的最后一天
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		return cal.getTime();
	}

	/**
	 * 获取下一月的第一天
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfNextMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		//获取某月最大天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		return cal.getTime();
	}

	/**
	 * 获取下一个月的最后一天
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfNextMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		return cal.getTime();
	}

	/**
	 * 返回上一个月
	 * @return
	 */
	public static String getLastMonth(Date date){
		String mon = "";
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		mon = format.format(m);
		return mon;

	}

	/**
	 * 获取指定月的第一天
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfThisMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//获取某月最大天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		return cal.getTime();
	}

	/**
	 * 获取指定月的某一天
	 * @param date
	 * @return
	 */
	public static Date getThisMonthOtherday(Date date,Integer day){
		int result = getMonthMaxDay(date);
		if(day > result){
			return null;
		}
		date = getFirstDayOfThisMonth(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,day-1);
		return cal.getTime();
	}

	public static int getMonthMaxDay(Date date){
		date = getFirstDayOfThisMonth(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回上2个小时
	 * @param date
	 */
	public static String getLastLastHour(Date date){
		String time = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 2);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		time = df.format(calendar.getTime());
		return time;
	}

	/**
	 * 返回前天
	 */
	public static String getLastLastDay(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,-2);
		Date time=cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * 返回上2个月
	 * @return
	 */
	public static String getLastLastMonth(Date date){
		String mon = "";
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		c.setTime(date);
		c.add(Calendar.MONTH, -2);
		Date m = c.getTime();
		mon = format.format(m);
		return mon;

	}
	
	public static Date getDateByStr(String dateTimeStr){
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.formateStr_yyyy_MM_DD_HH_MM_SS);
		try {
			if(StringUtils.isNotBlank(dateTimeStr)){
				date = simpleDateFormat.parse(dateTimeStr);
			}else{
				String str = simpleDateFormat.format(new Date());
				date = sdf.parse(dateTimeStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateByStr(String dateTimeStr,String formatStr){
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
		try {
			if(StringUtils.isNotBlank(dateTimeStr)){
				date = simpleDateFormat.parse(dateTimeStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getDateStrByDate(Date date,String formateStr){
		String dateStr = "";
		if(date!=null && StringUtils.isNotBlank(formateStr)){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formateStr);
			dateStr = simpleDateFormat.format(date);
		}
		return dateStr;
	}

	public static Date getDateByFormateStr(Date date,String formateStr){
		if(date!=null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formateStr);
			String dateStr = simpleDateFormat.format(date);
			try {
				return simpleDateFormat.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String getDateStrByDate(Date date){
		if(date!=null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.formateStr_yyyy_MM_DD_HH_MM_SS);
			return simpleDateFormat.format(date);
		}
		return null;
	}

	public static long getDateInterval(Date endDate, Date nowDate,String intervalType) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		long result = 0l;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		if(diff > 0){
			if("day".equals(intervalType)){
				// 计算差多少天
				result = diff / nd;
			}else if("hour".equals(intervalType)){
				// 计算差多少小时
				result = diff / nh;
			}else if("minute".equals(intervalType)){
				// 计算差多少分钟
				result = diff / nm;
			}else if("second".equals(intervalType)){
				// 计算差多少秒//输出结果
				result = diff / ns;
			}
		}
		return result;
	}

	public static String getInterValTimeForHHMMSS(long time){
		if(time < 0){
			return "";
		}
		int hour = (int) time/(60*60);
		int minute = (int)(time - hour*60*60)/60;
		int second = (int)(time -hour*60*60-minute*60);
		String hourStr = String.valueOf(hour);
		String minuteStr = String.valueOf(minute);
		String secondStr = String.valueOf(second);
		if(hour<10){
			hourStr = "0"+hourStr;
		}
		if(minute<10){
			minuteStr = "0"+minute;
		}
		if(second < 10){
			secondStr = "0"+secondStr;
		}
		return hourStr+":"+minuteStr+":"+secondStr;
	}

	//获取时间段中的随机时间
	public static Date getRandomTime(String startTime,String endTime){
		Date date = new Date();
		if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
			Date startDate = DateUtils.getDateByStr(startTime);
			Date endDate = DateUtils.getDateByStr(endTime);
			if(startDate!=null && endDate!=null){
				Long startL = startDate.getTime();
				Long endL = endDate.getTime();
				Long middleL = (long)(startL + (endL-startL)*Math.random());
				return new Date(middleL);
			}
		}
		return date;
	}

	//获取指定日期23:59:59
	public static Date getDate2359ByStrTime(String dateStr){
		Date date = null;
		if(StringUtils.isNotBlank(dateStr)){
			date = getDateByStr(dateStr,DateUtils.formateStr_yyyy_MM);
			if(date!=null){
				String newDateStr = getDateStrByDate(date,DateUtils.formateStr_yyyy_MM) +" 23:59:59";
				date = getDateByStr(newDateStr);
			}
		}
		return date;
	}


	//获取指定日期00:00:00
	public static Date getDate0000ByStrTime(String dateStr){
		Date date = null;
		if(StringUtils.isNotBlank(dateStr)){
			date = getDateByStr(dateStr,DateUtils.formateStr_yyyy_MM);
			if(date!=null){
				String newDateStr = getDateStrByDate(date,DateUtils.formateStr_yyyy_MM) +" 00:00:00";
				date = getDateByStr(newDateStr);
			}
		}
		return date;
	}

	public static Date getDate0000ByStrTime(Date paramDate){
		Date date = null;
		if(paramDate!=null){
			String newDateStr = getDateStrByDate(paramDate,DateUtils.formateStr_yyyy_MM) +" 00:00:00";
			date = getDateByStr(newDateStr);
		}
		return date;
	}

	/**
	 * 	获取当前系统时间
	 */
	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	public static String getCurrentTimeStr(){
		String result = getDateStrByDate(Calendar.getInstance().getTime(),DateUtils.formateStr_yyyy_MM_DD_HH_MM_SS);
		return result;
	}

	public static String getCurrentTimeStr(String format){
		String result = getDateStrByDate(Calendar.getInstance().getTime(),format);
		return result;
	}

	/**
	 * 时间格式  yyyy-MM-dd HH:mm:ss
	 * @param nowTime
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime.getTime() == startTime.getTime()
				|| nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	public static Date getRandomTime(String startTime,String endTime,String format){
		Date date = new Date();
		if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
			Date startDate = DateUtils.getDateByStr(startTime,format);
			Date endDate = DateUtils.getDateByStr(endTime,format);
			if(startDate!=null && endDate!=null){
				Long startL = startDate.getTime();
				Long endL = endDate.getTime();
				Long middleL = (long)(startL + (endL-startL)*Math.random());
				return new Date(middleL);
			}
		}
		return date;
	}

}

package com.drbwx.admin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 日期工具类
 * 
 * @author heihaier
 * 
 */
public class DateUtil extends DateUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(DateUtil.class);

	private static String[] parsePatterns = { "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {

		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {

		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {

		String formatDate = null;

		if (pattern != null && pattern.length > 0) {

			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {

			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}

		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {

		return formatDate(date, "yyyyMMddHHmmss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {

		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {

		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {

		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {

		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {

		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {

		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * 
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
	 * "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {

		Date date = null;

		if (str != null) {

			try {

				date = parseDate(str.toString(), parsePatterns);
			} catch (ParseException e) {
				logger.debug("ERROR : 日期转换异常 " + e.getMessage());
				e.printStackTrace();
			}
		}

		return date;
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {

		long t = new Date().getTime() - date.getTime();

		return t / (24 * 60 * 60 * 1000);
	}

	public static Date getDateStart(Date date) {

		if (date != null) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {

				date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
			} catch (ParseException e) {
				logger.debug("ERROR : 日期转换异常 " + e.getMessage());
				e.printStackTrace();
			}
		}

		return date;
	}

	public static Date getDateEnd(Date date) {

		if (date != null) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {

				date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
			} catch (ParseException e) {
				logger.debug("ERROR : 日期转换异常 " + e.getMessage());
				e.printStackTrace();
			}
		}

		return date;
	}

	public static String yyyyMMddHHmmss() {
		SimpleDateFormat c = new SimpleDateFormat("yyyyMMddHHmmss");
		return c.format(new Date());
	}

	public static String yyyyMMddHHmmss(Date date) {

		SimpleDateFormat c = new SimpleDateFormat("yyyyMMddHHmmss");

		return c.format(date);
	}

	public static long subtract(Date a, Date b) {

		return a.getTime() - b.getTime();
	}

	public static Date addDateDay(Date date, int dayNum) {
		Date addDate = null;
		if (date != null) {

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, dayNum);
			addDate = c.getTime();

		}

		return addDate;
	}

	/**
	 * 将字符串转换成日期
	 * 
	 * @param source
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return 日期
	 */
	public static Date parseStringToDate(String source, String pattern) {
		Date date = null;

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 将日期格式化为字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式
	 * @return 字符串
	 */
	public static String formatDateToString(Date date, String pattern) {
		String string = null;

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		string = sdf.format(date);

		return string;
	}

	/**
	 * 将日期格式化为字符串
	 * 
	 * @param pattern
	 *            日期格式
	 * @return 字符串
	 */
	public static String formatDateToString(String pattern) {
		return formatDateToString(new Date(), pattern);
	}

	/**
	 * 将某种格式的日期字符串转换成另一种格式的日期字符串
	 * 
	 * @param source
	 *            日期字符串
	 * @param oriPpattern
	 *            原来的日期格式
	 * @param newPattern
	 *            新日期格式
	 * @return 转换后的日期字符串
	 */
	public static String formatStringToString(String source,
			String oriPpattern, String newPattern) {
		if(source==null||"".equals(source)){
			return "";
		}
		
		String string = null;

		Date date = parseStringToDate(source, oriPpattern);
		string = formatDateToString(date, newPattern);

		return string;
	}

	/**
	 * 取得在指定时间上加减years年后的时间
	 * 
	 * @param date
	 *            指定时间
	 * @param years
	 *            年数，正为加，负为减
	 * @return 在指定时间上加减years年后的时间
	 */
	public static String addMinute(Date date, int minute) {
		Date time = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, minute);
		time = cal.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fullFormat = df.format(time);
		return fullFormat;
	}

	/**
	 * 获取两个日期相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDiffDays(Date date1, Date date2) {

		return (date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24);
	}

	public static void main(String[] args) {
		System.out.println(formatStringToString("2015/03/20", "yyyy/MM/dd",
				"yyyy-MM-dd HH:mm:ss"));
	}

}

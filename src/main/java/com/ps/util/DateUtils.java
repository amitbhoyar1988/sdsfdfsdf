package com.ps.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

public class DateUtils {

	static Logger logger = Logger.getLogger(DateUtils.class);

	static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static SimpleDateFormat dateMonthFormatter = new SimpleDateFormat("MM-dd");

	public static Date getDateTime(String inputDate) {

		if (logger.isDebugEnabled())
			logger.debug("In getDateTime utility method " + "converting string to date inputDate-> " + inputDate);

		Date date = null;
		try {
			if (!StringUtils.isBlank(inputDate)) {
				if (logger.isDebugEnabled())
					logger.debug("Parsing the input date-time string " + inputDate);
				date = dateTimeFormatter.parse(inputDate);
			}
		} catch (Exception e) {
			logger.error("Exception occured while " + "converting string to date-time " + e.getMessage(), e);
			e.printStackTrace();
		}

		if (logger.isDebugEnabled())
			logger.debug("Returning date-time after conversion, " + " date object-> " + date);

		return date;
	}

	public static String getDateTimeString(Date inputDate) {

		if (logger.isDebugEnabled())
			logger.debug(
					"In getDateTimeString utility method " + "converting date-time to string inputDate-> " + inputDate);

		String date = null;
		try {
			if (logger.isDebugEnabled())
				logger.debug("Formatting the input date object " + inputDate);
			date = dateTimeFormatter.format(inputDate);
		} catch (Exception e) {
			logger.error("Exception occured while " + "converting date-time to string " + e.getMessage(), e);
			e.printStackTrace();
		}

		if (logger.isDebugEnabled())
			logger.debug("Returning string date-time after conversion, " + " date String-> " + date);

		return date;
	}

	public static Date getDateMonth(String inputDate) {

		if (logger.isDebugEnabled())
			logger.debug("In getDateMonth utility method " + "converting string to date inputDate-> " + inputDate);

		Date date = null;
		try {
			if (!StringUtils.isBlank(inputDate)) {
				if (logger.isDebugEnabled())
					logger.debug("Parsing the input date-month string " + inputDate);
				date = dateMonthFormatter.parse(inputDate);
			}
		} catch (Exception e) {
			logger.error("Exception occured while " + "converting string to date " + e.getMessage(), e);
			e.printStackTrace();
		}

		if (logger.isDebugEnabled())
			logger.debug("Returning date-month after conversion, " + " date object-> " + date);

		return date;
	}

	public static String getDateMonthString(Date inputDate) {

		if (logger.isDebugEnabled())
			logger.debug(
					"In getDateMonthString utility method " + "converting date to string inputDate-> " + inputDate);

		String date = null;
		try {
			if (logger.isDebugEnabled())
				logger.debug("Formatting the input date object " + inputDate);
			date = dateMonthFormatter.format(inputDate);
		} catch (Exception e) {
			logger.error("Exception occured while " + "converting date to string " + e.getMessage(), e);
			e.printStackTrace();
		}

		if (logger.isDebugEnabled())
			logger.debug("Returning string date after conversion, " + " date String-> " + date);

		return date;
	}

	public static Date convert(LocalDate date, ZoneId zoneId) {
		if (date == null)
			return null;
		else
			return Date.from(date.atStartOfDay(zoneId).toInstant());
	}

	public static boolean checkStartDateLessThanEndDate(Date inputDate1, Date inputDate2) {

		if (logger.isDebugEnabled())
			logger.debug("In checkStartDateLessThanEndDate utility method " + "checking for StartDate-> " + inputDate1
					+ " and EndDate-> " + inputDate2);

		boolean result = true;
		try {
			if (inputDate1.compareTo(inputDate2) > 0) {
				result = false;
			}
		} catch (Exception e) {
			logger.error("Exception occured while " + "comparision between dates " + e.getMessage(), e);
			e.printStackTrace();
		}

		if (logger.isDebugEnabled())
			logger.debug("Returning result after comparision" + "checking for StartDate-> " + inputDate1
					+ " and EndDate-> " + inputDate2);

		return result;
	}

	public static boolean isFutureDate(Date inputDate) {

		if (logger.isDebugEnabled())
			logger.debug("In isFutureDate utility method " + "checking for Date-> " + inputDate);

		boolean result = false;
		Date current = new Date();
		try {
			if (!inputDate.before(current)) {
				result = true;
			}
		} catch (Exception e) {
			logger.error("Exception occured while " + "checking for Future Date " + e.getMessage(), e);
			e.printStackTrace();
		}
		if (logger.isDebugEnabled())
			logger.debug("Returning result after checking for Future Date" + "checking for Date-> " + inputDate);
		return result;
	}

	public static Date addOneDayToDate(Date date) {

		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			date = c.getTime();
		} catch (Exception e) {
			logger.error("Exception occured while adding 1 day into the date " + e.getMessage(), e);
			e.printStackTrace();
		}
		return date;
	}

	
	public static Date addHHMMToDate(Date date, int hh, int mm) {

		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);		
			c.add(Calendar.HOUR, hh);
	        c.add(Calendar.MINUTE, mm);
			date = c.getTime();
		} catch (Exception e) {
			logger.error("Exception occured while adding HH:MM into the date " + e.getMessage(), e);
			e.printStackTrace();
		}
		return date;
	}
}
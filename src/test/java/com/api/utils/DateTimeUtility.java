package com.api.utils;


import org.joda.time.Instant;

public class DateTimeUtility {

	public  DateTimeUtility() {}
	
	public static String getTimeWithDaysAgo(int days) {
		return Instant.now().minus(days).toString();
	}
}

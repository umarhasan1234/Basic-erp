package com.nrt.util;

public class CommonUtil {

	public static String changeLongToString(Long id) {

		return String.valueOf(id);
	}

	public static long changeStringToLong(String str) {
		return Long.parseLong(str);
	}

}
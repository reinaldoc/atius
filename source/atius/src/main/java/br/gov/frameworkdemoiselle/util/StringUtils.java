package br.gov.frameworkdemoiselle.util;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	public static String null2empty(String value) {
		if (value == null)
			return "";
		return value;
	}
}

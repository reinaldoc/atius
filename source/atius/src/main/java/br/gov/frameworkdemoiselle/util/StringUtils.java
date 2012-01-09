package br.gov.frameworkdemoiselle.util;


public class StringUtils extends org.apache.commons.lang.StringUtils {

	public static String null2empty(String value) {
		if (value == null)
			return "";
		return value;
	}

	@SuppressWarnings("deprecation")
	public static String capitalizeBr(String str) {
		String strTmp = capitaliseAllWords(str.toLowerCase());
		strTmp = strTmp.replaceAll(" Da ", " da ");
		strTmp = strTmp.replaceAll(" Das ", " das ");
		strTmp = strTmp.replaceAll(" Dos ", " dos ");
		strTmp = strTmp.replaceAll(" De ", " de ");
		strTmp = strTmp.replaceAll(" Do ", " do ");
		strTmp = strTmp.replaceAll(" Du ", " du ");
		strTmp = strTmp.replaceAll(" E ", " e ");
		return strTmp;
	}

}

package br.gov.frameworkdemoiselle.ldap.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.ldap.annotation.DistinguishedName;
import br.gov.frameworkdemoiselle.ldap.annotation.EntryKey;
import br.gov.frameworkdemoiselle.ldap.annotation.LDAPEntry;
import br.gov.frameworkdemoiselle.ldap.exception.EntryException;
import br.gov.frameworkdemoiselle.util.Beans;

public class ClazzUtils {

	private static Logger logger = LoggerProducer.create(ClazzUtils.class);

	/**
	 * Get @DistinguishedName value of a @LDAPEntry annotated object
	 * 
	 * @param entry
	 * @return Distinguished Name
	 */
	public static String getDistinguishedName(Object entry) {
		requireAnnotation(entry.getClass(), LDAPEntry.class);
		return (String) getRequiredAnnotatedValue(entry, DistinguishedName.class);
	}

	/**
	 * Convert @LDAPEntry annotated object to Map<String, String[]>
	 * 
	 * @param entry
	 * @return Entry Map
	 */
	public static Map<String, String[]> getStringsMap(Object entry) {
		requireAnnotation(entry.getClass(), LDAPEntry.class);
		Map<String, String[]> map = new HashMap<String, String[]>();

		Field[] fields = getSuperClassesFields(entry.getClass());
		for (Field field : fields) {
			if (field.isAnnotationPresent(DistinguishedName.class) || field.isAnnotationPresent(Ignore.class))
				continue;
			if (map.containsKey(field.getName()))
				continue;
			Object value = getFieldValue(field, entry);
			if (value == null)
				continue;
			if (value instanceof String[])
				map.put(field.getName(), (String[]) value);
			else if (verifyAnnotation(field.getType(), LDAPEntry.class))
				map.put(field.getName(), new String[] { (String) getRequiredAnnotatedValue(value, EntryKey.class) });
			else
				map.put(field.getName(), new String[] { value.toString() });
		}
		return map;
	}

	/**
	 * Convert a Map<dn, Map<attr, value[]>> to @LDAPEntry annotated object
	 * 
	 * @param entry
	 * @return Entry Map
	 */
	public static List<?> getEntryObjectList(Map<String, Map<String, String[]>> entryMap, Class<?> clazz) {
		requireAnnotation(clazz, LDAPEntry.class);
		List<Object> resultList = new ArrayList<Object>();
		for (Map.Entry<String, Map<String, String[]>> mapEntry : entryMap.entrySet()) {
			resultList.add(getEntryObject(mapEntry.getKey(), mapEntry.getValue(), Beans.getReference(clazz)));
		}
		return resultList;
	}

	public static Object getEntryObject(String dn, Map<String, String[]> map, Object entry) {

		Field[] fields = getSuperClassesFields(entry.getClass());
		for (Field field : fields) {
			logger.warn("Setting field " + entry.getClass().getSimpleName() + "." + field.getName() + " ==> " + field.getType().getSimpleName());
			if (field.isAnnotationPresent(Ignore.class))
				continue;
			if (field.isAnnotationPresent(DistinguishedName.class)) {
				setFieldValue(field, entry, dn);
				continue;
			}
			if (verifyAnnotation(field.getType(), LDAPEntry.class))
				setFieldValue(field, entry, getMappedEntryObject(field.getType(), map.get(field.getName())));
			else if (field.getType().isAssignableFrom(String.class))
				setFieldValue(field, entry, map.get(field.getName())[0]);
			else if (field.getType().isAssignableFrom(Integer.class))
				setFieldValue(field, entry, new Integer(map.get(field.getName())[0]));
			else if (field.getType().isArray())
				setFieldValue(field, entry, map.get(field.getName()));
			else
				logger.warn("Not handled field " + entry.getClass().getSimpleName() + "." + field.getName() + " ==> "
						+ field.getType().getSimpleName());

		}

		return entry;
	}

	public static Object getMappedEntryObject(Class<?> clazz, Object[] value) {
		return Beans.getReference(clazz);
	}

	/**
	 * Feeling a Class for a String Representation of Search Filters (RFC 4515)
	 * and throw EntryException when can't find a @LDAPEntry object
	 * 
	 * @param searchFilter
	 * @return Class
	 */
	public static Class<?> getRequiredClassForSearchFilter(String searchFilter, List<String> packageNames) {
		Class<?> clazz = getClassForSearchFilter(searchFilter, packageNames);
		if (clazz == null) {
			logger.error("Can't find a @LDAPEntry object for search filter " + searchFilter);
			throw new EntryException();
		}
		return clazz;
	}

	/**
	 * Feeling a Class or return null for a String Representation of Search
	 * Filters (RFC 4515)
	 * 
	 * @param searchFilter
	 * @return Class
	 */
	public static Class<?> getClassForSearchFilter(String searchFilter, List<String> packageNames) {
		if (searchFilter == null || packageNames == null || packageNames.size() == 0)
			return null;
		String patternStr = "objectClass=(.*?)(\\)|$)";
		Matcher matcher = Pattern.compile(patternStr).matcher(searchFilter);
		boolean matchFound = matcher.find();
		if (matchFound && matcher.groupCount() > 1)
			for (String packageName : packageNames)
				try {
					return Class.forName(packageName + "." + Character.toUpperCase(matcher.group(1).charAt(0)) + matcher.group(1).substring(1));
				} catch (Exception e) {
					return null;
				}
		return null;
	}

	/**
	 * Build a super classes List<Class<? extends Object>>
	 * 
	 * @param entry
	 * @param onlySuperClasses
	 * @return List of Super Classes
	 */
	public static List<Class<? extends Object>> getSuperClasses(Class<?> entryClass) {
		List<Class<? extends Object>> list = new ArrayList<Class<? extends Object>>();
		Class<? extends Object> superClazz = entryClass.getSuperclass();
		while (superClazz != null) {
			list.add(superClazz);
			superClazz = superClazz.getSuperclass();
		}
		return list;
	}

	/**
	 * Build a array of super classes fields
	 * 
	 * @param entry
	 * @param onlySuperClasses
	 * @return Array of Super Classes Fields
	 */
	public static Field[] getSuperClassesFields(Class<?> entryClass) {
		Field[] fieldArray = null;
		fieldArray = (Field[]) ArrayUtils.addAll(fieldArray, entryClass.getDeclaredFields());
		Class<?> superClazz = entryClass.getSuperclass();
		while (superClazz != null && !"java.lang.Object".equals(superClazz.getName())) {
			fieldArray = (Field[]) ArrayUtils.addAll(fieldArray, superClazz.getDeclaredFields());
			superClazz = superClazz.getSuperclass();
		}
		return fieldArray;
	}

	/**
	 * Get annotated value and when null throw EntryException
	 * 
	 * @param entry
	 * @param clazz
	 * @return
	 */
	public static Object getRequiredAnnotatedValue(Object entry, Class<? extends Annotation> clazz) {
		Object value = getAnnotatedValue(entry, clazz);
		if (value != null && !value.toString().trim().isEmpty()) {
			return value;
		}
		logger.error("Class " + entry.getClass().getSimpleName() + " doesn't have a valid value for @" + clazz.getSimpleName());
		throw new EntryException();
	}

	/**
	 * Get annotated value
	 * 
	 * @param entry
	 * @return Distinguished Name
	 */
	private static Object getAnnotatedValue(Object entry, Class<? extends Annotation> clazz) {
		Field[] fields = getSuperClassesFields(entry.getClass());
		for (Field field : fields)
			if (field.isAnnotationPresent(clazz))
				return getFieldValue(field, entry);
		return null;
	}

	/**
	 * Get field value
	 * 
	 * @param field
	 * @param parentObject
	 * @return
	 */
	public static Object getFieldValue(Field field, Object parentObject) {
		Object value = null;
		field.setAccessible(true);
		try {
			value = field.get(parentObject);
		} catch (Exception e) {
		}
		return value;
	}

	public static void setFieldValue(Field field, Object entry, Object value) {
		field.setAccessible(true);
		try {
			System.out.println("Field: " + field.getName() + " Value: " + value);
			field.set(entry, value);
		} catch (Exception e) {
			System.out.println("Exeception seting field");
			e.printStackTrace();
		}
		field.setAccessible(false);
	}

	/**
	 * Verify if annotation is present
	 * 
	 * @param entry
	 * @param clazz
	 */
	public static boolean verifyAnnotation(Class<?> entryClass, Class<? extends Annotation> clazz) {
		boolean annotationPresent = false;
		for (Class<? extends Object> claz : getSuperClasses(entryClass))
			if (claz.isAnnotationPresent(clazz))
				annotationPresent = true;
		return annotationPresent;
	}

	/**
	 * Verify if annotation is present on entry and when false throw
	 * EntryException
	 * 
	 * @param entry
	 * @param clazz
	 */
	public static void requireAnnotation(Class<?> entryClass, Class<? extends Annotation> clazz) {
		if (!verifyAnnotation(entryClass, clazz)) {
			logger.error("Entry " + entryClass.getSimpleName() + " doesn't have @" + clazz.getName());
			throw new EntryException();
		}

	}

}

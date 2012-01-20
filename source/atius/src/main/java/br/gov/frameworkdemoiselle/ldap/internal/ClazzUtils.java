package br.gov.frameworkdemoiselle.ldap.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.ldap.annotation.DistinguishedName;
import br.gov.frameworkdemoiselle.ldap.annotation.EntryKey;
import br.gov.frameworkdemoiselle.ldap.annotation.LDAPEntry;
import br.gov.frameworkdemoiselle.ldap.exception.EntryException;

public class ClazzUtils {

	private static Logger logger = LoggerProducer.create(ClazzUtils.class);

	/**
	 * Get @DistinguishedName value of a @LDAPEntry annotated object
	 * 
	 * @param entry
	 * @return Distinguished Name
	 */
	public static String getDistinguishedName(Object entry) {
		requireAnnotation(entry, LDAPEntry.class);
		return (String) getRequiredAnnotatedValue(entry, DistinguishedName.class);
	}

	/**
	 * Convert @LDAPEntry annotated object to Map<String, String[]>
	 * 
	 * @param entry
	 * @return Entry Map
	 */
	public static Map<String, String[]> getStringsMap(Object entry) {
		requireAnnotation(entry, LDAPEntry.class);
		Map<String, String[]> map = new HashMap<String, String[]>();

		Field[] fields = getSuperClassesFields(entry, false);
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
			else if (verifyAnnotation(value, LDAPEntry.class))
				map.put(field.getName(), new String[] { (String) getRequiredAnnotatedValue(value, EntryKey.class) });
			else
				map.put(field.getName(), new String[] { value.toString() });
		}
		return map;
	}

	/**
	 * Build a super classes List<Class<? extends Object>>
	 * 
	 * @param entry
	 * @param onlySuperClasses
	 * @return List of Super Classes
	 */
	public static List<Class<? extends Object>> getSuperClasses(Object entry, boolean onlySuperClasses) {
		List<Class<? extends Object>> list = new ArrayList<Class<? extends Object>>();
		if (entry != null) {
			if (!onlySuperClasses)
				list.add(entry.getClass());
			Class<? extends Object> superClazz = entry.getClass().getSuperclass();
			while (superClazz != null) {
				list.add(superClazz);
				superClazz = superClazz.getSuperclass();
			}
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
	public static Field[] getSuperClassesFields(Object entry, boolean onlySuperClasses) {
		Field[] fieldArray = null;
		if (entry != null) {
			if (!onlySuperClasses)
				fieldArray = (Field[]) ArrayUtils.addAll(fieldArray, entry.getClass().getDeclaredFields());
			Class<? extends Object> superClazz = entry.getClass().getSuperclass();
			while (superClazz != null && !"java.lang.Object".equals(superClazz.getName())) {
				fieldArray = (Field[]) ArrayUtils.addAll(fieldArray, superClazz.getDeclaredFields());
				superClazz = superClazz.getSuperclass();
			}
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
		Field[] fields = getSuperClassesFields(entry, false);
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

	/**
	 * Verify if annotation is present
	 * 
	 * @param entry
	 * @param clazz
	 */
	public static boolean verifyAnnotation(Object entry, Class<? extends Annotation> clazz) {
		boolean annotationPresent = false;
		if (entry != null)
			for (Class<? extends Object> claz : getSuperClasses(entry, false))
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
	public static void requireAnnotation(Object entry, Class<? extends Annotation> clazz) {
		if (!verifyAnnotation(entry, clazz)) {
			String entryClazzName = null;
			if (entry != null)
				entryClazzName = entry.getClass().getSimpleName();
			logger.error("Entry " + entryClazzName + " doesn't have @" + clazz.getName());
			throw new EntryException();
		}

	}

}

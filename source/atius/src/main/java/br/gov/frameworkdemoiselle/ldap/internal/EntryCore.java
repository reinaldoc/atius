package br.gov.frameworkdemoiselle.ldap.internal;

import java.io.Serializable;
import java.util.Map;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.ldap.annotation.DistinguishedName;
import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.exception.EntryException;
import br.gov.frameworkdemoiselle.util.Beans;

public class EntryCore implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntryCoreMap coreMap;

	public void persist(Object entry) {
		coreMap.persist(ClazzUtils.getObjectMap(entry), ClazzUtils.getDistinguishedName(entry));
	}

	public void merge(Object entry) {
		coreMap.merge(ClazzUtils.getObjectMap(entry), ClazzUtils.getDistinguishedName(entry));
	}

	public void merge(Object oldEntry, Object entry) {
		coreMap.merge(ClazzUtils.getObjectMap(oldEntry), ClazzUtils.getObjectMap(entry), ClazzUtils.getDistinguishedName(entry));
	}

	public void remove(Object entry) {
		coreMap.remove(ClazzUtils.getDistinguishedName(entry));
	}

	public <T> T find(Class<T> entryClass, Object id) {
		try {
			Map<String, String[]> map = coreMap.find(getReferenceFilter(entryClass, id));
			if (map == null)
				return null;
			return ClazzUtils.getEntryObject(map.get("dn")[0], map, entryClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EntryException("Error finding entry for Class " + entryClass + " and id " + id);
		}
	}

	public <T> T getReference(Class<T> entryClass, Object id) {
		try {
			T entry = Beans.getReference(entryClass);
			ClazzUtils.setAnnotatedFieldValueAs(entry, DistinguishedName.class,
					new String[] { coreMap.findReference(getReferenceFilter(entryClass, id)) });
			return entry;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EntryException("Error getting reference for Class " + entryClass + " and id " + id);
		}
	}

	public String findReference(Object searchFilter) {
		return coreMap.findReference((String) searchFilter);
	}

	private static String getReferenceFilter(Class<?> entryClass, Object id) {
		String fieldName = ClazzUtils.getFieldName(ClazzUtils.getRequiredFieldAnnotatedAs(entryClass, Id.class));
		return "(&(objectClass=" + entryClass.getSimpleName() + ")(" + fieldName + "=" + id + "))";
	}

}

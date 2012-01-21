package br.gov.frameworkdemoiselle.ldap.internal;

import java.io.Serializable;

import javax.inject.Inject;

public class EntryCore implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntryCoreMap coreMap;

	public void persist(Object entry) {
		coreMap.persist(ClazzUtils.getStringsMap(entry), ClazzUtils.getDistinguishedName(entry));
	}

	public void merge(Object entry) {
		coreMap.merge(ClazzUtils.getStringsMap(entry), ClazzUtils.getDistinguishedName(entry));
	}

	public void update(Object entry) {
		coreMap.update(ClazzUtils.getStringsMap(entry), ClazzUtils.getDistinguishedName(entry));
	}

	public void remove(Object entry) {
		coreMap.remove(ClazzUtils.getDistinguishedName(entry));
	}

	public <T> T find(Class<T> entryClass, Object searchFilter) {
		T entry = null;
		try {
			entry = entryClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return entry;
	}

	public <T> T getReference(Class<T> entryClass, Object dn) {
		T entry = null;
		try {
			entry = entryClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return entry;
	}

	public String findReference(Object searchFilter) {
		return coreMap.findReference((String) searchFilter);
	}

}
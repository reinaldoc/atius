package br.gov.frameworkdemoiselle.ldap.internal;

import java.io.Serializable;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.ldap.exception.EntryException;

public class EntryCore implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerProducer.create(EntryCore.class);

	private EntryCoreMap coreMap;

	public void persist(Object entry) {
		getCoreMap().persist(ClazzUtils.getStringsMap(entry), ClazzUtils.getDistinguishedName(entry));
	}

	public void merge(Object entry) {
		getCoreMap().merge(ClazzUtils.getStringsMap(entry), ClazzUtils.getDistinguishedName(entry));
	}

	public void update(Object entry) {
		getCoreMap().update(ClazzUtils.getStringsMap(entry), ClazzUtils.getDistinguishedName(entry));
	}

	public void remove(Object entry) {
		getCoreMap().remove(ClazzUtils.getDistinguishedName(entry));
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
		return getCoreMap().findReference((String) searchFilter);
	}

	private EntryCoreMap getCoreMap() {
		if (coreMap == null) {
			logger.error("EntryCoreMap is null (implementation error)");
			throw new EntryException();
		}
		return coreMap;
	}

	public void setCoreMap(EntryCoreMap coreMap) {
		this.coreMap = coreMap;
	}

}
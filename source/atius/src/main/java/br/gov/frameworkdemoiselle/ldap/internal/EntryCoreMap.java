package br.gov.frameworkdemoiselle.ldap.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.ldap.core.EntryQueryMap;
import br.gov.frameworkdemoiselle.ldap.exception.EntryException;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;

@RequestScoped
public class EntryCoreMap implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerProducer.create(EntryCoreMap.class);

	@Inject
	private ConnectionManager conn;

	@Inject
	private EntryQueryMap queryMap;

	private boolean verbose = false;

	private LDAPConnection getConnection() {
		return this.conn.initialized();
	}

	public void persist(Map<String, String[]> entry, String dn) {
		loggerArgs(entry, dn);
		try {
			LDAPAttributeSet attributeSet = new LDAPAttributeSet();
			for (Map.Entry<String, String[]> attrMap : entry.entrySet()) {
				loggerEntry(attrMap.getKey(), attrMap.getValue());
				attributeSet.add(new LDAPAttribute(attrMap.getKey(), attrMap.getValue()));
			}
			LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
			getConnection().add(newEntry);
		} catch (LDAPException e) {
			throw new EntryException("Error persisting entry " + dn);
		}
	}

	public void merge(Map<String, String[]> entry, String dn) {
		loggerArgs(entry, dn);
		try {
			List<LDAPModification> modList = new ArrayList<LDAPModification>();
			for (Map.Entry<String, String[]> attrMap : entry.entrySet()) {
				loggerEntry(attrMap.getKey(), attrMap.getValue());
				LDAPAttribute attribute = new LDAPAttribute(attrMap.getKey(), attrMap.getValue());
				modList.add(new LDAPModification(LDAPModification.REPLACE, attribute));
			}

			LDAPModification[] modsList = modList.toArray(new LDAPModification[0]);
			getConnection().modify(dn, modsList);
		} catch (LDAPException e) {
			throw new EntryException("Error merging entry " + dn);
		}
	}

	public void update(Map<String, String[]> entry, String dn) {
		loggerArgs(entry, dn);
		try {
			throw new LDAPException();
		} catch (LDAPException e) {
			throw new EntryException("Error updating entry " + dn);
		}
	}

	public void remove(String dn) {
		loggerArgs(null, dn);
		try {
			getConnection().delete(dn);
		} catch (LDAPException e) {
			throw new EntryException("Error deleting entry " + dn);
		}
	}

	public Map<String, String[]> find(String searchFilter) {
		loggerArgs(null, searchFilter);
		queryMap.setSearchFilter(searchFilter);
		return queryMap.getSingleResult();
	}

	public Map<String, String[]> getReference(String dn) {
		loggerArgs(null, dn);
		queryMap.setBaseDn(dn);
		queryMap.setScope(LDAPConnection.SCOPE_BASE);
		queryMap.setSearchFilter("objectClass=*");
		return queryMap.getSingleResult();
	}

	public String findReference(String searchFilter) {
		loggerArgs(null, searchFilter);
		queryMap.setSearchFilter(searchFilter);
		return queryMap.getSingleDn();
	}

	private void loggerArgs(Object entry, Object dn) {
		if (verbose) {
			logger.info(Thread.currentThread().getStackTrace()[2].getMethodName() + "(" + entry + "," + dn + ")");
			logger.info("dn: " + dn);
		}
	}

	private void loggerEntry(Object attr, Object value) {
		if (verbose) {
			if (value instanceof String[]) {
				String[] values = (String[]) value;
				for (String valueElement : values)
					logger.info(attr + ": " + valueElement);
			} else
				logger.info(attr + ": " + value);
		}
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

}
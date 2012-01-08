package br.gov.frameworkdemoiselle.ldap.internal;

import java.io.Serializable;
import java.util.Map;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.ldap.core.EntryQuery;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;

public class EntryCoreMap implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConnectionManager conn;

	@Inject
	private EntryQuery query;

	private LDAPConnection getConnection() {
		return this.conn.initialized();
	}

	/**
	 * Insert LDAP Entry
	 * 
	 * @throws LDAPException
	 */
	public void persist(Map<String, String[]> entry, String dn) throws LDAPException {
		LDAPAttributeSet attributeSet = new LDAPAttributeSet();
		for (Map.Entry<String, String[]> attrMap : entry.entrySet()) {
			attributeSet.add(new LDAPAttribute(attrMap.getKey(), attrMap.getValue()));
		}
		LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
		getConnection().add(newEntry);
	}

	/**
	 * Update not implemented
	 */
	public void merge(Map<String, String[]> entry, String dn) {

	}

	/**
	 * Remove LDAP Entry
	 * 
	 * @throws LDAPException
	 */
	public void remove(String dn) throws LDAPException {
		getConnection().delete(dn);
	}

	/**
	 * Find a LDAP Entry
	 */
	public Map<String, String[]> find(String dnSearchFilter) {
		query.setFilter(dnSearchFilter);
		return query.getSingleResult();
	}

	/**
	 * Find a LDAP Entry DN
	 */
	public String getReference(String dnSearchFilter) {
		query.setFilter(dnSearchFilter);
		return query.getSingleDn();
	}

}
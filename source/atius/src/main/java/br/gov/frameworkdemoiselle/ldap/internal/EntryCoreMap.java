package br.gov.frameworkdemoiselle.ldap.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.ldap.core.EntryQuery;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;

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
	 * Persist a LDAP Entry. Use LDAP Add Operation
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
	 * Update LDAP Entry from declared attributes only. Undeclared attributes
	 * will remain. Declared attributes will be replaced. Use LDAP Modify
	 * Operation.
	 */
	public void merge(Map<String, String[]> entry, String dn) throws LDAPException {
		List<LDAPModification> modList = new ArrayList<LDAPModification>();
		for (Map.Entry<String, String[]> attrMap : entry.entrySet()) {
			LDAPAttribute attribute = new LDAPAttribute(attrMap.getKey(), attrMap.getValue());
			modList.add(new LDAPModification(LDAPModification.REPLACE, attribute));
		}

		LDAPModification[] modsList = modList.toArray(new LDAPModification[0]);
		getConnection().modify(dn, modsList);
	}

	/**
	 * Update LDAP Entry to declared attributes only. Undeclared attributes will
	 * be removed. Declared attributes will be replaced. You must declare all
	 * required attributes. Use LDAP Modify Operation
	 */
	public void update(Map<String, String[]> entry, String dn) throws LDAPException {

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
	 * Find a LDAP Entry by LDAP Search Filter (RFC 4515)
	 */
	public Map<String, String[]> find(String searchFilter) {
		query.setFilter(searchFilter);
		return query.getSingleResult();
	}

	/**
	 * Find a LDAP Entry DN by DN (RFC 1485)
	 */
	public Map<String, String[]> getReference(String dn) {
		query.setBaseDn(dn);
		query.setScope(LDAPConnection.SCOPE_BASE);
		query.setFilter("objectClass=*");
		return query.getSingleResult();
	}

	/**
	 * Find a LDAP Entry DN by LDAP Search Filter (RFC 4515)
	 */
	public String findReference(String searchFilter) {
		query.setFilter(searchFilter);
		return query.getSingleDn();
	}

}
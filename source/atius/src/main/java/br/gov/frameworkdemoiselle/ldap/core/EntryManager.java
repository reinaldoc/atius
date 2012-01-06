package br.gov.frameworkdemoiselle.ldap.core;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.ldap.internal.ConnectionManager;
import br.gov.frameworkdemoiselle.ldap.internal.EntryCore;
import br.gov.frameworkdemoiselle.ldap.internal.EntryCoreMap;

import com.novell.ldap.LDAPException;

@SessionScoped
public class EntryManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerProducer.create(EntryManager.class);

	@Inject
	private ConnectionManager connectionManager;

	@Inject
	private EntryQuery query;

	@Inject
	private EntryCore core;

	@Inject
	private EntryCoreMap coreMap;

	private int protocol = 3;

	/**
	 * Set LDAP Protocol for LDAP BIND operation when not using resource
	 * configuration;
	 * 
	 * @param protocol
	 */
	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	/**
	 * Make a LDAP Connection with user values; Avoid use this method unless
	 * necessary, make resource configuration;
	 * 
	 * @throws LDAPException
	 */
	public boolean connect(String serverURI, boolean useTLS) {
		return connectionManager.connect(serverURI, useTLS);
	}

	/**
	 * Authenticate by user information; if already authenticated then reconnect
	 * and authenticate; Don't use this method unless necessary, make resource
	 * configuration;
	 * 
	 * @param binddn
	 * @param bindpw
	 */
	public boolean bind(String binddn, String bindpw) {
		return connectionManager.bind(binddn, bindpw, protocol);
	}

	/**
	 * Authenticate by user information; if already authenticated then reconnect
	 * and authenticate; Don't use this method unless necessary, make resource
	 * configuration;
	 * 
	 * @param binddn
	 * @param bindpw
	 */
	public boolean bind(String binddn, byte[] bindpw) {
		return connectionManager.bind(binddn, bindpw, protocol);
	}

	/**
	 * This is a isolated method that use a alternative connection to validate a
	 * dn or user and a password. This method don't touch current connection.
	 * 
	 * @param binddn
	 * @param bindpw
	 * @return
	 */
	public boolean authenticate(String binddn, String bindpw) {
		return connectionManager.authenticate(binddn, bindpw, protocol);
	}

	/**
	 * 
	 */
	public void persist(Map<String, String[]> entry, String dn) {
		try {
			coreMap.persist(entry, dn);
		} catch (LDAPException e) {
			logger.error("Error adding entry " + dn);
		}
	}

	/**
	 * 
	 */
	public void merge(Map<String, String[]> entry, String dn) {
		coreMap.merge(entry, dn);
	}

	/**
	 * Remove not implemented
	 */
	public void remove(String dn) {
		coreMap.remove(dn);
	}

	/**
	 * Find not implemented
	 */
	public Map<String, String[]> find(String rdn) {
		return coreMap.find(rdn);
	}

	/**
	 * Find not implemented
	 */
	public String getReference(String rdn) {
		return coreMap.getReference(rdn);
	}

	/**
	 * Future support for POJO. Not implemented.
	 */
	public void persist(Object entry) {
		core.persist(entry);
	}

	/**
	 * Future support for POJO. Not implemented.
	 */
	public void merge(Object entry) {
		core.merge(entry);
	}

	/**
	 * Future support for POJO. Not implemented.
	 */
	public void remove(Object entity) {
		core.remove(entity);
	}

	/**
	 * Future support for POJO. Not implemented.
	 */
	public <T> T find(Class<T> entryClass, Object dn) {
		return core.find(entryClass, dn);
	}

	/**
	 * Future support for POJO. Not implemented.
	 */
	public <T> T getReference(Class<T> entryClass, Object dn) {
		return core.getReference(entryClass, dn);
	}

	/**
	 * 
	 * @return
	 */
	public EntryQuery createQuery(String ldapSearchFilter) {
		query.setFilter(ldapSearchFilter);
		return query;
	}

}

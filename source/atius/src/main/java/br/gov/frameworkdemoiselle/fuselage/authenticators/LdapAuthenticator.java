package br.gov.frameworkdemoiselle.fuselage.authenticators;

import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.fuselage.business.UserBC;
import br.gov.frameworkdemoiselle.fuselage.configuration.LdapAuthenticatorConfig;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityUser;
import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.ldap.core.EntryManager;
import br.gov.frameworkdemoiselle.util.Strings;

public class LdapAuthenticator implements AuthenticatorModule {

	private Logger logger = LoggerProducer.create(LdapAuthenticator.class);

	@Inject
	private UserBC userBC;

	@Inject
	private EntryManager entryManager;

	@Inject
	private LdapAuthenticatorConfig ldapAuthConfig;

	private AuthenticatorResults results = new AuthenticatorResults();

	@Override
	public boolean authenticate(String username, String password) {
		results = new AuthenticatorResults();
		results.setAuthenticatorModuleName(getClass().getSimpleName());
		results.setLoggedIn(login(username, password));
		if (ldapAuthConfig.isVerbose()) {
			if (results.isLoggedIn())
				logger.info(userBC.getBundle().getString("fuselage.authenticators.login.success", username, results.getAuthenticatorModuleName()));
			else
				logger.info(userBC.getBundle().getString("fuselage.authenticators.login.failed", username, results.getAuthenticatorModuleName()));
			if (results.isUserUnavailable())
				logger.info(userBC.getBundle().getString("fuselage.authenticators.login.unavailable", username, results.getAuthenticatorModuleName()));
		}
		return results.isLoggedIn();
	}

	private boolean login(String username, String password) {
		if (Strings.isBlank(username) || Strings.isBlank(password))
			return false;

		if (entryManager.authenticate(username, password)) {
			results.getGenericResults().put("dn", entryManager.getAuthenticateDn());

			SecurityUser securityUser = userBC.loadByLogin(username);
			if (securityUser.getId() == null)
				securityUser.setLogin(username);
			else if (!securityUser.isEnabled()) {
				results.setSecurityUser(securityUser);
				results.setUserUnavailable(true);
				return false;
			}

			updateSecurityUser(securityUser);
			return true;
		}

		return false;
	}

	private void updateSecurityUser(SecurityUser securityUser) {
		Map<String, String> attMap;
		attMap = entryManager.createQuery(ldapAuthConfig.getUserSearchFilter().replaceAll("%u", securityUser.getLogin())).getSingleAttributesResult();

		securityUser.setName(attMap.get(ldapAuthConfig.getCnAttr()));
		securityUser.setOrgunit(attMap.get(ldapAuthConfig.getOuAttr()));
		securityUser.setDescription(attMap.get(ldapAuthConfig.getDescriptionAttr()));
		results.setSecurityUser(securityUser);

		userBC.insertOrUpdate(securityUser);

		Iterator<Map.Entry<String, String>> entryIter = attMap.entrySet().iterator();
		while (entryIter.hasNext()) {
			Map.Entry<String, String> entry = entryIter.next();
			if (entry.getValue() != null)
				results.getGenericResults().put(entry.getKey().toLowerCase(), entry.getValue());
		}
	}

	@Override
	public AuthenticatorResults getResults() {
		return results;
	}

}

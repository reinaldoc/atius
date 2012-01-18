package br.gov.frameworkdemoiselle.fuselage.authenticators;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.fuselage.business.UserBC;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityUser;
import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.util.Strings;

public class LocalAuthenticator implements AuthenticatorModule {

	private Logger logger = LoggerProducer.create(LocalAuthenticator.class);

	@Inject
	private UserBC userBC;

	private AuthenticatorResults results = new AuthenticatorResults();

	public boolean authenticate(String username, String password) {
		results = new AuthenticatorResults();
		results.setAuthenticatorModuleName(getClass().getSimpleName());
		results.setLoggedIn(login(username, password));
		if (results.isLoggedIn())
			logger.info(userBC.getBundle().getString("fuselage.authenticators.login.success", username, results.getAuthenticatorModuleName()));
		else
			logger.info(userBC.getBundle().getString("fuselage.authenticators.login.failed", username, results.getAuthenticatorModuleName()));
		if (results.isUserUnavailable())
			logger.info(userBC.getBundle().getString("fuselage.authenticators.login.unavailable", username, results.getAuthenticatorModuleName()));
		return results.isLoggedIn();
	}

	private boolean login(String username, String password) {
		if (Strings.isBlank(username) || Strings.isBlank(password))
			return false;

		SecurityUser securityUser = userBC.loadByLogin(username);
		results.setSecurityUser(securityUser);

		if (securityUser.getId() != null && !securityUser.isEnabled()) {
			results.setUserUnavailable(true);
			return false;
		}

		if (DigestUtils.sha512Hex(password).equals(securityUser.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public AuthenticatorResults getResults() {
		return results;
	}

}

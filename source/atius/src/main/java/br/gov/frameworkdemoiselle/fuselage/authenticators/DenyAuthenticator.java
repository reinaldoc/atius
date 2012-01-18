package br.gov.frameworkdemoiselle.fuselage.authenticators;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class DenyAuthenticator implements AuthenticatorModule {

	private Logger logger = LoggerProducer.create(DenyAuthenticator.class);

	private AuthenticatorResults results = new AuthenticatorResults();

	private ResourceBundle bundle;

	@Override
	public String getModuleName() {
		return "DenyAuthenticator";
	}

	public boolean authenticate(String username, String password) {
		results = new AuthenticatorResults();
		results.setAuthenticatorModuleName(getModuleName());
		results.setUserUnavailable(true);
		logger.info(bundle.getString("fuselage.authenticators.login.failed", username, getModuleName()));
		return false;
	}

	@Override
	public AuthenticatorResults getResults() {
		return results;
	}

}

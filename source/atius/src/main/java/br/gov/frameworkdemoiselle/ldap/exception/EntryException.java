package br.gov.frameworkdemoiselle.ldap.exception;

import br.gov.frameworkdemoiselle.DemoiselleException;

public class EntryException extends DemoiselleException {

	private static final long serialVersionUID = 1L;

	public EntryException(String message) {
		super(message);
	}

	public EntryException(String message, Throwable cause) {
		super(message, cause);
	}

}

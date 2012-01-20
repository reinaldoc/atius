package br.gov.frameworkdemoiselle.ldap.internal;

import java.io.Serializable;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;

public class AbstractEntryQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger;

	private boolean verbose = false;

	private String searchFilter;

	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerProducer.create(this.getClass());
		return logger;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public String getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

}

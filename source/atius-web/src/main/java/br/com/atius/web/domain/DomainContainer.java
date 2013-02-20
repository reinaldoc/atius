package br.com.atius.web.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class DomainContainer extends Entry {

	@Id
	private String cn;

	private int nextUidNumber;

	public DomainContainer() {
		super();
	}

	public DomainContainer(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "domainContainer" };
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public int getNextUidNumber() {
		return nextUidNumber;
	}

	public void setNextUidNumber(int nextUidNumber) {
		this.nextUidNumber = nextUidNumber;
	}

}

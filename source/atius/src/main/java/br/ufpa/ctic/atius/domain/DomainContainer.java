package br.ufpa.ctic.atius.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class DomainContainer extends Entry {

	@Id
	private String cn;

	private Integer nextUidNumber;

	public DomainContainer() {
		super();
	}

	public DomainContainer(boolean forMerge) {
		super(forMerge);
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

	public Integer getNextUidNumber() {
		return nextUidNumber;
	}

	public void setNextUidNumber(Integer nextUidNumber) {
		this.nextUidNumber = nextUidNumber;
	}

}

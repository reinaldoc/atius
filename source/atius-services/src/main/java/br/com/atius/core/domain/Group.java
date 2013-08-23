package br.com.atius.core.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.annotation.LDAPEntry;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

@LDAPEntry
public class Group extends Entry {

	@Id
	private String cn;

	private String[] member;

	@Override
	protected String[] objectClass() {
		return null;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String[] getMember() {
		return member;
	}

	public void setMember(String[] member) {
		this.member = member;
	}

}

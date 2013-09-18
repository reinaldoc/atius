package br.com.atius.core.domain;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.annotation.LDAPEntry;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

@LDAPEntry
public class OrganizationalPerson extends Entry {

	@Id
	private String cn;

	private String[] memberOf;

	private String mail;

	@Name("wWWHomePage")
	private String redirectEmail;

	@Name("description")
	private String cargo;

	@Name("physicalDeliveryOfficeName")
	private String unidade;

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

	public String[] getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(String[] memberOf) {
		this.memberOf = memberOf;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRedirectEmail() {
		return redirectEmail;
	}

	public void setRedirectEmail(String redirectEmail) {
		this.redirectEmail = redirectEmail;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

}

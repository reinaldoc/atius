package br.ufpa.ctic.atius.domain;

import org.hibernate.validator.constraints.NotEmpty;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class InetOrgPerson extends Entry {

	private String cn;

	@Id
	@NotEmpty(message = "Identifique o contato")
	private String mail;

	protected String[] objectClass() {
		return new String[] { "inetOrgPerson" };
	}

	public InetOrgPerson() {
		super();
	}

	public InetOrgPerson(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCnMail() {
		if (cn != null && !cn.isEmpty())
			return cn + " (" + mail + ")";
		return mail;
	}

}

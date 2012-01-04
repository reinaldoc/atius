package br.ufpa.ctic.atius.domain;

public class InetOrgPerson {

	private String cn;

	private String mail;

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
		return cn + " (" + mail + ")";
	}

}

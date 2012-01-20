package br.ufpa.ctic.atius.domain;

import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class WebsiteCategory extends Entry {

	private String cn;

	private String order;

	protected String[] objectClass() {
		return new String[] { "websiteCategory" };
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String name) {
		this.cn = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}

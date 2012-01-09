package br.ufpa.ctic.atius.domain;

import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class WebsiteCategory extends Entry {

	private String name;

	private String order;

	protected String[] objectClass() {
		return new String[] { "websiteCategory" };
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}

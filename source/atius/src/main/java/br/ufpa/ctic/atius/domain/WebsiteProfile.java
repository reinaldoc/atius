package br.ufpa.ctic.atius.domain;

import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class WebsiteProfile extends Entry {

	private String name;

	private String webserverName;

	private String schemaserverName;

	protected String[] objectClass() {
		return new String[] { "websiteProfile" };
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebserverName() {
		return webserverName;
	}

	public void setWebserverName(String webserverName) {
		this.webserverName = webserverName;
	}

	public String getSchemaserverName() {
		return schemaserverName;
	}

	public void setSchemaserverName(String schemaserverName) {
		this.schemaserverName = schemaserverName;
	}

}

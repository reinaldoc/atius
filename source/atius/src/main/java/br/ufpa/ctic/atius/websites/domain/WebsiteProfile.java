package br.ufpa.ctic.atius.websites.domain;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class WebsiteProfile extends Entry {

	@Id
	@Name("cn")
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

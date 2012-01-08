package br.ufpa.ctic.atius.domain;

public class WebsiteProfile {

	private String dn;

	private String[] objectClass = new String[] { "websiteProfile" };

	private String name;

	private String webserverName;

	private String schemaserverName;

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String[] getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(String[] objectClass) {
		this.objectClass = objectClass;
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

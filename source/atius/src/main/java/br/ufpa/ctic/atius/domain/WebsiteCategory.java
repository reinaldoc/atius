package br.ufpa.ctic.atius.domain;


public class WebsiteCategory {

	private String dn;

	private String[] objectClass = new String[] { "websiteCategory" };

	private String name;

	private String order;

	public WebsiteCategory() {
		// TODO Auto-generated constructor stub
	}

	public WebsiteCategory(String name, String order) {
		this.name = name;
		this.order = order;
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

}

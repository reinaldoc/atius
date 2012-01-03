package br.ufpa.ctic.atius.domain;


public class WebsiteDomain {

	private String dn;

	private String[] objectClass = new String[] { "websiteCategory", "posixAccount" };

	private String cn;

	private String serverName;

	private String websiteProfile;

	private String websiteCategory;

	private String uid;

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getWebsiteProfile() {
		return websiteProfile;
	}

	public void setWebsiteProfile(String websiteProfile) {
		this.websiteProfile = websiteProfile;
	}

	public String getWebsiteCategory() {
		return websiteCategory;
	}

	public void setWebsiteCategory(String websiteCategory) {
		this.websiteCategory = websiteCategory;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public WebsiteDomain() {
		// TODO Auto-generated constructor stub
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

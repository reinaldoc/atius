package br.ufpa.ctic.atius.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class WebsiteDomain {

	private String dn;

	private String[] objectClass = new String[] { "websiteCategory", "posixAccount" };

	@Size(min = 10, max = 128, message = "Identifique melhor a entidade responsável.")
	private String cn;

	@Size(min = 10, max = 128, message = "Especifique o domínio.")
	private String serverName;

	@NotEmpty(message = "Selecione o tipo do site requerido.")
	private String websiteProfile;

	@NotEmpty(message = "Selecione a categoria do site requerido.")
	private String websiteCategory;

	private String uid;

	@NotNull(message = "Especifique o proprietário do site.")
	private InetOrgPerson ownerId;

	@NotNull(message = "Especifique o contato técnico do site.")
	private InetOrgPerson adminId;

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

	public InetOrgPerson getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(InetOrgPerson ownerId) {
		this.ownerId = ownerId;
	}

	public InetOrgPerson getAdminId() {
		return adminId;
	}

	public void setAdminId(InetOrgPerson adminId) {
		this.adminId = adminId;
	}

}

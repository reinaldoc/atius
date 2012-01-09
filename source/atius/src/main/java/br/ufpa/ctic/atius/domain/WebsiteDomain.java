package br.ufpa.ctic.atius.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class WebsiteDomain extends Entry {

	@Size(min = 10, max = 128, message = "Identifique melhor a entidade responsável.")
	private String cn;

	@Size(min = 10, max = 128, message = "Especifique o domínio.")
	@Pattern(regexp = "^(?!www\\.).*$", message = "Não use www no domínio.")
	private String serverName;

	@NotEmpty(message = "Selecione o tipo do site requerido.")
	private String websiteProfile;

	@NotEmpty(message = "Selecione a categoria do site requerido.")
	private String websiteCategory;

	@NotNull(message = "Especifique o proprietário do site.")
	private InetOrgPerson ownerId;

	@NotNull(message = "Especifique o contato técnico do site.")
	private InetOrgPerson adminId;

	private Integer blockCount = new Integer(0);

	private Integer inodeCount = new Integer(0);

	private String availability = "enabled";

	private String documentRoot;

	private String serverAlias;

	private String uid;

	private String uidNumber;

	private String gidNumber = "100";

	private String homeDirectory;

	private String loginShell = "/bin/false";

	protected String[] objectClass() {
		return new String[] { "websiteDomain", "posixAccount", "shadowAccount" };
	}

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

	public Integer getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(Integer blockCount) {
		this.blockCount = blockCount;
	}

	public Integer getInodeCount() {
		return inodeCount;
	}

	public void setInodeCount(Integer inodeCount) {
		this.inodeCount = inodeCount;
	}

	public String getAvailability() {
		return availability;
	}

	public boolean isAvailabilityEnabled() {
		if ("enabled".equals(availability))
			return true;
		return false;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getDocumentRoot() {
		if (documentRoot == null && serverName != null)
			documentRoot = "/var/www/" + serverName.split("\\.")[0];
		return documentRoot;
	}

	public void setDocumentRoot(String documentRoot) {
		this.documentRoot = documentRoot;
	}

	public String getServerAlias() {
		if (serverAlias == null && serverName != null)
			serverAlias = "www." + serverName;
		return serverAlias;
	}

	public void setServerAlias(String serverAlias) {
		this.serverAlias = serverAlias;
	}

	public String getUid() {
		if (uid == null && serverName != null)
			uid = serverName.split("\\.")[0];
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUidNumber() {
		return uidNumber;
	}

	public void setUidNumber(String uidNumber) {
		this.uidNumber = uidNumber;
	}

	public String getGidNumber() {
		return gidNumber;
	}

	public void setGidNumber(String gidNumber) {
		this.gidNumber = gidNumber;
	}

	public String getHomeDirectory() {
		if (homeDirectory == null)
			homeDirectory = getDocumentRoot();
		return homeDirectory;
	}

	public void setHomeDirectory(String homeDirectory) {
		this.homeDirectory = homeDirectory;
	}

	public String getLoginShell() {
		return loginShell;
	}

	public void setLoginShell(String loginShell) {
		this.loginShell = loginShell;
	}

}

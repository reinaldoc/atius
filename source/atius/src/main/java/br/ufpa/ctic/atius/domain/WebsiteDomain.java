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

	private Integer blockSoftLimit;

	private Integer blockHardLimit;

	private Integer inodeSoftLimit;

	private Integer inodeHardLimit;

	private Integer blockCount = new Integer(0);

	private Integer inodeCount = new Integer(0);

	private String graceTimeCountdown;

	private String availability = "enabled";

	private String documentRoot;

	private String serverAlias;

	private Boolean suPhpEngine;

	private String uid;

	private String uidNumber;

	private String gidNumber = "100";

	private String homeDirectory;

	private String loginShell = "/bin/false";

	public WebsiteDomain() {
		super();
	}

	public WebsiteDomain(boolean setObjectClass) {
		super(setObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "websiteDomain", "posixAccount", "shadowAccount" };
	}

	public void setValuesByServerName() {
		setDocumentRoot();
		setServerAlias();
		setUid();
		setHomeDirectory();
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

	public Integer getBlockSoftLimit() {
		return blockSoftLimit;
	}

	public void setBlockSoftLimit(Integer blockSoftLimit) {
		this.blockSoftLimit = blockSoftLimit;
	}

	public Integer getBlockHardLimit() {
		return blockHardLimit;
	}

	public void setBlockHardLimit(Integer blockHardLimit) {
		this.blockHardLimit = blockHardLimit;
	}

	public Integer getInodeSoftLimit() {
		return inodeSoftLimit;
	}

	public void setInodeSoftLimit(Integer inodeSoftLimit) {
		this.inodeSoftLimit = inodeSoftLimit;
	}

	public Integer getInodeHardLimit() {
		return inodeHardLimit;
	}

	public void setInodeHardLimit(Integer inodeHardLimit) {
		this.inodeHardLimit = inodeHardLimit;
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

	public String getGraceTimeCountdown() {
		return graceTimeCountdown;
	}

	public void setGraceTimeCountdown(String graceTimeCountdown) {
		this.graceTimeCountdown = graceTimeCountdown;
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
		return documentRoot;
	}

	public void setDocumentRoot(String documentRoot) {
		this.documentRoot = documentRoot;
	}

	public void setDocumentRoot() {
		if (serverName != null)
			documentRoot = "/var/www/" + serverName.split("\\.")[0];
	}

	public String getServerAlias() {
		return serverAlias;
	}

	public void setServerAlias(String serverAlias) {
		this.serverAlias = serverAlias;
	}

	public void setServerAlias() {
		if (serverName != null)
			serverAlias = "www." + serverName;
	}

	public Boolean getSuPhpEngine() {
		return suPhpEngine;
	}

	public void setSuPhpEngine(Boolean suPhpEngine) {
		this.suPhpEngine = suPhpEngine;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setUid() {
		if (serverName != null)
			uid = serverName.split("\\.")[0];
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
		return homeDirectory;
	}

	public void setHomeDirectory(String homeDirectory) {
		this.homeDirectory = homeDirectory;
	}

	public void setHomeDirectory() {
		if (serverName != null)
			homeDirectory = "/var/www/" + serverName.split("\\.")[0];
	}

	public String getLoginShell() {
		return loginShell;
	}

	public void setLoginShell(String loginShell) {
		this.loginShell = loginShell;
	}

}

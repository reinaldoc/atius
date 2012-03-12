package br.ufpa.ctic.atius.dhcp.domain;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

public class DhcpService extends DhcpOption {

	@Id
	private String cn;

	private String dhcpPrimaryDN;

	private String[] dhcpStatements;

	@Ignore
	private boolean dhcpStatementsLoaded;

	@Ignore
	private Boolean dhcpStatementsAuthoritative;

	@Ignore
	private String dhcpStatementsLeaseTime;

	@Ignore
	private String dhcpStatementsMaxLeaseTime;

	@Ignore
	private String dhcpServerName;

	public DhcpService() {
		super();
		dhcpStatementsLoaded = false;
	}

	public DhcpService(boolean skipObjectClass) {
		super(skipObjectClass);
		dhcpStatementsLoaded = false;
	}

	protected String[] objectClass() {
		return new String[] { "dhcpService", "dhcpOptions" };
	}

	public void getStatements() {
		if (dhcpStatements != null && !dhcpStatementsLoaded) {
			dhcpStatementsLoaded = true;
			for (String statement : dhcpStatements)
				if ("default-lease-time".equals(Strings.substringBefore(statement, " ")))
					dhcpStatementsLeaseTime = Strings.substringAfter(statement, " ");
				else if ("max-lease-time".equals(Strings.substringBefore(statement, " ")))
					dhcpStatementsMaxLeaseTime = Strings.substringAfter(statement, " ");
				else if ("authoritative".equals(statement))
					dhcpStatementsAuthoritative = true;
		}
	}

	public void setStatements() {
		String authoritative;
		if (dhcpStatementsAuthoritative == true)
			authoritative = "authoritative";
		else
			authoritative = "not authoritative";
		dhcpStatements = new String[] { authoritative, "ddns-update-style none", "default-lease-time " + dhcpStatementsLeaseTime,
				"max-lease-time " + dhcpStatementsMaxLeaseTime };
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getDhcpPrimaryDN() {
		return dhcpPrimaryDN;
	}

	public void setDhcpPrimaryDN(String dhcpPrimaryDN) {
		this.dhcpPrimaryDN = dhcpPrimaryDN;
	}

	public String[] getDhcpStatements() {
		return dhcpStatements;
	}

	public void setDhcpStatements(String[] dhcpStatements) {
		this.dhcpStatements = dhcpStatements;
	}

	public boolean isDhcpStatementsAuthoritative() {
		if (dhcpStatementsAuthoritative == null) {
			dhcpStatementsAuthoritative = false;
			getStatements();
		}
		return dhcpStatementsAuthoritative;
	}

	public void setDhcpStatementsAuthoritative(boolean dhcpStatementsAuthoritative) {
		this.dhcpStatementsAuthoritative = dhcpStatementsAuthoritative;
	}

	public String getDhcpStatementsLeaseTime() {
		if (dhcpStatementsLeaseTime == null)
			getStatements();
		return dhcpStatementsLeaseTime;
	}

	public void setDhcpStatementsLeaseTime(String dhcpStatementsLeaseTime) {
		this.dhcpStatementsLeaseTime = dhcpStatementsLeaseTime;
	}

	public String getDhcpStatementsMaxLeaseTime() {
		if (dhcpStatementsMaxLeaseTime == null)
			getStatements();
		return dhcpStatementsMaxLeaseTime;
	}

	public void setDhcpStatementsMaxLeaseTime(String dhcpStatementsMaxLeaseTime) {
		this.dhcpStatementsMaxLeaseTime = dhcpStatementsMaxLeaseTime;
	}

	public String getDhcpServerName() {
		return dhcpServerName;
	}

	public void setDhcpServerName(String dhcpServerName) {
		this.dhcpServerName = dhcpServerName;
	}

}

package br.ufpa.ctic.atius.dhcp.domain;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

public class DhcpService extends Entry {

	@Id
	private String cn;

	private String dhcpPrimaryDN;

	private String[] dhcpOption;

	@Ignore
	private String dhcpOptionDNS;

	@Ignore
	private String dhcpOptionNTP;

	@Ignore
	private String dhcpOptionDomainPrefix;

	private String[] dhcpStatements;

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
	}

	public DhcpService(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "dhcpService", "dhcpOptions" };
	}

	public void getOptions() {
		if (dhcpOption != null)
			for (String option : dhcpOption) {
				if ("ntp-servers".equals(Strings.substringBefore(option, " ")))
					dhcpOptionNTP = Strings.substringAfter(option, " ");
				else if ("domain-name-servers".equals(Strings.substringBefore(option, " ")))
					dhcpOptionDNS = Strings.substringAfter(option, " ");
				else if ("domain-name".equals(Strings.substringBefore(option, " ")))
					dhcpOptionDomainPrefix = Strings.substringBetween(option, "\"");
			}
	}

	public void getStatements() {
		if (dhcpStatements != null)
			for (String statement : dhcpStatements)
				if ("default-lease-time".equals(Strings.substringBefore(statement, " ")))
					dhcpStatementsLeaseTime = Strings.substringAfter(statement, " ");
				else if ("max-lease-time".equals(Strings.substringBefore(statement, " ")))
					dhcpStatementsMaxLeaseTime = Strings.substringAfter(statement, " ");
				else if ("authoritative".equals(statement))
					dhcpStatementsAuthoritative = true;
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

	public String[] getDhcpOption() {
		return dhcpOption;
	}

	public void setDhcpOption(String[] dhcpOption) {
		this.dhcpOption = dhcpOption;
	}

	public String getDhcpOptionDNS() {
		if (dhcpOptionDNS == null) {
			dhcpOptionDNS = "0.0.0.0";
			getOptions();
		}
		return dhcpOptionDNS;
	}

	public void setDhcpOptionDNS(String dhcpOptionDNS) {
		this.dhcpOptionDNS = dhcpOptionDNS;
	}

	public String getDhcpOptionNTP() {
		if (dhcpOptionNTP == null) {
			dhcpOptionNTP = "0.0.0.0";
			getOptions();
		}
		return dhcpOptionNTP;
	}

	public void setDhcpOptionNTP(String dhcpOptionNTP) {
		this.dhcpOptionNTP = dhcpOptionNTP;
	}

	public String getDhcpOptionDomainPrefix() {
		if (dhcpOptionDomainPrefix == null) {
			dhcpOptionDomainPrefix = "-";
			getOptions();
		}
		return dhcpOptionDomainPrefix;
	}

	public void setDhcpOptionDomainPrefix(String dhcpOptionDomainPrefix) {
		this.dhcpOptionDomainPrefix = dhcpOptionDomainPrefix;
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
		if (dhcpStatementsLeaseTime == null) {
			dhcpStatementsLeaseTime = "0";
			getStatements();
		}
		return dhcpStatementsLeaseTime;
	}

	public void setDhcpStatementsLeaseTime(String dhcpStatementsLeaseTime) {
		this.dhcpStatementsLeaseTime = dhcpStatementsLeaseTime;
	}

	public String getDhcpStatementsMaxLeaseTime() {
		if (dhcpStatementsMaxLeaseTime == null) {
			dhcpStatementsMaxLeaseTime = "0";
			getStatements();
		}
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

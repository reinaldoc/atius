package br.com.atius.dhcp.domain;

import org.apache.commons.lang.ArrayUtils;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.gov.frameworkdemoiselle.ldap.template.Entry;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

public class DhcpOption extends Entry {

	private String[] dhcpStatements;

	@Ignore
	private Boolean dhcpStatementsAuthoritative;

	@Ignore
	private String dhcpStatementsLeaseTime;

	@Ignore
	private String dhcpStatementsMaxLeaseTime;

	private String[] dhcpOption;

	@Ignore
	private boolean dhcpLoaded;

	@Ignore
	private String dhcpOptionGateway;

	@Ignore
	private String dhcpOptionDomainPrefix;

	@Ignore
	private String dhcpOptionDNS;

	@Ignore
	private String dhcpOptionNTP;

	@Ignore
	private String dhcpOptionSMB;

	@Ignore
	private String dhcpOptionSMBtype;

	public DhcpOption() {
		super();
		dhcpLoaded = false;
	}

	public DhcpOption(boolean skipObjectClass) {
		super(skipObjectClass);
		dhcpLoaded = false;
	}

	@Override
	protected String[] objectClass() {
		return null;
	}

	/**
	 * Populate dhcpOptionGateway, dhcpOptionNTP, dhcpOptionDNS,
	 * dhcpOptionDomainPrefix, dhcpOptionSMB from dhcpOption attribute;
	 * 
	 * Populate dhcpStatementsAuthoritative, dhcpStatementsLeaseTime,
	 * dhcpStatementsMaxLeaseTime from dhcpStatements attribute;
	 */
	public void load() {
		if (!dhcpLoaded) {
			dhcpLoaded = true;
			if (dhcpOption != null)
				for (String option : dhcpOption)
					if ("routers".equals(Strings.substringBefore(option, " ")))
						dhcpOptionGateway = Strings.substringAfter(option, " ");
					else if ("ntp-servers".equals(Strings.substringBefore(option, " ")))
						dhcpOptionNTP = Strings.substringAfter(option, " ");
					else if ("domain-name-servers".equals(Strings.substringBefore(option, " ")))
						dhcpOptionDNS = Strings.substringAfter(option, " ");
					else if ("domain-name".equals(Strings.substringBefore(option, " ")))
						dhcpOptionDomainPrefix = Strings.substringBetween(option, "\"");
					else if ("netbios-name-servers".equals(Strings.substringBefore(option, " ")))
						dhcpOptionSMB = Strings.substringAfter(option, " ");
					else if ("netbios-node-type".equals(Strings.substringBefore(option, " ")))
						dhcpOptionSMBtype = Strings.substringAfter(option, " ");
			if (dhcpStatements != null)
				for (String statement : dhcpStatements)
					if ("default-lease-time".equals(Strings.substringBefore(statement, " ")))
						dhcpStatementsLeaseTime = Strings.substringAfter(statement, " ");
					else if ("max-lease-time".equals(Strings.substringBefore(statement, " ")))
						dhcpStatementsMaxLeaseTime = Strings.substringAfter(statement, " ");
					else if ("authoritative".equals(statement))
						dhcpStatementsAuthoritative = true;
		}

	}

	/**
	 * Set dhcpOption attribute from dhcpOptionGateway, dhcpOptionNTP,
	 * dhcpOptionDNS, dhcpOptionDomainPrefix, dhcpOptionSMB;
	 * 
	 * Set dhcpStatements attribute from dhcpStatementsAuthoritative,
	 * dhcpStatementsLeaseTime, dhcpStatementsMaxLeaseTime from dhcpStatements;
	 */
	public void set() {
		String[] dhcpOption = null;
		if (Strings.isNotBlank(dhcpOptionGateway))
			dhcpOption = (String[]) ArrayUtils.add(dhcpOption, "routers " + dhcpOptionGateway);
		if (Strings.isNotBlank(dhcpOptionNTP))
			dhcpOption = (String[]) ArrayUtils.add(dhcpOption, "ntp-servers " + dhcpOptionNTP);
		if (Strings.isNotBlank(dhcpOptionDNS))
			dhcpOption = (String[]) ArrayUtils.add(dhcpOption, "domain-name-servers " + dhcpOptionDNS);
		if (Strings.isNotBlank(dhcpOptionDomainPrefix))
			dhcpOption = (String[]) ArrayUtils.add(dhcpOption, "domain-name \"" + dhcpOptionDomainPrefix + "\"");
		if (Strings.isNotBlank(dhcpOptionSMB))
			dhcpOption = (String[]) ArrayUtils.add(dhcpOption, "netbios-name-servers " + dhcpOptionSMB);
		if (Strings.isNotBlank(dhcpOptionSMBtype))
			dhcpOption = (String[]) ArrayUtils.add(dhcpOption, "netbios-node-type " + dhcpOptionSMBtype);
		if (dhcpOption == null && this.dhcpOption != null)
			removeAttribute("dhcpOption");
		this.dhcpOption = dhcpOption;

		String[] dhcpStatements = null;
		if (dhcpStatementsAuthoritative != null && dhcpStatementsAuthoritative) {
			dhcpStatements = (String[]) ArrayUtils.add(dhcpStatements, "authoritative");
			dhcpStatements = (String[]) ArrayUtils.add(dhcpStatements, "ddns-update-style none");
		}
		if (Strings.isNotBlank(dhcpStatementsLeaseTime))
			dhcpStatements = (String[]) ArrayUtils.add(dhcpStatements, "default-lease-time " + dhcpStatementsLeaseTime);
		if (Strings.isNotBlank(dhcpStatementsMaxLeaseTime))
			dhcpStatements = (String[]) ArrayUtils.add(dhcpStatements, "max-lease-time " + dhcpStatementsMaxLeaseTime);
		if (dhcpStatements == null && this.dhcpStatements != null)
			removeAttribute("dhcpStatements");
		this.dhcpStatements = dhcpStatements;

	}

	public String getDhcpGateway() {
		if (dhcpOptionGateway == null)
			load();
		return dhcpOptionGateway;
	}

	public void setDhcpGateway(String dhcpOptionGateway) {
		if (Strings.isBlank(dhcpOptionGateway))
			this.dhcpOptionGateway = null;
		else
			this.dhcpOptionGateway = dhcpOptionGateway;
	}

	public String getDhcpOptionDNS() {
		if (dhcpOptionDNS == null)
			load();
		return dhcpOptionDNS;
	}

	public void setDhcpOptionDNS(String dhcpOptionDNS) {
		if (Strings.isBlank(dhcpOptionDNS))
			this.dhcpOptionDNS = null;
		else
			this.dhcpOptionDNS = dhcpOptionDNS;
	}

	public String getDhcpOptionNTP() {
		if (dhcpOptionNTP == null)
			load();
		return dhcpOptionNTP;
	}

	public void setDhcpOptionNTP(String dhcpOptionNTP) {
		if (Strings.isBlank(dhcpOptionNTP))
			this.dhcpOptionNTP = null;
		else
			this.dhcpOptionNTP = dhcpOptionNTP;
	}

	public String getDhcpOptionDomainPrefix() {
		if (dhcpOptionDomainPrefix == null)
			load();
		return dhcpOptionDomainPrefix;
	}

	public void setDhcpOptionDomainPrefix(String dhcpOptionDomainPrefix) {
		if (Strings.isBlank(dhcpOptionDomainPrefix))
			this.dhcpOptionDomainPrefix = null;
		else
			this.dhcpOptionDomainPrefix = dhcpOptionDomainPrefix;
	}

	public String getDhcpOptionSMB() {
		if (dhcpOptionSMB == null)
			load();
		return dhcpOptionSMB;
	}

	public void setDhcpOptionSMB(String dhcpOptionSMB) {
		if (Strings.isBlank(dhcpOptionSMB))
			this.dhcpOptionSMB = null;
		else
			this.dhcpOptionSMB = dhcpOptionSMB;
	}

	public String getDhcpOptionSMBtype() {
		if (dhcpOptionSMBtype == null)
			load();
		return dhcpOptionSMBtype;
	}

	public void setDhcpOptionSMBtype(String dhcpOptionSMBtype) {
		if (Strings.isBlank(dhcpOptionSMBtype))
			this.dhcpOptionSMBtype = null;
		else
			this.dhcpOptionSMBtype = dhcpOptionSMBtype;
	}

	public boolean isDhcpStatementsAuthoritative() {
		if (dhcpStatementsAuthoritative == null) {
			dhcpStatementsAuthoritative = false;
			load();
		}
		return dhcpStatementsAuthoritative;
	}

	public void setDhcpStatementsAuthoritative(boolean dhcpStatementsAuthoritative) {
		this.dhcpStatementsAuthoritative = dhcpStatementsAuthoritative;
	}

	public String getDhcpStatementsLeaseTime() {
		if (dhcpStatementsLeaseTime == null)
			load();
		return dhcpStatementsLeaseTime;
	}

	public void setDhcpStatementsLeaseTime(String dhcpStatementsLeaseTime) {
		if (Strings.isBlank(dhcpStatementsLeaseTime))
			this.dhcpStatementsLeaseTime = null;
		else
			this.dhcpStatementsLeaseTime = dhcpStatementsLeaseTime;
	}

	public String getDhcpStatementsMaxLeaseTime() {
		if (dhcpStatementsMaxLeaseTime == null)
			load();
		return dhcpStatementsMaxLeaseTime;
	}

	public void setDhcpStatementsMaxLeaseTime(String dhcpStatementsMaxLeaseTime) {
		if (Strings.isBlank(dhcpStatementsMaxLeaseTime))
			this.dhcpStatementsMaxLeaseTime = null;
		else
			this.dhcpStatementsMaxLeaseTime = dhcpStatementsMaxLeaseTime;
	}

}

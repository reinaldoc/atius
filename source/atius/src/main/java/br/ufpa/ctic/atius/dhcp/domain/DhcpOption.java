package br.ufpa.ctic.atius.dhcp.domain;

import org.apache.commons.lang.ArrayUtils;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.gov.frameworkdemoiselle.ldap.template.Entry;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

public class DhcpOption extends Entry {

	private String[] dhcpOption;

	@Ignore
	private boolean dhcpOptionLoaded;

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
		dhcpOptionLoaded = false;
	}

	public DhcpOption(boolean skipObjectClass) {
		super(skipObjectClass);
		dhcpOptionLoaded = false;
	}

	@Override
	protected String[] objectClass() {
		return null;
	}

	public void getDhcpOptions() {
		if (dhcpOption != null && !dhcpOptionLoaded) {
			dhcpOptionLoaded = true;
			for (String option : dhcpOption) {
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
			}
		}
	}

	public void setDhcpOptions() {
		dhcpOption = null;
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
	}

	public String getDhcpGateway() {
		if (dhcpOptionGateway == null)
			getDhcpOptions();
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
			getDhcpOptions();
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
			getDhcpOptions();
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
			getDhcpOptions();
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
			getDhcpOptions();
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
			getDhcpOptions();
		return dhcpOptionSMBtype;
	}

	public void setDhcpOptionSMBtype(String dhcpOptionSMBtype) {
		if (Strings.isBlank(dhcpOptionSMBtype))
			this.dhcpOptionSMBtype = null;
		else
			this.dhcpOptionSMBtype = dhcpOptionSMBtype;
	}

}

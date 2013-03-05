package br.com.atius.dhcp.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

public class DhcpHost extends DhcpOptions {

	@Id
	private String cn;

	private String dhcpHWAddress;

	private String dhcpStatements;

	private String dhcpComments;

	public DhcpHost() {
		super();
	}

	public DhcpHost(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "dhcpHost" };
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getDhcpHWAddress() {
		if (dhcpHWAddress != null)
			return Strings.substringAfter(dhcpHWAddress, " ");
		return dhcpHWAddress;
	}

	public void setDhcpHWAddress(String dhcpHWAddress) {
		if (Strings.isNotBlank(dhcpHWAddress))
			dhcpHWAddress = "ethernet " + dhcpHWAddress;
		this.dhcpHWAddress = dhcpHWAddress;
	}

	public String getDhcpStatements() {
		if (dhcpStatements != null)
			return Strings.substringAfter(dhcpStatements, " ");
		return dhcpStatements;
	}

	public void setDhcpStatements(String dhcpStatements) {
		if (Strings.isNotBlank(dhcpStatements))
			dhcpStatements = "fixed-address " + dhcpStatements;
		this.dhcpStatements = dhcpStatements;
	}

	public String getDhcpComments() {
		return dhcpComments;
	}

	public void setDhcpComments(String dhcpComments) {
		this.dhcpComments = dhcpComments;
	}

}

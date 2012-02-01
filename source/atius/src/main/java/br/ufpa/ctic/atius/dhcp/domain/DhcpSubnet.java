package br.ufpa.ctic.atius.dhcp.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class DhcpSubnet extends Entry {

	@Id
	private String cn;

	private String[] dhcpOption;

	private String dhcpNetMask;

	private String dhcpComments;

	public DhcpSubnet() {
		super();
	}

	public DhcpSubnet(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "dhcpSubnet" };
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String[] getDhcpOption() {
		return dhcpOption;
	}

	public void setDhcpOption(String[] dhcpOption) {
		this.dhcpOption = dhcpOption;
	}

	public String getDhcpNetMask() {
		return dhcpNetMask;
	}

	public void setDhcpNetMask(String dhcpNetMask) {
		this.dhcpNetMask = dhcpNetMask;
	}

	public String getDhcpComments() {
		return dhcpComments;
	}

	public void setDhcpComments(String dhcpComments) {
		this.dhcpComments = dhcpComments;
	}

}

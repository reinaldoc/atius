package br.ufpa.ctic.atius.dhcp.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class DhcpSubnet extends Entry {

	@Id
	private String cn;

	private String[] dhcpOption;

	private String dhcpNetMask;

	private String dhcpRange;

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

	public String getDhcpGateway() {
		if (dhcpOption != null)
			for (String option : dhcpOption)
				if (option.contains("routers"))
					return option.split(" ")[1];
		return null;
	}

	public void setDhcpGateway(String gateway) {
		// if (dhcpOption == null)
		dhcpOption = new String[] { "routers " + gateway };
	}

	public String getDhcpNetMask() {
		return dhcpNetMask;
	}

	public void setDhcpNetMask(String dhcpNetMask) {
		this.dhcpNetMask = dhcpNetMask;
	}

	public String getDhcpRange() {
		return dhcpRange;
	}

	public void setDhcpRange(String dhcpRange) {
		this.dhcpRange = dhcpRange;
	}

	public String getDhcpComments() {
		return dhcpComments;
	}

	public void setDhcpComments(String dhcpComments) {
		this.dhcpComments = dhcpComments;
	}

}

package br.ufpa.ctic.atius.dhcp.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class DhcpHost extends Entry {

	@Id
	private String cn;

	private String dhcpHWAddress;

	private String dhcpStatements;

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
		return dhcpHWAddress;
	}

	public void setDhcpHWAddress(String dhcpHWAddress) {
		this.dhcpHWAddress = dhcpHWAddress;
	}

	public String getDhcpStatements() {
		return dhcpStatements;
	}

	public void setDhcpStatements(String dhcpStatements) {
		this.dhcpStatements = dhcpStatements;
	}

}

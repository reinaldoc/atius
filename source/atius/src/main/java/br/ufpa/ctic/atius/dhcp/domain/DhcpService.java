package br.ufpa.ctic.atius.dhcp.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class DhcpService extends Entry {

	@Id
	private String cn;

	private String dhcpPrimaryDN;

	private String[] dhcpOption;

	private String[] dhcpStatements;

	public DhcpService() {
		super();
	}

	public DhcpService(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "dhcpService", "dhcpOptions" };
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

	public String[] getDhcpStatements() {
		return dhcpStatements;
	}

	public void setDhcpStatements(String[] dhcpStatements) {
		this.dhcpStatements = dhcpStatements;
	}

}

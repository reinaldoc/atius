package br.ufpa.ctic.atius.dhcp.domain;

import javax.validation.constraints.Size;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class DhcpSharedNetwork extends Entry {

	@Id
	@Size(min = 3, message = "Identifique melhor a rede")
	private String cn;

	private String[] dhcpOption;

	public DhcpSharedNetwork() {
		super();
	}

	public DhcpSharedNetwork(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "dhcpSharedNetwork" };
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

}

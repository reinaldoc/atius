package br.com.atius.dhcp.domain;

import javax.validation.constraints.Size;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;

public class DhcpSharedNetwork extends DhcpOption {

	@Id
	@Size(min = 3, message = "Identifique melhor a rede")
	private String cn;

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

}

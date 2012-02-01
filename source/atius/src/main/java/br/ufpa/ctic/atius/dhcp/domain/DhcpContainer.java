package br.ufpa.ctic.atius.dhcp.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class DhcpContainer extends Entry {

	@Id
	private String cn;

	private String dhcpPrimaryServer;

	public DhcpContainer() {
		super();
	}

	public DhcpContainer(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "dhcpContainer" };
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getDhcpPrimaryServer() {
		return dhcpPrimaryServer;
	}

	public void setDhcpPrimaryServer(String dhcpPrimaryServer) {
		this.dhcpPrimaryServer = dhcpPrimaryServer;
	}

}

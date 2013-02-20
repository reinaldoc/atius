package br.com.atius.dhcp.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class DhcpServer extends Entry {

	@Id
	private String cn;

	private String dhcpServiceDN;

	public DhcpServer() {
		super();
	}

	public DhcpServer(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "dhcpServer" };
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getDhcpServiceDN() {
		return dhcpServiceDN;
	}

	public void setDhcpServiceDN(String dhcpServiceDN) {
		this.dhcpServiceDN = dhcpServiceDN;
	}

}

package br.com.atius.dhcp.domain;

import javax.validation.constraints.Size;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.gov.frameworkdemoiselle.ldap.annotation.Id;

public class DhcpService extends DhcpOption {

	@Id
	private String cn;

	private String dhcpPrimaryDN;

	@Ignore
	@Size(min = 3, max = 256, message = "Especifique melhor o nome do servidor")
	private String dhcpServerName;

	public DhcpService() {
		super();
	}

	public DhcpService(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	@Override
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

	public String getDhcpServerName() {
		return dhcpServerName;
	}

	public void setDhcpServerName(String dhcpServerName) {
		this.dhcpServerName = dhcpServerName;
	}

}

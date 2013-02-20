package br.com.atius.dhcp.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class DhcpGroup extends Entry {

	@Id
	private String cn;

	private String dhcpComments;

	public DhcpGroup() {
		super();
	}

	public DhcpGroup(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "dhcpGroup" };
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getDhcpComments() {
		return dhcpComments;
	}

	public void setDhcpComments(String dhcpComments) {
		this.dhcpComments = dhcpComments;
	}

}

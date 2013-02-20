package br.com.atius.dhcp.common;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource = "atius", prefix = "dhcp")
public class DhcpConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Name("basedn")
	private String dhcpContainerDN;

	public String getDhcpContainerDN() {
		return dhcpContainerDN;
	}

}

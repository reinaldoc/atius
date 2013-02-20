package br.com.atius.dhcp.business;

import javax.inject.Inject;

import br.com.atius.dhcp.common.DhcpSessionInfo;
import br.com.atius.dhcp.domain.DhcpHost;
import br.com.atius.dhcp.domain.DhcpServer;
import br.com.atius.dhcp.domain.DhcpSharedNetwork;
import br.com.atius.dhcp.domain.DhcpSubnet;
import br.com.atius.dhcp.persistence.DhcpHostDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;

@BusinessController
public class DhcpHostBC extends DelegateCrud<DhcpHost, String, DhcpHostDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSessionInfo sessionInfo;

	public DhcpServer getDhcpServer() {
		return sessionInfo.getDhcpServer();
	}

	public DhcpSharedNetwork getDhcpSharedNetwork() {
		return sessionInfo.getDhcpSharedNetwork();
	}

	public DhcpSubnet getDhcpSubnet() {
		return sessionInfo.getDhcpSubnet();
	}

}

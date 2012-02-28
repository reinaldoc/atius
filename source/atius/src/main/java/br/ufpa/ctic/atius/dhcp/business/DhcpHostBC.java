package br.ufpa.ctic.atius.dhcp.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.domain.DhcpHost;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSubnet;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpHostDAO;
import br.ufpa.ctic.atius.dhcp.view.app.DhcpSessionInfo;

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

package br.ufpa.ctic.atius.dhcp.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.common.DhcpSessionInfo;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;
import br.ufpa.ctic.atius.dhcp.domain.DhcpService;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpServiceDAO;

@BusinessController
public class DhcpServiceBC extends DelegateCrud<DhcpService, String, DhcpServiceDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSessionInfo sessionInfo;

	@Inject
	private DhcpServerBC dhcpServerBC;

	public DhcpService getDhcpService(DhcpServer dhcpServer) {
		if (dhcpServer == null || dhcpServer.getDhcpServiceDN() == null)
			return new DhcpService();
		getQueryConfig().setGeneric(dhcpServer.getDhcpServiceDN());
		List<DhcpService> dhcpServices = findAll();
		if (dhcpServices.size() > 0)
			return dhcpServices.get(0);
		return new DhcpService();
	}

	public DhcpService getDhcpService() {
		return sessionInfo.getDhcpService();
	}

	public DhcpServer getDhcpServer() {
		return sessionInfo.getDhcpServer();
	}

	public DhcpServer insertDhcpServer(String serverName) {
		DhcpServer dhcpServer = new DhcpServer();
		dhcpServer.setDn("cn=" + serverName + "," + dhcpServerBC.getDhcpContainerDN());
		dhcpServer.setCn(serverName);
		dhcpServer.setDhcpServiceDN("cn=dhcpService," + dhcpServer.getDn());
		dhcpServerBC.insert(dhcpServer);
		return dhcpServer;
	}

}

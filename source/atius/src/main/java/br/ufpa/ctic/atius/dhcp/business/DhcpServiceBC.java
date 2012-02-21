package br.ufpa.ctic.atius.dhcp.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;
import br.ufpa.ctic.atius.dhcp.domain.DhcpService;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpServiceDAO;
import br.ufpa.ctic.atius.dhcp.view.app.DhcpSessionInfo;

@BusinessController
public class DhcpServiceBC extends DelegateCrud<DhcpService, String, DhcpServiceDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSessionInfo sessionInfo;

	public DhcpService getDhcpService(DhcpServer dhcpServer) {
		getQueryConfig().setGeneric(dhcpServer.getDhcpServiceDN());
		List<DhcpService> dhcpServices = findAll();
		if (dhcpServices.size() > 0)
			return dhcpServices.get(0);
		return null;
	}

	public DhcpService getDhcpService() {
		return sessionInfo.getDhcpService();
	}

}

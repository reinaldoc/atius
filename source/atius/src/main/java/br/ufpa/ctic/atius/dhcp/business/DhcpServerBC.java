package br.ufpa.ctic.atius.dhcp.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.configuration.DhcpConfig;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpServerDAO;
import br.ufpa.ctic.atius.dhcp.view.app.DhcpSessionInfo;

@BusinessController
public class DhcpServerBC extends DelegateCrud<DhcpServer, String, DhcpServerDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpConfig dhcpConfig;

	@Inject
	private DhcpSessionInfo sessionInfo;

	@Inject
	private DhcpContainerBC dhcpContainerBC;

	public DhcpServer getPrimaryDhcpServer() {
		String primaryDhcpServer = dhcpContainerBC.getPrimaryDhcpServer();
		if (primaryDhcpServer == null)
			return new DhcpServer();
		getQueryConfig().setGeneric(dhcpConfig.getDhcpContainerDN());
		return load(primaryDhcpServer);
	}

	public String getDhcpContainerDN() {
		return dhcpConfig.getDhcpContainerDN();
	}

	public DhcpServer getDhcpServer() {
		return sessionInfo.getDhcpServer();
	}

	public void selectDhcpServer(DhcpServer dhcpServer) {
		sessionInfo.selectDhcpServer(dhcpServer);
	}

	public void deleteDhcpService(String dhcpServiceDN) {
		getDelegate().remove(dhcpServiceDN);
	}

}

package br.ufpa.ctic.atius.dhcp.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.configuration.DhcpConfig;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpServerDAO;

@BusinessController
public class DhcpServerBC extends DelegateCrud<DhcpServer, String, DhcpServerDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpConfig dhcpConfig;

	@Inject
	private DhcpContainerBC dhcpContainerBC;

	public DhcpServer getDefaultDhcpServer() {
		getQueryConfig().setGeneric(dhcpConfig.getDhcpContainerDN());
		return load(dhcpContainerBC.getPrimaryDhcpServer());
	}

}

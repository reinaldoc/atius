package br.ufpa.ctic.atius.dhcp.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.common.DhcpSessionInfo;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpSharedNetworkDAO;

@BusinessController
public class DhcpSharedNetworkBC extends DelegateCrud<DhcpSharedNetwork, String, DhcpSharedNetworkDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSessionInfo sessionInfo;

	public void selectDhcpSharedNetwork(DhcpSharedNetwork dhcpSharedNetwork) {
		sessionInfo.selectDhcpSharedNetwork(dhcpSharedNetwork);
	}

	public String getDhcpServiceDN() {
		return sessionInfo.getDhcpService().getDn();
	}

}

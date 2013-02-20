package br.com.atius.dhcp.business;

import javax.inject.Inject;

import br.com.atius.dhcp.common.DhcpSessionInfo;
import br.com.atius.dhcp.domain.DhcpSharedNetwork;
import br.com.atius.dhcp.persistence.DhcpSharedNetworkDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;

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

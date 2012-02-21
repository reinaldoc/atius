package br.ufpa.ctic.atius.dhcp.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpSharedNetworkDAO;
import br.ufpa.ctic.atius.dhcp.view.app.DhcpSessionInfo;

@BusinessController
public class DhcpSharedNetworkBC extends DelegateCrud<DhcpSharedNetwork, String, DhcpSharedNetworkDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSessionInfo sessionInfo;

	public List<DhcpSharedNetwork> findAll() {
		getQueryConfig().setGeneric(sessionInfo.getDhcpServiceDN());
		return getDelegate().findAll();
	}

	public void selectDhcpSharedNetwork(DhcpSharedNetwork dhcpSharedNetwork) {
		selectMenu(dhcpSharedNetwork.getCn());
		sessionInfo.selectDhcpSharedNetwork(dhcpSharedNetwork);
	}

	public String getDhcpServiceDN() {
		return sessionInfo.getDhcpServiceDN();
	}

}

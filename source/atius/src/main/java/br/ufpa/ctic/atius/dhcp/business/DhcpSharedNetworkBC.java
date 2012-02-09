package br.ufpa.ctic.atius.dhcp.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.gov.frameworkdemoiselle.util.core.MenuContext;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpSharedNetworkDAO;
import br.ufpa.ctic.atius.dhcp.view.app.DhcpSessionInfo;

@BusinessController
public class DhcpSharedNetworkBC extends DelegateCrud<DhcpSharedNetwork, String, DhcpSharedNetworkDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MenuContext menuContext;

	@Inject
	private DhcpSessionInfo sessionInfo;

	public void selectMenu(String itemName) {
		menuContext.select("MenuDhcp", itemName);
	}

	public List<DhcpSharedNetwork> findAll() {
		getQueryConfig().setGeneric(sessionInfo.getDhcpServiceDN());
		return getDelegate().findAll();
	}

}

package br.ufpa.ctic.atius.dhcp.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.common.DhcpSessionInfo;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSubnet;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpSubnetDAO;

@BusinessController
public class DhcpSubnetBC extends DelegateCrud<DhcpSubnet, String, DhcpSubnetDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSessionInfo sessionInfo;

	public String getDhcpSharedNetworkDN() {
		return sessionInfo.getDhcpSharedNetwork().getDn();
	}

	public void selectDhcpSubnet(DhcpSubnet dhcpSubnet) {
		sessionInfo.selectDhcpSubnet(dhcpSubnet);
	}

}

package br.com.atius.dhcp.business;

import javax.inject.Inject;

import br.com.atius.dhcp.common.DhcpSessionInfo;
import br.com.atius.dhcp.domain.DhcpSubnet;
import br.com.atius.dhcp.persistence.DhcpSubnetDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;

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

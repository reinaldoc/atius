package br.ufpa.ctic.atius.dhcp.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.domain.DhcpHost;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpHostDAO;
import br.ufpa.ctic.atius.dhcp.view.app.DhcpSessionInfo;

@BusinessController
public class DhcpHostBC extends DelegateCrud<DhcpHost, String, DhcpHostDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSessionInfo sessionInfo;

	public String getDhcpSubnetDN() {
		return sessionInfo.getDhcpSubnetDN();
	}

}

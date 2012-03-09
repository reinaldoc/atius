package br.ufpa.ctic.atius.dhcp.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.domain.DhcpOption;
import br.ufpa.ctic.atius.dhcp.domain.DhcpService;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpOptionDAO;
import br.ufpa.ctic.atius.dhcp.view.app.DhcpSessionInfo;

@BusinessController
public class DhcpOptionBC extends DelegateCrud<DhcpOption, String, DhcpOptionDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSessionInfo sessionInfo;

	public DhcpService getDhcpService() {
		return sessionInfo.getDhcpService();
	}

}
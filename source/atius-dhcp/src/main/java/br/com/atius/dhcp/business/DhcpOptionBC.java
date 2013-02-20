package br.com.atius.dhcp.business;

import javax.inject.Inject;

import br.com.atius.dhcp.common.DhcpSessionInfo;
import br.com.atius.dhcp.domain.DhcpOption;
import br.com.atius.dhcp.domain.DhcpServer;
import br.com.atius.dhcp.persistence.DhcpOptionDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;

@BusinessController
public class DhcpOptionBC extends DelegateCrud<DhcpOption, String, DhcpOptionDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSessionInfo sessionInfo;

	public DhcpServer getDhcpServer() {
		return sessionInfo.getDhcpServer();
	}

}
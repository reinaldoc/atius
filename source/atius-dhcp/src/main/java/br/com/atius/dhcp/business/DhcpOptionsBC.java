package br.com.atius.dhcp.business;

import javax.inject.Inject;

import br.com.atius.dhcp.common.DhcpSessionInfo;
import br.com.atius.dhcp.domain.DhcpOptions;
import br.com.atius.dhcp.domain.DhcpServer;
import br.com.atius.dhcp.persistence.DhcpOptionsDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;

@BusinessController
public class DhcpOptionsBC extends DelegateCrud<DhcpOptions, String, DhcpOptionsDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSessionInfo sessionInfo;

	public DhcpServer getDhcpServer() {
		return sessionInfo.getDhcpServer();
	}

}
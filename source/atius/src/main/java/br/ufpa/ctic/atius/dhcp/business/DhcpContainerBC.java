package br.ufpa.ctic.atius.dhcp.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.configuration.DhcpConfig;
import br.ufpa.ctic.atius.dhcp.domain.DhcpContainer;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpContainerDAO;

@BusinessController
public class DhcpContainerBC extends DelegateCrud<DhcpContainer, String, DhcpContainerDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpConfig dhcpConfig;

	public String getPrimaryDhcpServer() {
		getQueryConfig().setGeneric(dhcpConfig.getDhcpContainerDN());
		try {
			return findAll().get(0).getDhcpPrimaryServer();
		} catch (Exception e) {
			return null;
		}
	}

}

package br.ufpa.ctic.atius.dhcp.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.gov.frameworkdemoiselle.util.contrib.Strings;
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
		List<DhcpContainer> dhcpContainers = findAll();
		if (dhcpContainers.size() == 0) {
			DhcpContainer dhcpContainer = new DhcpContainer();
			dhcpContainer.setDn(dhcpConfig.getDhcpContainerDN());
			dhcpContainer.setCn(Strings.substringAfter(Strings.substringBefore(dhcpConfig.getDhcpContainerDN(), ","), "="));
			try {
				insert(dhcpContainer);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			return null;
		}
		return findAll().get(0).getDhcpPrimaryServer();
	}

	public void selectPrimaryDhcpServer(String dhcpServerName) {
		getQueryConfig().setGeneric(dhcpConfig.getDhcpContainerDN());
		List<DhcpContainer> dhcpContainers = findAll();
		if (dhcpContainers.size() != 0) {
			dhcpContainers.get(0).setDhcpPrimaryServer(dhcpServerName);
			update(dhcpContainers.get(0));
		}
	}
	
}

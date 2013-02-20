package br.com.atius.dhcp.view.list;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.dhcp.business.DhcpServerBC;
import br.com.atius.dhcp.domain.DhcpServer;
import br.com.atius.dhcp.view.edit.DhcpServiceEditMB;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@ViewController
public class DhcpServerListMB extends AbstractListPageBean<DhcpServer, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpServerBC bc;

	@Override
	protected List<DhcpServer> handleResultList(QueryConfig<DhcpServer> queryConfig) {
		queryConfig.setGeneric(bc.getDhcpContainerDN());
		return bc.findAll();
	}

	public void selectDhcpServer(DhcpServer dhcpServer) {
		if (dhcpServer.equals(bc.getDhcpServer()))
			bc.selectPrimaryDhcpServer(dhcpServer.getCn());
		bc.selectDhcpServer(dhcpServer);
		Faces.getManagedProperty("#{dhcpSharedNetworkListMB}", DhcpSharedNetworkListMB.class).list();
		Faces.getManagedProperty("#{dhcpSharedNetworkListMB}", DhcpSharedNetworkListMB.class).init();
		Faces.getManagedProperty("#{dhcpServiceEditMB}", DhcpServiceEditMB.class).init();
		Faces.getManagedProperty("#{dhcpSubnetListMB}", DhcpSubnetListMB.class).list();
	}

}

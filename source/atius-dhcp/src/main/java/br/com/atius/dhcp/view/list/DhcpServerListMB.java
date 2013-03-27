package br.com.atius.dhcp.view.list;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.dhcp.business.DhcpContainerBC;
import br.com.atius.dhcp.business.DhcpServerBC;
import br.com.atius.dhcp.domain.DhcpServer;
import br.com.atius.dhcp.view.edit.DhcpServiceEditMB;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

@ViewController
public class DhcpServerListMB extends AbstractListPageBean<DhcpServer, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpServerBC bc;

	@Inject
	private DhcpContainerBC dhcpContainerBC;

	@Inject
	private DhcpSharedNetworkListMB dhcpSharedNetworkListMB;

	@Inject
	private DhcpServiceEditMB dhcpServiceEditMB;

	@Inject
	private DhcpSubnetListMB dhcpSubnetListMB;

	private String defaultDhcpServer;

	@Override
	protected List<DhcpServer> handleResultList(QueryConfig<DhcpServer> queryConfig) {
		queryConfig.setGeneric(bc.getDhcpContainerDN());
		return bc.findAll();
	}

	public void selectDhcpServer(DhcpServer dhcpServer) {
		bc.selectDhcpServer(dhcpServer);
		dhcpSharedNetworkListMB.list();
		dhcpSharedNetworkListMB.init();
		dhcpServiceEditMB.init();
		dhcpSubnetListMB.list();
	}

	public String getDefaultDhcpServer() {
		if (defaultDhcpServer == null)
			defaultDhcpServer = dhcpContainerBC.getPrimaryDhcpServer();
		return defaultDhcpServer;
	}

	public void setDefaultDhcpServer(String defaultDhcpServer) {
		this.defaultDhcpServer = defaultDhcpServer;
	}

	public void updateDefaultDhcpServer() {
		if (Strings.isBlank(defaultDhcpServer)) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.server.defaultserver.empty", SeverityType.ERROR);
			defaultDhcpServer = null;
			return;
		}

		try {
			bc.selectPrimaryDhcpServer(defaultDhcpServer);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.server.defaultserver.failed", SeverityType.ERROR);
			defaultDhcpServer = null;
		}

	}

}

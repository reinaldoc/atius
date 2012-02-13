package br.ufpa.ctic.atius.dhcp.view.app;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.ufpa.ctic.atius.dhcp.business.DhcpServerBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;

@SessionScoped
public class DhcpSessionInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpServerBC dhcpServerBC;

	private DhcpServer dhcpServer;

	private DhcpSharedNetwork dhcpSharedNetwork;

	public String getDhcpServiceDN() {
		if (dhcpServer == null) {
			dhcpServer = dhcpServerBC.getDefaultDhcpServer();
			if (dhcpServer != null)
				return dhcpServer.getDhcpServiceDN();
		}
		return null;
	}

	public String getDhcpServerName() {
		return dhcpServer.getCn();
	}

	public void selectDhcpServer(String dhcpServerName) {
		dhcpServer = dhcpServerBC.load(dhcpServerName);
	}

	public String getDhcpSharedNetworkDN() {
		if (dhcpSharedNetwork != null)
			return dhcpSharedNetwork.getDn();
		return null;
	}

	public void selectDhcpSharedNetwork(DhcpSharedNetwork dhcpSharedNetwork) {
		this.dhcpSharedNetwork = dhcpSharedNetwork;
	}

}

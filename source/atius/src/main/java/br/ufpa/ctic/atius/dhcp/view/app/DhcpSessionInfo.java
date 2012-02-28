package br.ufpa.ctic.atius.dhcp.view.app;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufpa.ctic.atius.dhcp.business.DhcpServerBC;
import br.ufpa.ctic.atius.dhcp.business.DhcpServiceBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;
import br.ufpa.ctic.atius.dhcp.domain.DhcpService;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSubnet;

@Named
@SessionScoped
public class DhcpSessionInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpServerBC dhcpServerBC;

	@Inject
	private DhcpServiceBC dhcpServiceBC;

	private DhcpServer dhcpServer;

	private DhcpService dhcpService;

	private DhcpSharedNetwork dhcpSharedNetwork;

	private DhcpSubnet dhcpSubnet;

	public DhcpServer getDhcpServer() {
		if (dhcpServer == null) {
			dhcpServer = dhcpServerBC.getPrimaryDhcpServer();
			dhcpService = dhcpServiceBC.getDhcpService(dhcpServer);
		}
		return dhcpServer;
	}

	public void selectDhcpServer(DhcpServer dhcpServer) {
		this.dhcpServer = dhcpServer;
		this.dhcpService = dhcpServiceBC.getDhcpService(dhcpServer);
		this.dhcpSharedNetwork = null;
		this.dhcpSubnet = null;
	}

	public DhcpService getDhcpService() {
		if (dhcpService == null)
			getDhcpServer();
		return dhcpService;
	}

	public DhcpSharedNetwork getDhcpSharedNetwork() {
		if (dhcpSharedNetwork == null)
			return new DhcpSharedNetwork();
		return dhcpSharedNetwork;
	}

	public void selectDhcpSharedNetwork(DhcpSharedNetwork dhcpSharedNetwork) {
		this.dhcpSharedNetwork = dhcpSharedNetwork;
	}

	public DhcpSubnet getDhcpSubnet() {
		if (dhcpSubnet == null)
			dhcpSubnet = new DhcpSubnet();
		return dhcpSubnet;
	}

	public void selectDhcpSubnet(DhcpSubnet dhcpSubnet) {
		this.dhcpSubnet = dhcpSubnet;
	}

}

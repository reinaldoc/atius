package br.ufpa.ctic.atius.dhcp.view.app;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.ufpa.ctic.atius.dhcp.business.DhcpServerBC;
import br.ufpa.ctic.atius.dhcp.business.DhcpServiceBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;
import br.ufpa.ctic.atius.dhcp.domain.DhcpService;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSubnet;

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

	public String getDhcpServiceDN() {
		if (dhcpServer == null) {
			dhcpServer = dhcpServerBC.getDefaultDhcpServer();
			if (dhcpServer != null) {
				dhcpService = dhcpServiceBC.getDhcpService(dhcpServer);
				return dhcpServer.getDhcpServiceDN();
			}
		} else
			return dhcpServer.getDhcpServiceDN();
		return null;
	}

	public String getDhcpServerName() {
		return dhcpServer.getCn();
	}

	public void selectDhcpServer(DhcpServer dhcpServer) {
		this.dhcpServer = dhcpServer;
		this.dhcpService = dhcpServiceBC.getDhcpService(dhcpServer);
	}

	public String getDhcpSharedNetworkDN() {
		if (dhcpSharedNetwork != null)
			return dhcpSharedNetwork.getDn();
		return null;
	}

	public void selectDhcpSharedNetwork(DhcpSharedNetwork dhcpSharedNetwork) {
		this.dhcpSharedNetwork = dhcpSharedNetwork;
	}

	public String getDhcpSubnetDN() {
		if (dhcpSubnet != null)
			return dhcpSubnet.getDn();
		return null;
	}

	public void selectDhcpSubnet(DhcpSubnet dhcpSubnet) {
		this.dhcpSubnet = dhcpSubnet;
	}

	public DhcpService getDhcpService() {
		return dhcpService;
	}

}

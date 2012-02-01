package br.ufpa.ctic.atius.dhcp.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpSharedNetworkDAO;

@BusinessController
public class DhcpSharedNetworkBC extends DelegateCrud<DhcpSharedNetwork, String, DhcpSharedNetworkDAO> {

	private static final long serialVersionUID = 1L;

}

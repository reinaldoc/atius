package br.ufpa.ctic.atius.dhcp.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpServerDAO;

@BusinessController
public class DhcpServerBC extends DelegateCrud<DhcpServer, String, DhcpServerDAO> {

	private static final long serialVersionUID = 1L;

}

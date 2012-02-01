package br.ufpa.ctic.atius.dhcp.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.dhcp.domain.DhcpContainer;
import br.ufpa.ctic.atius.dhcp.persistence.DhcpContainerDAO;

@BusinessController
public class DhcpContainerBC extends DelegateCrud<DhcpContainer, String, DhcpContainerDAO> {

	private static final long serialVersionUID = 1L;

}

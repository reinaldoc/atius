package br.com.atius.services.business;

import br.com.atius.services.domain.ServiceItem;
import br.com.atius.services.persistence.ServiceItemDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ServiceItemBC extends DelegateCrud<ServiceItem, Integer, ServiceItemDAO> {

	private static final long serialVersionUID = 1L;

}

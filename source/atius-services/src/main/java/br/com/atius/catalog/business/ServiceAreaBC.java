package br.com.atius.catalog.business;

import br.com.atius.catalog.domain.ServiceArea;
import br.com.atius.catalog.persistence.ServiceAreaDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ServiceAreaBC extends DelegateCrud<ServiceArea, Integer, ServiceAreaDAO> {

	private static final long serialVersionUID = 1L;

}

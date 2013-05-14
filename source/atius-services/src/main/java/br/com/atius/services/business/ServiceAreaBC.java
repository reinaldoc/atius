package br.com.atius.services.business;

import br.com.atius.services.domain.ServiceArea;
import br.com.atius.services.persistence.ServiceAreaDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ServiceAreaBC extends DelegateCrud<ServiceArea, Integer, ServiceAreaDAO> {

	private static final long serialVersionUID = 1L;

}

package br.com.atius.catalog.business;

import javax.inject.Inject;

import br.com.atius.catalog.domain.ServiceArea;
import br.com.atius.catalog.domain.ServiceGroup;
import br.com.atius.catalog.persistence.ServiceGroupDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ServiceGroupBC extends DelegateCrud<ServiceGroup, Integer, ServiceGroupDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceAreaBC serviceAreaBC;

	public ServiceArea loadArea(Integer id) {
		return serviceAreaBC.load(id);
	}

}

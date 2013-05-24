package br.com.atius.services.business;

import javax.inject.Inject;

import br.com.atius.services.domain.ServiceArea;
import br.com.atius.services.domain.ServiceGroup;
import br.com.atius.services.persistence.ServiceGroupDAO;
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

package br.com.atius.catalog.business;

import javax.inject.Inject;

import br.com.atius.catalog.domain.ServiceGroup;
import br.com.atius.catalog.domain.ServiceSubgroup;
import br.com.atius.catalog.persistence.ServiceSubgroupDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ServiceSubgroupBC extends DelegateCrud<ServiceSubgroup, Integer, ServiceSubgroupDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceGroupBC serviceGroupBC;

	public ServiceGroup loadGroup(Integer id) {
		return serviceGroupBC.load(id);
	}

}

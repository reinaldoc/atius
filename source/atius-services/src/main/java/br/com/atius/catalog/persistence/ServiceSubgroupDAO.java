package br.com.atius.catalog.persistence;

import br.com.atius.catalog.domain.ServiceSubgroup;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class ServiceSubgroupDAO extends JPACrud<ServiceSubgroup, Integer> {

	private static final long serialVersionUID = 1L;

}

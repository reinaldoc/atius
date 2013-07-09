package br.com.atius.catalog.persistence;

import br.com.atius.catalog.domain.ServiceGroup;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class ServiceGroupDAO extends JPACrud<ServiceGroup, Integer> {

	private static final long serialVersionUID = 1L;

}

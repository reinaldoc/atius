package br.com.atius.services.persistence;

import br.com.atius.services.domain.ServiceGroup;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class ServiceGroupDAO extends JPACrud<ServiceGroup, Integer> {

	private static final long serialVersionUID = 1L;

}

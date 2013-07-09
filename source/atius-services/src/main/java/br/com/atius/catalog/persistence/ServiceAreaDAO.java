package br.com.atius.catalog.persistence;

import br.com.atius.catalog.domain.ServiceArea;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class ServiceAreaDAO extends JPACrud<ServiceArea, Integer> {

	private static final long serialVersionUID = 1L;

}

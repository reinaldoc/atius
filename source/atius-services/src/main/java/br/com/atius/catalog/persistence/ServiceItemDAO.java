package br.com.atius.catalog.persistence;

import br.com.atius.catalog.domain.ServiceItem;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class ServiceItemDAO extends JPACrud<ServiceItem, Integer> {

	private static final long serialVersionUID = 1L;

}

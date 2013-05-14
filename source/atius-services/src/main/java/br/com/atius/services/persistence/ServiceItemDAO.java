package br.com.atius.services.persistence;

import br.com.atius.services.domain.ServiceItem;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class ServiceItemDAO extends JPACrud<ServiceItem, Integer> {

	private static final long serialVersionUID = 1L;

}

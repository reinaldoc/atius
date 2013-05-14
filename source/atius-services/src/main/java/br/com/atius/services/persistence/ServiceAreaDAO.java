package br.com.atius.services.persistence;

import br.com.atius.services.domain.ServiceArea;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class ServiceAreaDAO extends JPACrud<ServiceArea, Integer> {

	private static final long serialVersionUID = 1L;

}

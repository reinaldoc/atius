package br.com.atius.core.business;

import br.com.atius.core.domain.Repository;
import br.com.atius.core.persistence.RepositoryDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;

@BusinessController
public class RepositoryBC extends DelegateCrud<Repository, Integer, RepositoryDAO> {

	private static final long serialVersionUID = 1L;

}

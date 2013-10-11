package br.com.atius.core.persistence;

import br.com.atius.core.domain.Repository;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class RepositoryDAO extends JPACrud<Repository, Integer> {

	private static final long serialVersionUID = 1L;

}

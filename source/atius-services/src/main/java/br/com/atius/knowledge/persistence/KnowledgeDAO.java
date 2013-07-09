package br.com.atius.knowledge.persistence;

import br.com.atius.knowledge.domain.Knowledge;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class KnowledgeDAO extends JPACrud<Knowledge, Integer> {

	private static final long serialVersionUID = 1L;

}

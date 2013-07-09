package br.com.atius.knowledge.business;

import br.com.atius.knowledge.domain.Knowledge;
import br.com.atius.knowledge.persistence.KnowledgeDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class KnowledgeBC extends DelegateCrud<Knowledge, Integer, KnowledgeDAO> {

	private static final long serialVersionUID = 1L;

}

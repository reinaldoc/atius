package br.com.atius.knowledge.persistence;

import br.com.atius.knowledge.domain.Faq;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class FaqDAO extends JPACrud<Faq, Integer> {

	private static final long serialVersionUID = 1L;

}

package br.com.atius.knowledge.business;

import javax.inject.Inject;

import br.com.atius.catalog.business.ServiceItemBC;
import br.com.atius.catalog.domain.ServiceItem;
import br.com.atius.knowledge.domain.Faq;
import br.com.atius.knowledge.persistence.FaqDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class FaqBC extends DelegateCrud<Faq, Integer, FaqDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceItemBC serviceItemBC;

	public ServiceItem loadService(Integer id) {
		return serviceItemBC.load(id);
	}

}

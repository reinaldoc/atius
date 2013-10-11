package br.com.atius.catalog.business;

import java.util.List;

import br.com.atius.catalog.domain.ServiceItem;
import br.com.atius.catalog.persistence.ServiceItemDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ServiceItemBC extends DelegateCrud<ServiceItem, Integer, ServiceItemDAO> {

	private static final long serialVersionUID = 1L;

	public List<ServiceItem> search(String search) {
		return getDelegate().search(search);
	}

}

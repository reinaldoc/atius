package br.com.atius.catalog.view.list;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.catalog.business.ServiceItemBC;
import br.com.atius.catalog.common.SessionCatalog;
import br.com.atius.catalog.domain.ServiceItem;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;

@ViewController
public class ServiceItemListMB extends AbstractListPageBean<ServiceItem, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceItemBC bc;

	@Inject
	private SessionCatalog sessionCatalog;

	@Override
	protected List<ServiceItem> handleResultList(QueryConfig<ServiceItem> queryConfig) {
		return bc.search(sessionCatalog.getSearch());
	}

}

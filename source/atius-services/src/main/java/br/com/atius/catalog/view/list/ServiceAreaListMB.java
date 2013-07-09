package br.com.atius.catalog.view.list;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.catalog.business.ServiceAreaBC;
import br.com.atius.catalog.domain.ServiceArea;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;

@ViewController
public class ServiceAreaListMB extends AbstractListPageBean<ServiceArea, Integer> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceAreaBC bc;

	@Override
	protected List<ServiceArea> handleResultList(QueryConfig<ServiceArea> queryConfig) {
		return bc.findAll();
	}
	
}

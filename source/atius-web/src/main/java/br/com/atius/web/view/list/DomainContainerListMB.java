package br.com.atius.web.view.list;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.web.business.DomainContainerBC;
import br.com.atius.web.domain.DomainContainer;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;

@ViewController
public class DomainContainerListMB extends AbstractListPageBean<DomainContainer, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DomainContainerBC bc;

	@Override
	protected List<DomainContainer> handleResultList(QueryConfig<DomainContainer> queryConfig) {
		return bc.findAll();
	}

}

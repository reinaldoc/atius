package br.ufpa.ctic.atius.web.view.list;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.ufpa.ctic.atius.web.business.DomainContainerBC;
import br.ufpa.ctic.atius.web.domain.DomainContainer;

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

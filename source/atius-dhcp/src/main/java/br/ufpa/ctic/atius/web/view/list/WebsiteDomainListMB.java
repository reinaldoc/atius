package br.ufpa.ctic.atius.web.view.list;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.ufpa.ctic.atius.web.business.WebsiteDomainBC;
import br.ufpa.ctic.atius.web.domain.WebsiteDomain;

@ViewController
public class WebsiteDomainListMB extends AbstractListPageBean<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteDomainBC bc;

	@Override
	protected List<WebsiteDomain> handleResultList(QueryConfig<WebsiteDomain> queryConfig) {
		return bc.find(getResultFilter(), getMenuContext().getSelected("WebsiteCategory"));
	}

}

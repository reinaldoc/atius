package br.com.atius.web.view.list;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.web.business.WebsiteDomainBC;
import br.com.atius.web.domain.WebsiteDomain;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;

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

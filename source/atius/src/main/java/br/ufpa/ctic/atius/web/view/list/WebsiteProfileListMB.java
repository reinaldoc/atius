package br.ufpa.ctic.atius.web.view.list;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.ufpa.ctic.atius.web.business.WebsiteProfileBC;
import br.ufpa.ctic.atius.web.domain.WebsiteProfile;

@ViewController
public class WebsiteProfileListMB extends AbstractListPageBean<WebsiteProfile, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteProfileBC bc;

	@Override
	protected List<WebsiteProfile> handleResultList(QueryConfig<WebsiteProfile> queryConfig) {
		return bc.findAll();
	}

}

package br.com.atius.web.view.list;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.web.business.WebsiteProfileBC;
import br.com.atius.web.domain.WebsiteProfile;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;

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

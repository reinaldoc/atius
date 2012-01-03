package br.ufpa.ctic.atius.view.list;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.ufpa.ctic.atius.business.WebsiteDomainBC;
import br.ufpa.ctic.atius.domain.WebsiteDomain;

@ViewController
public class WebsiteDomainListMB extends AbstractListPageBean<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteDomainBC bc;

	@Override
	protected List<WebsiteDomain> handleResultList() {
		// TODO Auto-generated method stub
		return bc.findAll();
	}

}

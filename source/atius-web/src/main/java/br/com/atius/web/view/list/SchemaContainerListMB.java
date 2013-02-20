package br.com.atius.web.view.list;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.web.business.SchemaContainerBC;
import br.com.atius.web.domain.SchemaContainer;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;

@ViewController
public class SchemaContainerListMB extends AbstractListPageBean<SchemaContainer, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SchemaContainerBC bc;

	@Override
	protected List<SchemaContainer> handleResultList(QueryConfig<SchemaContainer> queryConfig) {
		return bc.findAll();
	}

}

package br.com.atius.knowledge.view.list;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.knowledge.business.KnowledgeBC;
import br.com.atius.knowledge.domain.Knowledge;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;

@ViewController
public class KnowledgeListMB extends AbstractListPageBean<Knowledge, Integer> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private KnowledgeBC bc;

	@Override
	protected List<Knowledge> handleResultList(QueryConfig<Knowledge> queryConfig) {
		return bc.findAll();
	}
	
}

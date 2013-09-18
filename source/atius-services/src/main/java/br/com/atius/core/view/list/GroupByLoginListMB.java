package br.com.atius.core.view.list;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.core.business.GroupBC;
import br.com.atius.core.domain.Group;
import br.gov.frameworkdemoiselle.fuselage.view.app.FuselageMB;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;

@ViewController
public class GroupByLoginListMB extends AbstractListPageBean<Group, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private GroupBC bc;

	@Inject
	private FuselageMB fuselage;

	@Override
	protected List<Group> handleResultList(QueryConfig<Group> queryConfig) {
		return bc.getGroupsByUserDn(fuselage.getDn());
	}

}

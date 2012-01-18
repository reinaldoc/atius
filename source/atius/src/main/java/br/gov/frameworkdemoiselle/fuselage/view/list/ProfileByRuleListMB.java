package br.gov.frameworkdemoiselle.fuselage.view.list;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.fuselage.business.ProfileByRuleBC;
import br.gov.frameworkdemoiselle.fuselage.configuration.ViewConfig;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityProfileByRule;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
public class ProfileByRuleListMB extends AbstractListPageBean<SecurityProfileByRule, Long> {
	private static final long serialVersionUID = 1L;

	@Inject
	private ViewConfig viewConfig;

	@Inject
	private ProfileByRuleBC bc;

	@PostConstruct
	public void init() {
		setLazyDataModelInitialSortAttribute("name");
	}

	@Override
	protected List<SecurityProfileByRule> handleResultList() {
		return bc.findAll();
	}

	@Transactional
	public String deleteSelection() {
		bc.delete(getSelectedList());
		clearSelection();
		return null;
	}

	public Map<String, String> getImplementations() {
		return viewConfig.getImplementations();
	}

}

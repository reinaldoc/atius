package br.gov.frameworkdemoiselle.fuselage.view.edit;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.fuselage.business.ResourceBC;
import br.gov.frameworkdemoiselle.fuselage.configuration.ViewConfig;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityResource;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

@ViewController
public class ResourceEditMB extends AbstractEditPageBean<SecurityResource, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ResourceBC bc;

	@Inject
	private ViewConfig viewConfig;

	@Override
	public String insert() {
		bc.insert(getBean());
		return null;
	}

	@Override
	public String update() {
		bc.update(getBean());
		return null;
	}

	@Override
	public String delete() {
		bc.delete(getBean().getId());
		return null;
	}

	@Override
	public SecurityResource load(Long id) {
		return null;
	}

	public List<String> names(String query) {
		return viewConfig.getNamesuggestions();
	}

}
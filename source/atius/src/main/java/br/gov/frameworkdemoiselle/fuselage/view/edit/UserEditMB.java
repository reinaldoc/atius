package br.gov.frameworkdemoiselle.fuselage.view.edit;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.fuselage.business.UserBC;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityUser;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

@ViewController
public class UserEditMB extends AbstractEditPageBean<SecurityUser, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserBC bc;

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
	
	public String userAvailable() {
		return null;
	}

}

package br.com.atius.core.view.edit;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.atius.core.business.OrganizationalPersonBC;
import br.com.atius.core.domain.OrganizationalPerson;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@SessionScoped
@ViewController
public class OrganizationalPersonEditMB extends AbstractEditPageBean<OrganizationalPerson, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrganizationalPersonBC bc;

	@Override
	public String insert() {
		try {
			bc.insert(getBean());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String update() {
		try {
			bc.update(getBean());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			bc.delete(getBean().getDn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public OrganizationalPerson load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return new OrganizationalPerson();
	}

}

package br.gov.frameworkdemoiselle.fuselage.view.edit;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.fuselage.business.ResourceBC;
import br.gov.frameworkdemoiselle.fuselage.configuration.ViewConfig;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityResource;
import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.Faces;

@ViewController
public class ResourceEditMB extends AbstractEditPageBean<SecurityResource, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ResourceBC bc;

	@Inject
	private ViewConfig viewConfig;

	@Override
	public String insert() {
		try {
			bc.insert(getBean());
			Faces.addMessage(bc.getBundle().getI18nMessage("fuselage.resource.insert.sucess", getBean().getName()));
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("fuselage.resource.insert.failed", SeverityType.ERROR));
		}
		return null;
	}

	@Override
	public String update() {
		try {
			bc.update(getBean());
			Faces.addMessage(bc.getBundle().getI18nMessage("fuselage.resource.update.sucess", getBean().getName()));
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("fuselage.resource.update.failed", SeverityType.ERROR));
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			bc.delete(getBean().getId());
			Faces.addMessage(bc.getBundle().getI18nMessage("fuselage.resource.delete.sucess", getBean().getName()));
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("fuselage.resource.delete.failed", SeverityType.ERROR));
		}
		return null;
	}

	@Override
	public SecurityResource load(Long id) {
		return bc.load(id);
	}

	public List<String> names(String query) {
		return viewConfig.getNamesuggestions();
	}

}
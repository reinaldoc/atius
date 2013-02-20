package br.ufpa.ctic.atius.web.view.edit;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.ufpa.ctic.atius.web.business.WebsiteCategoryBC;
import br.ufpa.ctic.atius.web.domain.WebsiteCategory;

@ViewController
public class WebsiteCategoryEditMB extends AbstractEditPageBean<WebsiteCategory, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteCategoryBC bc;

	public String delete() {
		try {
			bc.delete(getBean().getName());
			Faces.addI18nMessage("atius.web.category.delete.success", getBean().getName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.category.delete.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String insert() {
		try {
			bc.insert(getBean());
			Faces.addI18nMessage("atius.web.category.insert.success", getBean().getName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.category.insert.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String update() {
		try {
			bc.update(getBean());
			Faces.addI18nMessage("atius.web.category.update.success", getBean().getName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.category.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	protected WebsiteCategory load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.category.load.failed", SeverityType.ERROR);
		}
		return new WebsiteCategory();
	}

}

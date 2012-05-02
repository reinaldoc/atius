package br.ufpa.ctic.atius.web.view.edit;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.ufpa.ctic.atius.web.business.WebsiteProfileBC;
import br.ufpa.ctic.atius.web.domain.WebsiteProfile;

@ViewController
public class WebsiteProfileEditMB extends AbstractEditPageBean<WebsiteProfile, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteProfileBC bc;

	public String delete() {
		try {
			bc.delete(getBean().getName());
			Faces.addI18nMessage("atius.web.profile.delete.success", getBean().getName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.profile.delete.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String insert() {
		try {
			bc.insert(getBean());
			Faces.addI18nMessage("atius.web.profile.insert.success", getBean().getName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.profile.insert.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String update() {
		try {
			bc.update(getBean());
			Faces.addI18nMessage("atius.web.profile.update.success", getBean().getName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.profile.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	protected WebsiteProfile load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.profile.load.failed", SeverityType.ERROR);
		}
		return new WebsiteProfile();
	}

}

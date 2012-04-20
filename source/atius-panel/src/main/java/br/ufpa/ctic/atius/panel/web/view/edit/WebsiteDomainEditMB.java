package br.ufpa.ctic.atius.panel.web.view.edit;

import javax.inject.Inject;

import org.jasypt.util.password.rfc2307.RFC2307SSHAPasswordEncryptor;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.gov.frameworkdemoiselle.util.contrib.Strings;
import br.ufpa.ctic.atius.web.business.WebsiteDomainBC;
import br.ufpa.ctic.atius.web.domain.WebsiteDomain;

@ViewController
public class WebsiteDomainEditMB extends AbstractEditPageBean<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteDomainBC bc;

	public String delete() {
		return null;
	}

	public String insert() {
		return null;
	}

	public String update() {
		try {
			if (Strings.isNotBlank(getBean().getPassword()) || Strings.isNotBlank(getBean().getPasswordrepeat())) {
				if (Strings.isBlank(getBean().getPassword()) || getBean().getPassword().length() < 8) {
					Faces.validationFailed();
					Faces.addI18nMessage("fuselage.user.password.notstrong");
					return null;
				} else if (Strings.isNotBlank(getBean().getPassword()) && !getBean().getPassword().equals(getBean().getPasswordrepeat())) {
					Faces.validationFailed();
					Faces.addI18nMessage("fuselage.user.password.notmatch");
					return null;
				}
				RFC2307SSHAPasswordEncryptor encryptor = new RFC2307SSHAPasswordEncryptor();
				getBean().setUserPassword(encryptor.encryptPassword(getBean().getPassword()));
			}
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("fuselage.user.update.failed", SeverityType.ERROR);
			return null;
		}

		try {
			bc.update(getBean());
			Faces.addI18nMessage("atius.sites.websites.update.success", getBean().getServerName());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public WebsiteDomain load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.load.failed", SeverityType.ERROR);
		}
		return new WebsiteDomain();
	}

}

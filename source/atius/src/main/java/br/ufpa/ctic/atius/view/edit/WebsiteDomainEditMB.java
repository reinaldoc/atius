package br.ufpa.ctic.atius.view.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.Faces;
import br.gov.frameworkdemoiselle.util.FileRenderer;
import br.gov.frameworkdemoiselle.util.Parameter;
import br.gov.frameworkdemoiselle.util.Strings;
import br.ufpa.ctic.atius.business.WebsiteDomainBC;
import br.ufpa.ctic.atius.domain.InetOrgPerson;
import br.ufpa.ctic.atius.domain.WebsiteDomain;

@ViewController
public class WebsiteDomainEditMB extends AbstractEditPageBean<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteDomainBC bc;

	@Inject
	@Path("reports/WebsiteDomain.jasper")
	private Report report;

	@Inject
	private FileRenderer renderer;

	@Inject
	@RequestScoped
	private Parameter<String> id;

	public String delete() {
		try {
			bc.delete(getBean().getServerName());
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.delete.success", getBean().getServerName()));
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.delete.failed", SeverityType.ERROR));
		}
		return null;
	}

	public String disable() {
		try {
			bc.disable(getBean());
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.disable.success", getBean().getServerName()));
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.disable.failed", SeverityType.ERROR));
		}
		return null;
	}

	public String enable() {
		try {
			bc.enable(getBean());
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.enable.success", getBean().getServerName()));
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.enable.failed", SeverityType.ERROR));
		}
		return null;
	}

	public String insert() {
		try {
			if (!bc.domainAvailable(getBean().getServerName())) {
				Faces.validationFailed();
				Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.available.unavailable"));
				return null;
			}
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.available.failed", SeverityType.ERROR));
		}

		getBean().setValuesByServerName();
		try {
			bc.insert(getBean());
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.insert.success", getBean().getServerName()));
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.insert.failed", SeverityType.ERROR));
		}
		return null;
	}

	public String update() {
		try {
			bc.update(getBean());
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.update.success", getBean().getServerName()));
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.update.failed", SeverityType.ERROR));
		}
		return null;
	}

	public String print() {
		try {
			if (Strings.isNotBlank(id.getValue())) {
				WebsiteDomain websiteDomain = load(id.getValue());
				if (websiteDomain != null && Strings.isNotBlank(websiteDomain.getServerName())) {
					List<WebsiteDomain> beanReport = new ArrayList<WebsiteDomain>();
					beanReport.add(websiteDomain);
					byte[] buffer = report.export(beanReport, new HashMap<String, Object>(), Type.PDF);
					this.renderer.render(buffer, FileRenderer.ContentType.PDF, "websiteDomain.pdf");
				}
			}
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.report.failed", SeverityType.ERROR));
		}
		return null;
	}

	public List<InetOrgPerson> findPerson(String search) {
		try {
			return bc.findPerson(search);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.owner.failed", SeverityType.ERROR));
		}
		return new ArrayList<InetOrgPerson>();
	}

	public String domainAvailable() {
		try {
			if (bc.domainAvailable(getBean().getServerName()))
				Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.available.success", getBean().getServerName()));
			else {
				Faces.validationFailed();
				Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.available.unavailable"));
			}
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.available.failed", SeverityType.ERROR));
		}

		return null;
	}

	@Override
	public WebsiteDomain load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addMessage(bc.getBundle().getI18nMessage("atius.sites.websites.load.failed", SeverityType.ERROR));
		}
		return new WebsiteDomain();
	}

}

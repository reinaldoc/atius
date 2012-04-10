package br.ufpa.ctic.atius.web.view.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.FileRenderer;
import br.gov.frameworkdemoiselle.util.Parameter;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.gov.frameworkdemoiselle.util.contrib.Strings;
import br.ufpa.ctic.atius.web.business.WebsiteDomainBC;
import br.ufpa.ctic.atius.web.domain.InetOrgPerson;
import br.ufpa.ctic.atius.web.domain.WebsiteDomain;

@ViewController
public class WebsiteDomainEditMB extends AbstractEditPageBean<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteDomainBC bc;

	@Inject
	@Path("report/WebsiteDomain.jasper")
	private Report report;

	@Inject
	private FileRenderer renderer;

	@Inject
	@RequestScoped
	private Parameter<String> id;

	public String delete() {
		try {
			bc.delete(getBean().getServerName());
			Faces.addI18nMessage("atius.sites.websites.delete.success", getBean().getServerName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.delete.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String disable() {
		try {
			bc.disable(getBean());
			Faces.addI18nMessage("atius.sites.websites.disable.success", getBean().getServerName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.disable.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String enable() {
		try {
			bc.enable(getBean());
			Faces.addI18nMessage("atius.sites.websites.enable.success", getBean().getServerName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.enable.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String insert() {
		try {
			if (!bc.domainAvailable(getBean().getServerName())) {
				Faces.validationFailed();
				Faces.addI18nMessage("atius.sites.websites.available.unavailable", getBean().getServerName());
				return null;
			}
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.available.failed", SeverityType.ERROR);
			return null;
		}

		getBean().prepare();
		try {
			bc.insert(getBean());
			Faces.addI18nMessage("atius.sites.websites.insert.success", getBean().getServerName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.insert.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String update() {
		try {
			bc.update(getBean());
			Faces.addI18nMessage("atius.sites.websites.update.success", getBean().getServerName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String print() {
		try {
			if (Strings.isNotBlank(id.getValue())) {
				WebsiteDomain websiteDomain = load(id.getValue());
				if (websiteDomain != null && Strings.isNotBlank(websiteDomain.getServerName())) {
					FacesContext context = FacesContext.getCurrentInstance();
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("LOGO_LEFT", context.getExternalContext().getRealPath("WEB-INF/classes/report/images/ufpa_logo.jpg"));
					params.put("LOGO_RIGHT", context.getExternalContext().getRealPath("WEB-INF/classes/report/images/ctic_logo.gif"));

					List<WebsiteDomain> beanReport = new ArrayList<WebsiteDomain>();
					beanReport.add(websiteDomain);

					byte[] buffer = report.export(beanReport, params, Type.PDF);
					this.renderer.render(buffer, FileRenderer.ContentType.PDF, "websiteDomain.pdf");
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.report.failed", SeverityType.ERROR);
		}
		return null;
	}

	public List<InetOrgPerson> findPerson(String search) {
		try {
			return bc.findPerson(search);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.owner.failed", SeverityType.ERROR);
		}
		return new ArrayList<InetOrgPerson>();
	}

	public String domainAvailable() {
		try {
			if (bc.domainAvailable(getBean().getServerName()))
				Faces.addI18nMessage("atius.sites.websites.available.success", getBean().getServerName());
			else {
				Faces.validationFailed();
				Faces.addI18nMessage("atius.sites.websites.available.unavailable", getBean().getServerName());
			}
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.sites.websites.available.failed", SeverityType.ERROR);
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

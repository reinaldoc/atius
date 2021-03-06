package br.com.atius.web.view.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.atius.web.business.WebsiteDomainBC;
import br.com.atius.web.domain.InetOrgPerson;
import br.com.atius.web.domain.WebsiteDomain;
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
			Faces.addI18nMessage("atius.web.websites.delete.success", getBean().getServerName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.websites.delete.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String disable() {
		try {
			bc.disable(getBean());
			Faces.addI18nMessage("atius.web.websites.disable.success", getBean().getServerName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.websites.disable.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String enable() {
		try {
			bc.enable(getBean());
			Faces.addI18nMessage("atius.web.websites.enable.success", getBean().getServerName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.websites.enable.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String insert() {
		try {
			if (!bc.domainAvailable(getBean().getServerName())) {
				Faces.validationFailed();
				Faces.addI18nMessage("atius.web.websites.available.unavailable", getBean().getServerName());
				return null;
			}
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.websites.available.failed", SeverityType.ERROR);
			return null;
		}

		getBean().prepare();
		try {
			bc.insert(getBean());
			Faces.addI18nMessage("atius.web.websites.insert.success", getBean().getServerName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.websites.insert.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String update() {
		try {
			bc.update(getBean());
			Faces.addI18nMessage("atius.web.websites.update.success", getBean().getServerName());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.websites.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	public String print() {
		try {
			if (Strings.isNotBlank(id.getValue())) {
				WebsiteDomain websiteDomain = load(id.getValue());
				if (websiteDomain != null && Strings.isNotBlank(websiteDomain.getServerName())) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("LOGO_LEFT", Faces.getReportPath("images/ufpa_logo.jpg"));
					params.put("LOGO_RIGHT", Faces.getReportPath("images/ctic_logo.gif"));

					List<WebsiteDomain> beanReport = new ArrayList<WebsiteDomain>();
					beanReport.add(websiteDomain);

					byte[] buffer = report.export(beanReport, params, Type.PDF);
					this.renderer.render(buffer, FileRenderer.ContentType.PDF, "websiteDomain.pdf");
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.websites.report.failed", SeverityType.ERROR);
		}
		return null;
	}

	public List<InetOrgPerson> findPerson(String search) {
		try {
			return bc.findPerson(search);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.websites.owner.failed", SeverityType.ERROR);
		}
		return new ArrayList<InetOrgPerson>();
	}

	public String domainAvailable() {
		try {
			if (bc.domainAvailable(getBean().getServerName()))
				Faces.addI18nMessage("atius.web.websites.available.success", getBean().getServerName());
			else {
				Faces.validationFailed();
				Faces.addI18nMessage("atius.web.websites.available.unavailable", getBean().getServerName());
			}
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.websites.available.failed", SeverityType.ERROR);
		}

		return null;
	}

	@Override
	protected WebsiteDomain load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.web.websites.load.failed", SeverityType.ERROR);
		}
		return new WebsiteDomain();
	}

}

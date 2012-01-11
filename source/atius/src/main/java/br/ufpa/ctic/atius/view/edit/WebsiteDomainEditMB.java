package br.ufpa.ctic.atius.view.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.DefaultMessage;
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
	@Path("reports/WebsiteDomain.jrxml")
	private Report report;

	@Inject
	private FileRenderer renderer;

	@Inject
	@RequestScoped
	private Parameter<String> id;

	public String delete() {
		bc.delete(getBean().getServerName());
		Faces.addValidMessage(new DefaultMessage("Domínio " + getBean().getServerName() + " excluído com sucesso."));
		return null;
	}

	public String disable() {
		// bc.disable(getBean().getServerName());
		Faces.addValidMessage(new DefaultMessage("Domínio " + getBean().getServerName() + " desativado com sucesso."));
		return null;
	}

	public String enable() {
		// bc.enable(getBean().getServerName());
		Faces.addValidMessage(new DefaultMessage("Domínio " + getBean().getServerName() + " ativado com sucesso."));
		return null;
	}

	public String insert() {
		if (!bc.domainAvailable(getBean().getServerName())) {
			Faces.validationFailed(new DefaultMessage("Domínio indisponível, escolha outro."));
			return null;
		}
		bc.insert(getBean());
		return null;
	}

	public String update() {
		System.out.println("==========> update()");
		// bc.update(getBean());
		return null;
	}

	public String print() {
		if (Strings.isNotBlank(id.getValue())) {
			WebsiteDomain websiteDomain = load(id.getValue());
			if (websiteDomain != null && Strings.isNotBlank(websiteDomain.getServerName())) {
				List<WebsiteDomain> beanReport = new ArrayList<WebsiteDomain>();
				beanReport.add(websiteDomain);
				byte[] buffer = report.export(beanReport, new HashMap<String, Object>(), Type.PDF);
				this.renderer.render(buffer, FileRenderer.ContentType.PDF, "websiteDomain.pdf");
			}
		}
		return null;
	}

	public List<InetOrgPerson> findPerson(String search) {
		return bc.findPerson(search);
	}

	public String domainAvailable() {
		if (bc.domainAvailable(getBean().getServerName()))
			Faces.addMessage(new DefaultMessage("O domínio " + getBean().getServerName() + " está disponível"));
		else {
			Faces.validationFailed(new DefaultMessage("Domínio indisponível, escolha outro."));
		}
		return null;
	}

	@Override
	public WebsiteDomain load(String id) {
		return bc.load(id);
	}

}

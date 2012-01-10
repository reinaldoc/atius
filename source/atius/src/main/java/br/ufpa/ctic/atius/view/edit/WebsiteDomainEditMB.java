package br.ufpa.ctic.atius.view.edit;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.Faces;
import br.ufpa.ctic.atius.business.WebsiteDomainBC;
import br.ufpa.ctic.atius.domain.InetOrgPerson;
import br.ufpa.ctic.atius.domain.WebsiteDomain;

@ViewController
public class WebsiteDomainEditMB extends AbstractEditPageBean<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteDomainBC bc;

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
		return null;
	}

}

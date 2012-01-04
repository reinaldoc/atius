package br.ufpa.ctic.atius.view.edit;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.ufpa.ctic.atius.business.WebsiteDomainBC;
import br.ufpa.ctic.atius.domain.InetOrgPerson;
import br.ufpa.ctic.atius.domain.WebsiteDomain;

@ViewController
public class WebsiteDomainEditMB extends AbstractEditPageBean<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteDomainBC bc;

	@Inject
	private FacesContext facesContext;

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert() {
		System.out.println("========> Insert() "+getBean().getServerName());
		if (!bc.domainAvailable(getBean().getServerName())) {
			facesContext.addMessage("sites", new FacesMessage("Domínio indisponível, escolha outro."));
			facesContext.validationFailed();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() {
		System.out.println("========> Update()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void handleLoad() {
		// TODO Auto-generated method stub

	}

	public String domainAvailable() {
		if (bc.domainAvailable(getBean().getServerName()))
			facesContext.addMessage("sites", new FacesMessage("O domínio " + getBean().getServerName()
					+ " está disponível"));
		else {
			facesContext.addMessage("sites", new FacesMessage("Domínio indisponível, escolha outro."));
			facesContext.validationFailed();
		}
		return null;
	}

	public List<InetOrgPerson> findPerson(String search) {
		return bc.findPerson(search);
	}
}

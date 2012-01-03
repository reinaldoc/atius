package br.ufpa.ctic.atius.view.edit;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.ufpa.ctic.atius.business.WebsiteDomainBC;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void handleLoad() {
		// TODO Auto-generated method stub

	}

	public String domainAvailable() {
		if (bc.domainAvailable(getBean().getServerName()))
			facesContext.addMessage("growl", new FacesMessage("O domínio " + getBean().getServerName()
					+ " está disponível"));
		else
			facesContext.addMessage("growl", new FacesMessage("Domínio indisponível, escolha outro."));
		return null;
	}

}

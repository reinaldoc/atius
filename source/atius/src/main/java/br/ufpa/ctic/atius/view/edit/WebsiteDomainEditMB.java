package br.ufpa.ctic.atius.view.edit;

import java.util.List;

import javax.faces.event.ActionEvent;
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

	@Override
	public String delete() {
		bc.delete(getId());
		return null;
	}

	public String insert() {
		if (!bc.domainAvailable(getBean().getServerName())) {
			Faces.addMessage("sites", new DefaultMessage("Domínio indisponível, escolha outro."));
			Faces.validationFailed();
			return null;
		}
		bc.insert(getBean());
		return null;
	}

	@Override
	public String update() {
		System.out.println("==========> update()");
		//bc.update(getBean());
		return null;
	}

	@Override
	protected void handleLoad() {
		// TODO Auto-generated method stub

	}

	public List<InetOrgPerson> findPerson(String search) {
		return bc.findPerson(search);
	}

	/*
	 * chamada a partir do commandButton para adição de um registro. O método
	 * Faces.resetValidation() limpará o estado da validação de todos os inputs
	 * da visão.
	 */
	public String create() {
		setId(null);
		setBean(new WebsiteDomain());
		Faces.resetValidation();
		return null;
	}

	/*
	 * chamada a partir do commandButton que carrega para edição um registro. Se
	 * o método resetParentFormValidation() foi chamado a patir do mesmo botao
	 * via actionListener não é necessário chamar Faces.resetValidation() aqui;
	 */
	public String loadByServerName(String serverName) {
		setId(serverName);
		setBean(bc.load(serverName));
		Faces.resetValidation();
		return null;
	}

	/*
	 * OPCIONALMENTE: chamado a partir de um actionListener a partir de um
	 * commandButton dentro do mesmo form. O método
	 * Faces.resetParentFormValidation limpará o estado da validação de cada
	 * input SOMENTE DESTE form. Logo é mais perfomatico que o
	 * Faces.resetValidation();
	 */
	public void resetParentFormValidation(ActionEvent ae) {
		Faces.resetParentFormValidation(ae.getComponent());
	}

	/*
	 * chamado a
	 */
	public String domainAvailable() {
		if (bc.domainAvailable(getBean().getServerName()))
			Faces.addMessage(new DefaultMessage("O domínio " + getBean().getServerName() + " está disponível"));
		else {
			Faces.addMessage(new DefaultMessage("Domínio indisponível, escolha outro."));
			Faces.validationFailed();
		}
		return null;
	}

}

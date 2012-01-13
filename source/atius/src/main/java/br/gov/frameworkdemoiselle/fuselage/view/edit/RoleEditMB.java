package br.gov.frameworkdemoiselle.fuselage.view.edit;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.fuselage.business.RoleBC;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityResource;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityRole;
import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.Faces;

@ViewController
public class RoleEditMB extends AbstractEditPageBean<SecurityRole, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private RoleBC bc;

	@Override
	public String insert() {
		bc.insert(getBean());
		return null;
	}

	@Override
	public String update() {
		bc.update(getBean());
		return null;
	}

	@Override
	public String delete() {
		bc.delete(getBean().getId());
		return null;
	}

	@Override
	public SecurityRole load(Long id) {
		return null;
	}

	public String addResource(SecurityResource resource) {
		System.out.println("==> add " + resource.getName() + ":" + resource.getValue());
		if (getBean().getResource() != null)
			getBean().getResource().add(resource);
		else
			Faces.validationFailed(new DefaultMessage("Oooopppsss... a operação não pode ser realizada"));
		return null;
	}

	public String delResource(SecurityResource resource) {
		System.out.println("==> del " + resource.getName() + ":" + resource.getValue());
		if (getBean().getResource() != null) {
			for (int i = 0; i < getBean().getResource().size(); i++) {
				SecurityResource securityResource = getBean().getResource().get(i);
				System.out.println("==> compare " + securityResource.getName() + ":" + resource.getName());
				if (securityResource == resource) {
					System.out.println("==> compare " + securityResource.getName() + ":" + resource.getName() + " true");
					getBean().getResource().remove(i);
					i--;
				}
			}
		} else
			Faces.validationFailed(new DefaultMessage("Oooopppsss... a operação não pode ser realizada"));
		return null;
	}

}
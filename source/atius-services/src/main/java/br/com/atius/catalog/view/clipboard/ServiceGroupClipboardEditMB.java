package br.com.atius.catalog.view.clipboard;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.atius.catalog.business.ServiceGroupBC;
import br.com.atius.catalog.domain.ServiceGroup;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@SessionScoped
@ViewController
public class ServiceGroupClipboardEditMB extends AbstractEditPageBean<ServiceGroup, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceGroupBC bc;

	@Override
	public void editById(Integer id) {
		super.editById(id);
		getBean().getItems().size();
	}

	@Override
	public String insert() {
		return null;
	}

	@Override
	@Transactional
	public String update() {
		return null;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	public ServiceGroup load(Integer id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return new ServiceGroup();
	}

}

package br.com.atius.services.view.edit;

import javax.inject.Inject;

import br.com.atius.services.business.ServiceGroupBC;
import br.com.atius.services.domain.ServiceArea;
import br.com.atius.services.domain.ServiceGroup;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@ViewController
public class ServiceGroupEditMB extends AbstractEditPageBean<ServiceGroup, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceGroupBC bc;

	public void editBean(ServiceArea serviceArea) {
		super.editBean();
		getBean().setServiceArea(bc.loadArea(serviceArea.getId()));
	}

	@Override
	public String insert() {
		try {
			bc.insert(getBean());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String update() {
		try {
			bc.update(getBean());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			bc.delete(getBean().getId());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
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

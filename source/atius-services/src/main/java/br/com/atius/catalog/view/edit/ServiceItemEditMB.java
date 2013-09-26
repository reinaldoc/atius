package br.com.atius.catalog.view.edit;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.atius.catalog.business.ServiceGroupBC;
import br.com.atius.catalog.business.ServiceItemBC;
import br.com.atius.catalog.business.ServiceSubgroupBC;
import br.com.atius.catalog.domain.ServiceGroup;
import br.com.atius.catalog.domain.ServiceItem;
import br.com.atius.catalog.domain.ServiceSubgroup;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@SessionScoped
@ViewController
public class ServiceItemEditMB extends AbstractEditPageBean<ServiceItem, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceItemBC bc;

	@Inject
	private ServiceGroupBC serviceGroupBC;

	@Inject
	private ServiceSubgroupBC serviceSubgroupBC;

	public void editBean(ServiceGroup serviceGroup) {
		super.editBean();
		getBean().setGroup(serviceGroupBC.load(serviceGroup.getId()));
	}

	public void editBean(ServiceSubgroup serviceSubgroup) {
		super.editBean();
		getBean().setSubgroup(serviceSubgroupBC.load(serviceSubgroup.getId()));
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
	public ServiceItem load(Integer id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return new ServiceItem();
	}

}

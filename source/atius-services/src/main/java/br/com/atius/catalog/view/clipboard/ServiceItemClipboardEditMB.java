package br.com.atius.catalog.view.clipboard;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.atius.catalog.business.ServiceItemBC;
import br.com.atius.catalog.domain.ServiceItem;
import br.com.atius.core.common.Operation;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@SessionScoped
@ViewController
public class ServiceItemClipboardEditMB extends AbstractEditPageBean<ServiceItem, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceItemBC bc;

	@Inject
	private ServiceGroupClipboardEditMB serviceGroupClipboardEditMB;

	@Inject
	private ServiceSubgroupClipboardEditMB serviceSubgroupClipboardEditMB;

	private Operation operation = Operation.COPY;

	public void copy(ServiceItem item) {
		super.editById(item.getId());
		operation = Operation.COPY;
		getBean().getKnowledges().size();
	}

	public void move(ServiceItem item) {
		super.editById(item.getId());
		operation = Operation.MOVE;
	}

	@Override
	public String insert() {
		return null;
	}

	@Override
	public String update() {
		return null;
	}

	public String update_group() {
		try {
			getBean().setSubgroup(null);
			getBean().setGroup(serviceGroupClipboardEditMB.getBean());
			if (operation == Operation.COPY)
				getBean().setId(null);
			bc.update(getBean());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return "/pages/catalog/group.jsf?faces-redirect=true";
	}

	public String update_subgroup() {
		try {
			getBean().setGroup(null);
			getBean().setSubgroup(serviceSubgroupClipboardEditMB.getBean());
			if (operation == Operation.COPY)
				getBean().setId(null);
			bc.update(getBean());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return "/pages/catalog/group.jsf?faces-redirect=true";
	}

	@Override
	public String delete() {
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

	public Operation getOperationEnum() {
		return operation;
	}

	public int getOperation() {
		return operation.getCode();
	}

}

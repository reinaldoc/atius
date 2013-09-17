package br.com.atius.knowledge.view.edit;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.atius.catalog.domain.ServiceItem;
import br.com.atius.knowledge.business.KnowledgeBC;
import br.com.atius.knowledge.domain.Knowledge;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@SessionScoped
@ViewController
public class KnowledgeEditMB extends AbstractEditPageBean<Knowledge, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private KnowledgeBC bc;

	public void editBean(ServiceItem serviceItem) {
		super.editBean();
		List<ServiceItem> items = new ArrayList<ServiceItem>();
		items.add(bc.loadService(serviceItem.getId()));
		getBean().setServices(items);
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
	public Knowledge load(Integer id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return new Knowledge();
	}

}

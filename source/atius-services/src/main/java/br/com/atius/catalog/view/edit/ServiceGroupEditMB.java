package br.com.atius.catalog.view.edit;

import java.io.ByteArrayInputStream;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.atius.catalog.business.ServiceGroupBC;
import br.com.atius.catalog.domain.ServiceArea;
import br.com.atius.catalog.domain.ServiceGroup;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@SessionScoped
@ViewController
public class ServiceGroupEditMB extends AbstractEditPageBean<ServiceGroup, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceGroupBC bc;

	public void editBean(ServiceArea serviceArea) {
		super.editBean();
		getBean().setArea(bc.loadArea(serviceArea.getId()));
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

	public void upload(FileUploadEvent event) {
		getBean().setImage(event.getFile().getContents());
	}

	public StreamedContent getImageByParamId() {
		try {
			String id = Faces.getFacesContext().getExternalContext().getRequestParameterMap().get("id");
			ServiceGroup group = bc.load(Integer.valueOf(id));
			return new DefaultStreamedContent(new ByteArrayInputStream(group.getImage()), "image/png");
		} catch (Exception e) {
			return new DefaultStreamedContent();
		}
	}

}

package br.com.atius.catalog.view.edit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;

import br.com.atius.catalog.business.ServiceAreaBC;
import br.com.atius.catalog.business.ServiceGroupBC;
import br.com.atius.catalog.common.SessionCatalog;
import br.com.atius.catalog.domain.ServiceArea;
import br.com.atius.catalog.domain.ServiceGroup;
import br.com.atius.core.domain.Repository;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@ViewController
public class ServiceGroupEditMB extends AbstractEditPageBean<ServiceGroup, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionCatalog sessionCatalog;

	@Inject
	private ServiceGroupBC bc;

	@Inject
	private ServiceAreaBC serviceAreaBC;

	@PostConstruct
	protected void init() {
		if (sessionCatalog.getGroupId() != null)
			editById(sessionCatalog.getGroupId());
	}

	public void editBean(ServiceArea serviceArea) {
		super.editBean();
		getBean().setArea(serviceAreaBC.load(serviceArea.getId()));
	}

	@Override
	public String insert() {
		try {
			if (getBean().getImage() != null)
				getBean().getImage().setDescription("Imagem do grupo: " + getBean().getName());
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
			if (getBean().getImage() != null) {
				sessionCatalog.evictRepository(getBean().getImage().getId());
				getBean().getImage().setDescription("Imagem do grupo: " + getBean().getName());
			}
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
		if (getBean().getImage() == null)
			getBean().setImage(
					new Repository(event.getFile().getContents(), event.getFile().getContentType(), event.getFile().getFileName()));
		else {
			getBean().getImage().setData(event.getFile().getContents());
			getBean().getImage().setContentType(event.getFile().getContentType());
			getBean().getImage().setFilename(event.getFile().getFileName());
		}
	}

}

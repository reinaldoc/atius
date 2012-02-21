package br.ufpa.ctic.atius.dhcp.view.edit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.ufpa.ctic.atius.dhcp.business.DhcpServiceBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpService;

@ViewController
public class DhcpServiceEditMB extends AbstractEditPageBean<DhcpService, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpServiceBC bc;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		setBean(bc.getDhcpService());
	}

	@Override
	public String insert() {
		try {
			// getBean().setParentDN(bc.getDhcpSubnetDN());
			bc.insert(getBean());
			Faces.addI18nMessage("atius.dhcp.service.insert.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.service.insert.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String update() {
		try {
			bc.update(getBean());
			Faces.addI18nMessage("atius.dhcp.service.update.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.service.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			bc.delete(getBean().getCn());
			Faces.addI18nMessage("atius.dhcp.service.delete.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.service.delete.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	protected DhcpService load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.service.load.failed", SeverityType.ERROR);
		}
		return new DhcpService();
	}

}

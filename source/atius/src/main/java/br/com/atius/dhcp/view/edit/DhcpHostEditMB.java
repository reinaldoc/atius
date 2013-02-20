package br.com.atius.dhcp.view.edit;

import javax.inject.Inject;

import br.com.atius.dhcp.business.DhcpHostBC;
import br.com.atius.dhcp.domain.DhcpHost;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@ViewController
public class DhcpHostEditMB extends AbstractEditPageBean<DhcpHost, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpHostBC bc;

	@Override
	public String insert() {
		try {
			getBean().setParentDN(bc.getDhcpSubnet().getDn());
			bc.insert(getBean());
			Faces.addI18nMessage("atius.dhcp.host.insert.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.host.insert.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String update() {
		try {
			bc.update(getBean());
			Faces.addI18nMessage("atius.dhcp.host.update.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.host.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			bc.delete(getBean().getCn());
			Faces.addI18nMessage("atius.dhcp.host.delete.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.host.delete.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	protected DhcpHost load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.host.load.failed", SeverityType.ERROR);
		}
		return new DhcpHost();
	}

}

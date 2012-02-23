package br.ufpa.ctic.atius.dhcp.view.edit;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.ufpa.ctic.atius.dhcp.business.DhcpSharedNetworkBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;

@ViewController
public class DhcpSharedNetworkEditMB extends AbstractEditPageBean<DhcpSharedNetwork, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSharedNetworkBC bc;

	@Override
	public String insert() {
		try {
			// getBean().setParentDN(bc.getDhcpSubnetDN());
			bc.insert(getBean());
			Faces.addI18nMessage("atius.dhcp.network.insert.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.network.insert.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String update() {
		try {
			bc.update(getBean());
			Faces.addI18nMessage("atius.dhcp.network.update.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.network.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			bc.delete(getBean().getCn());
			Faces.addI18nMessage("atius.dhcp.network.delete.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.network.delete.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	protected DhcpSharedNetwork load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.network.load.failed", SeverityType.ERROR);
		}
		return new DhcpSharedNetwork();
	}

}

package br.ufpa.ctic.atius.dhcp.view.edit;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.ufpa.ctic.atius.dhcp.business.DhcpServerBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;
import br.ufpa.ctic.atius.dhcp.view.list.DhcpServerListMB;

@ViewController
public class DhcpServerEditMB extends AbstractEditPageBean<DhcpServer, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpServerBC bc;

	@Override
	public String insert() {
		try {
			// getBean().setParentDN(bc.getDhcpSubnetDN());
			bc.insert(getBean());
			Faces.addI18nMessage("atius.dhcp.server.insert.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.server.insert.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String update() {
		try {
			bc.update(getBean());
			Faces.addI18nMessage("atius.dhcp.server.update.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.server.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			bc.deleteDhcpService(getBean().getDhcpServiceDN());
			bc.delete(getBean().getCn());
			Faces.addI18nMessage("atius.dhcp.server.delete.success", getBean().getCn());
			if (getBean().equals(bc.getDhcpServer()))
				bc.selectDhcpServer(new DhcpServer());
			Faces.getManagedProperty("#{dhcpServerListMB}", DhcpServerListMB.class).clearResultList();
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.server.delete.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	protected DhcpServer load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.server.load.failed", SeverityType.ERROR);
		}
		return new DhcpServer();
	}

}

package br.com.atius.dhcp.view.edit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.atius.dhcp.business.DhcpServiceBC;
import br.com.atius.dhcp.domain.DhcpServer;
import br.com.atius.dhcp.domain.DhcpService;
import br.com.atius.dhcp.view.list.DhcpServerListMB;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@ViewController
public class DhcpServiceEditMB extends AbstractEditPageBean<DhcpService, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpServiceBC bc;

	@Inject
	private DhcpServerListMB dhcpServerListMB;

	@PostConstruct
	public void init() {
		editBean(bc.getDhcpService());
		getBean().setDhcpServerName(bc.getDhcpServer().getCn());
		getBean().load();
	}

	@Override
	public String insert() {
		try {
			DhcpServer dhcpServer = bc.insertDhcpServer(getBean().getDhcpServerName());
			getBean().setParentDN(dhcpServer.getDn());
			getBean().setCn("dhcpService");
			getBean().set();
			bc.insert(getBean());
			Faces.addI18nMessage("atius.dhcp.service.insert.success", getBean().getCn());
			dhcpServerListMB.list();
			dhcpServerListMB.selectDhcpServer(dhcpServer);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.service.insert.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String update() {
		try {
			getBean().set();
			bc.update(getBean());
			Faces.addI18nMessage("atius.dhcp.service.update.success", getBean().getCn());
			init();
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

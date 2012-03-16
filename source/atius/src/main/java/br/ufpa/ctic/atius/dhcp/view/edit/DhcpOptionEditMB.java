package br.ufpa.ctic.atius.dhcp.view.edit;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.ufpa.ctic.atius.dhcp.business.DhcpOptionBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpHost;
import br.ufpa.ctic.atius.dhcp.domain.DhcpOption;
import br.ufpa.ctic.atius.dhcp.domain.DhcpService;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSubnet;

@ViewController
public class DhcpOptionEditMB extends AbstractEditPageBean<DhcpOption, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpOptionBC bc;

	private String updateId;

	@Override
	public String insert() {
		return null;
	}

	@Override
	public String update() {
		try {
			getBean().set();
			bc.update(getBean());
			Faces.addI18nMessage("atius.dhcp.option.update.success");
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.option.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	protected DhcpOption load(String id) {
		return new DhcpOption();
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getObjectType() {
		if (getBean() instanceof DhcpService)
			return "Servidor";
		if (getBean() instanceof DhcpSharedNetwork)
			return "Rede";
		if (getBean() instanceof DhcpSubnet)
			return "Subrede";
		return "Hostname";
	}

	public String getObjectName() {
		if (getBean() instanceof DhcpService)
			return bc.getDhcpServer().getCn();
		if (getBean() instanceof DhcpSharedNetwork)
			return ((DhcpSharedNetwork) getBean()).getCn();
		if (getBean() instanceof DhcpSubnet)
			return ((DhcpSubnet) getBean()).getCn();
		return ((DhcpHost) getBean()).getCn();
	}

}

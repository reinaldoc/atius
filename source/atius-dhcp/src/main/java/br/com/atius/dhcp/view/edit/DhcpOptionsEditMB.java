package br.com.atius.dhcp.view.edit;

import javax.inject.Inject;

import org.apache.commons.net.util.SubnetUtils;

import br.com.atius.dhcp.business.DhcpOptionsBC;
import br.com.atius.dhcp.domain.DhcpHost;
import br.com.atius.dhcp.domain.DhcpOptions;
import br.com.atius.dhcp.domain.DhcpService;
import br.com.atius.dhcp.domain.DhcpSharedNetwork;
import br.com.atius.dhcp.domain.DhcpSubnet;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

@ViewController
public class DhcpOptionsEditMB extends AbstractEditPageBean<DhcpOptions, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpOptionsBC bc;

	private String updateId;

	@Override
	public String insert() {
		return null;
	}

	@Override
	public String update() {
		if (validate()) {
			try {
				getBean().set();
				bc.update(getBean());
				Faces.addI18nMessage("atius.dhcp.option.update.success");
			} catch (RuntimeException e) {
				e.printStackTrace();
				Faces.validationFailed();
				Faces.addI18nMessage("atius.dhcp.option.update.failed", SeverityType.ERROR);
			}
		}
		return null;
	}

	private boolean validate() {
		boolean validate = true;

		if (Strings.isNotBlank(getBean().getDhcpOptionDNS()))
			try {
				new SubnetUtils(getBean().getDhcpOptionDNS() + "/32");
			} catch (RuntimeException e) {
				Faces.validationFailed();
				Faces.addI18nMessage("atius.dhcp.validation.dnsserver.failed", SeverityType.ERROR);
				validate = false;
			}

		if (Strings.isNotBlank(getBean().getDhcpOptionNTP()))
			try {
				new SubnetUtils(getBean().getDhcpOptionNTP() + "/32");
			} catch (RuntimeException e) {
				Faces.validationFailed();
				Faces.addI18nMessage("atius.dhcp.validation.ntpserver.failed", SeverityType.ERROR);
				validate = false;
			}

		if (Strings.isNotBlank(getBean().getDhcpOptionSMB()))
			try {
				new SubnetUtils(getBean().getDhcpOptionSMB() + "/32");
			} catch (RuntimeException e) {
				Faces.validationFailed();
				Faces.addI18nMessage("atius.dhcp.validation.smbserver.failed", SeverityType.ERROR);
				validate = false;
			}

		return validate;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	protected DhcpOptions load(String id) {
		return bc.load(id);
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

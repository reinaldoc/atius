package br.ufpa.ctic.atius.dhcp.view.edit;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.ufpa.ctic.atius.dhcp.business.DhcpSubnetBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSubnet;

@ViewController
public class DhcpSubnetEditMB extends AbstractEditPageBean<DhcpSubnet, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSubnetBC bc;

	@Override
	public String insert() {
		try {
			getBean().setParentDN(bc.getDhcpSharedNetworkDN());
			getBean().setDhcpRange();
			bc.insert(getBean());
			Faces.addI18nMessage("atius.dhcp.subnet.insert.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.subnet.insert.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String update() {
		try {
			getBean().setDhcpRange();
			bc.update(getBean());
			Faces.addI18nMessage("atius.dhcp.subnet.update.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.subnet.update.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			bc.delete(getBean().getCn());
			Faces.addI18nMessage("atius.dhcp.subnet.delete.success", getBean().getCn());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.subnet.delete.failed", SeverityType.ERROR);
		}
		return null;
	}

	@Override
	protected DhcpSubnet load(String id) {
		try {
			return bc.load(id);
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.subnet.load.failed", SeverityType.ERROR);
		}
		return new DhcpSubnet();
	}

	public List<String> getNetmasks() {
		List<String> netmasks = new ArrayList<String>();
		for (int i = 32; i > -1; i--)
			netmasks.add(String.valueOf(i));
		return netmasks;
	}

	public void selectDhcpSubnet(DhcpSubnet dhcpSubnet) {
		bc.selectDhcpSubnet(dhcpSubnet);
	}

}

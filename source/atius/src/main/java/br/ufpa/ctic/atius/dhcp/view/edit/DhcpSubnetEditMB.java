package br.ufpa.ctic.atius.dhcp.view.edit;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.net.util.SubnetUtils;

import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.gov.frameworkdemoiselle.util.contrib.Strings;
import br.ufpa.ctic.atius.dhcp.business.DhcpSubnetBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSubnet;

@ViewController
public class DhcpSubnetEditMB extends AbstractEditPageBean<DhcpSubnet, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSubnetBC bc;

	@Override
	public String insert() {
		SubnetUtils subnet;
		try {
			subnet = new SubnetUtils(getBean().getCn() + "/" + getBean().getDhcpNetMask());
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.validation.networkaddress.failed", SeverityType.ERROR);
			return null;
		}

		try {
			if (!subnet.getInfo().isInRange(getBean().getDhcpGateway()))
				throw new IllegalArgumentException("Gateway is not in subnet");
		} catch (RuntimeException e) {
			Faces.validationFailed();
			Faces.addI18nMessage("atius.dhcp.validation.gateway.failed", SeverityType.ERROR);
			return null;
		}

		getBean().setDhcpRange();
		if (Strings.isNotBlank(getBean().getDhcpRangeFirst()) || Strings.isNotBlank(getBean().getDhcpRangeLast())) {
			try {
				if (!subnet.getInfo().isInRange(getBean().getDhcpRangeFirst()))
					throw new IllegalArgumentException("Start range is not in subnet");
			} catch (RuntimeException e) {
				Faces.validationFailed();
				Faces.addI18nMessage("atius.dhcp.validation.range.failed", SeverityType.ERROR, getBean().getCn() + "/" + getBean().getDhcpNetMask());
				return null;
			}
			try {
				if (!subnet.getInfo().isInRange(getBean().getDhcpRangeLast()))
					throw new IllegalArgumentException("End range is not in subnet");
			} catch (RuntimeException e) {
				Faces.validationFailed();
				Faces.addI18nMessage("atius.dhcp.validation.range.failed", SeverityType.ERROR, getBean().getCn() + "/" + getBean().getDhcpNetMask());
				return null;
			}
		}

		try {
			getBean().setParentDN(bc.getDhcpSharedNetworkDN());
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

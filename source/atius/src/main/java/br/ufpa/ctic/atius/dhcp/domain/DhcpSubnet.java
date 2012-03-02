package br.ufpa.ctic.atius.dhcp.domain;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

public class DhcpSubnet extends Entry {

	@Id
	@Pattern(regexp = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})", message = "Especifique um endereço válido para a subrede")
	private String cn;

	private String[] dhcpOption;

	private String dhcpNetMask;

	private String dhcpRange;

	@Ignore
	private String dhcpRangeFirst;

	@Ignore
	private String dhcpRangeLast;

	@Size(min = 3, message = "Identifique melhor o nome da subrede")
	private String dhcpComments;

	public DhcpSubnet() {
		super();
	}

	public DhcpSubnet(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "dhcpSubnet" };
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String[] getDhcpOption() {
		return dhcpOption;
	}

	public void setDhcpOption(String[] dhcpOption) {
		this.dhcpOption = dhcpOption;
	}

	public String getDhcpGateway() {
		if (dhcpOption != null)
			for (String option : dhcpOption)
				if (option.contains("routers"))
					return option.split(" ")[1];
		return null;
	}

	public void setDhcpGateway(String gateway) {
		if (Strings.isNotBlank(gateway))
			dhcpOption = new String[] { "routers " + gateway };
		else
			dhcpOption = null;
	}

	public String getDhcpNetMask() {
		return dhcpNetMask;
	}

	public void setDhcpNetMask(String dhcpNetMask) {
		this.dhcpNetMask = dhcpNetMask;
	}

	public String getDhcpRangeFirst() {
		if (dhcpRange != null)
			return Strings.substringBefore(dhcpRange, " ");
		return null;
	}

	public void setDhcpRangeFirst(String dhcpRangeFirst) {
		this.dhcpRangeFirst = dhcpRangeFirst;
	}

	public String getDhcpRangeLast() {
		if (dhcpRange != null)
			return Strings.substringAfter(dhcpRange, " ");
		return null;
	}

	public void setDhcpRangeLast(String dhcpRangeLast) {
		this.dhcpRangeLast = dhcpRangeLast;
	}

	public void setDhcpRange() {
		if (Strings.isNotBlank(dhcpRangeFirst) && Strings.isNotBlank(dhcpRangeLast))
			dhcpRange = dhcpRangeFirst + " " + dhcpRangeLast;
		else
			dhcpRange = null;
	}

	public String getDhcpComments() {
		return dhcpComments;
	}

	public void setDhcpComments(String dhcpComments) {
		this.dhcpComments = dhcpComments;
	}

}

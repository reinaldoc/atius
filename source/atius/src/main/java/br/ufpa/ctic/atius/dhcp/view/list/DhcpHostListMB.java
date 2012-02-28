package br.ufpa.ctic.atius.dhcp.view.list;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.enumeration.contrib.Comparison;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.gov.frameworkdemoiselle.util.contrib.Strings;
import br.ufpa.ctic.atius.dhcp.business.DhcpHostBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpHost;

@ViewController
public class DhcpHostListMB extends AbstractListPageBean<DhcpHost, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpHostBC bc;

	private Integer searchNode = 0;
	
	public void init() {
		searchNode = 0;
		clearResultFilter();
	}

	@Override
	protected List<DhcpHost> handleResultList(QueryConfig<DhcpHost> queryConfig) {
		if (getSearchNode() == null)
			return null;
		queryConfig.setGeneric(getSearchNode());

		List<DhcpHost> dhcpHosts = new ArrayList<DhcpHost>();
		if (Strings.isNotBlank(getResultFilter())) {
			queryConfig.getFilter().put("dhcpHWAddress", "ethernet " + getResultFilter());
			dhcpHosts.addAll(bc.findAll());

			queryConfig.getFilter().remove("dhcpHWAddress");
			queryConfig.getFilter().put("dhcpStatements", "fixed-address " + getResultFilter());
			dhcpHosts.addAll(bc.findAll());

			queryConfig.getFilter().remove("dhcpStatements");
			queryConfig.getFilter().put("cn", getResultFilter());
			queryConfig.setFilterComparison(Comparison.CONTAINS);
		}
		dhcpHosts.addAll(bc.findAll());
		return dhcpHosts;
	}

	private String getSearchNode() {
		if (searchNode.intValue() == 0)
			return bc.getDhcpSubnet().getDn();
		else if (searchNode.intValue() == 1)
			return bc.getDhcpSharedNetwork().getDn();
		else
			return bc.getDhcpServer().getDn();
	}

	public void selectSearchNode() {
		clearResultList();
		if (searchNode.intValue() == 0) {
			searchNode = 1;
			Faces.addI18nMessage("atius.dhcp.search.network", bc.getDhcpSharedNetwork().getCn());
		} else if (searchNode.intValue() == 1) {
			searchNode = 2;
			Faces.addI18nMessage("atius.dhcp.search.server", bc.getDhcpServer().getCn());
		} else {
			searchNode = 0;
			Faces.addI18nMessage("atius.dhcp.search.subnet", bc.getDhcpSubnet().getCn() + "/" + bc.getDhcpSubnet().getDhcpNetMask());
		}
	}

}

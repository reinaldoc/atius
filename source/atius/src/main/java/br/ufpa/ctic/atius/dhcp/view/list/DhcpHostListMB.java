package br.ufpa.ctic.atius.dhcp.view.list;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.enumeration.contrib.Comparison;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Strings;
import br.ufpa.ctic.atius.dhcp.business.DhcpHostBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpHost;

@ViewController
public class DhcpHostListMB extends AbstractListPageBean<DhcpHost, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpHostBC bc;

	@Override
	protected List<DhcpHost> handleResultList(QueryConfig<DhcpHost> queryConfig) {
		if (bc.getDhcpSubnetDN() == null)
			return null;

		queryConfig.setGeneric(bc.getDhcpSubnetDN());

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

}

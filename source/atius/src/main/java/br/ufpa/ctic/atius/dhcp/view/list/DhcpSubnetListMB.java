package br.ufpa.ctic.atius.dhcp.view.list;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.ufpa.ctic.atius.dhcp.business.DhcpSubnetBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSubnet;

@ViewController
public class DhcpSubnetListMB extends AbstractListPageBean<DhcpSubnet, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSubnetBC bc;

	@Override
	protected List<DhcpSubnet> handleResultList(QueryConfig<DhcpSubnet> queryConfig) {
		getQueryConfig().setGeneric(bc.getDhcpSharedNetworkDN());
		return bc.findAll();
	}

}

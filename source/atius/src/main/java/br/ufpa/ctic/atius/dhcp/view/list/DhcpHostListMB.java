package br.ufpa.ctic.atius.dhcp.view.list;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
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
		getQueryConfig().setGeneric(bc.getDhcpSubnetDN());
		return bc.findAll();
	}

}

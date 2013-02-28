package br.com.atius.dhcp.view.list;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.atius.dhcp.business.DhcpSharedNetworkBC;
import br.com.atius.dhcp.domain.DhcpSharedNetwork;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.gov.frameworkdemoiselle.util.Strings;

@ViewController
public class DhcpSharedNetworkListMB extends AbstractListPageBean<DhcpSharedNetwork, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSharedNetworkBC bc;

	@Override
	protected List<DhcpSharedNetwork> handleResultList(QueryConfig<DhcpSharedNetwork> queryConfig) {
		if (bc.getDhcpServiceDN() == null)
			return null;
		queryConfig.setGeneric(bc.getDhcpServiceDN());
		return bc.findAll();
	}

	@PostConstruct
	public void init() {
		if (Strings.isEmpty(getSelectedMenu()))
			if (getResultList() != null && getResultList().size() > 0) {
				selectMenu(getResultList().get(0).getCn());
				bc.selectDhcpSharedNetwork(getResultList().get(0));
			} else
				selectMenu("Config");
	}

	public void selectDhcpSharedNetwork(DhcpSharedNetwork dhcpSharedNetwork) {
		selectMenu(dhcpSharedNetwork.getCn());
		bc.selectDhcpSharedNetwork(dhcpSharedNetwork);
	}

}

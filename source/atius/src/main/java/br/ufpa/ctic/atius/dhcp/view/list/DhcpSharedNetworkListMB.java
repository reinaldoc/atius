package br.ufpa.ctic.atius.dhcp.view.list;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.ufpa.ctic.atius.dhcp.business.DhcpSharedNetworkBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpSharedNetwork;

@ViewController
public class DhcpSharedNetworkListMB extends AbstractListPageBean<DhcpSharedNetwork, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSharedNetworkBC bc;

	@Override
	protected String getSortAttribute() {
		return "cn";
	}

	@Override
	protected List<DhcpSharedNetwork> handleResultList() {
		return bc.findAll();
	}

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		bc.selectMenu(getFirstSharedNetwork());
	}

	public String getFirstSharedNetwork() {
		if (getResultList().size() > 0)
			return getResultList().get(0).getCn();
		else
			return "";
	}

}

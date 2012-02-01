package br.ufpa.ctic.atius.dhcp.view.list;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.ufpa.ctic.atius.dhcp.business.DhcpServerBC;
import br.ufpa.ctic.atius.dhcp.domain.DhcpServer;

@ViewController
public class DhcpServerListMB extends AbstractListPageBean<DhcpServer, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpServerBC bc;

	@Override
	protected List<DhcpServer> handleResultList() {
		return bc.findAll();
	}

}

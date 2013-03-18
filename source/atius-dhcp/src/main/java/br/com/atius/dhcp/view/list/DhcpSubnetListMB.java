package br.com.atius.dhcp.view.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.atius.dhcp.business.DhcpSubnetBC;
import br.com.atius.dhcp.common.DhcpSessionInfo;
import br.com.atius.dhcp.domain.DhcpHost;
import br.com.atius.dhcp.domain.DhcpSubnet;
import br.gov.frameworkdemoiselle.enumeration.contrib.Comparison;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.gov.frameworkdemoiselle.util.FileRenderer;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

@ViewController
public class DhcpSubnetListMB extends AbstractListPageBean<DhcpSubnet, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DhcpSubnetBC bc;

	@Inject
	private DhcpHostListMB dhcpHostListMB;

	@Inject
	private DhcpSessionInfo sessionInfo;

	@Inject
	@Path("reports/dhcpSubnet.jasper")
	private Report report;

	@Inject
	private FileRenderer renderer;

	@Override
	protected List<DhcpSubnet> handleResultList(QueryConfig<DhcpSubnet> queryConfig) {
		if (bc.getDhcpSharedNetworkDN() == null)
			return null;

		queryConfig.setGeneric(bc.getDhcpSharedNetworkDN());

		List<DhcpSubnet> dhcpSubnets = new ArrayList<DhcpSubnet>();
		if (Strings.isNotBlank(getResultFilter())) {
			// dhcpComments is defined as caseIgnoreIA5Match on dhcp.schema
			// from ISC than we can not do substring search
			queryConfig.getFilter().put("dhcpComments", getResultFilter());
			dhcpSubnets.addAll(bc.findAll());

			queryConfig.getFilter().remove("dhcpComments");
			queryConfig.getFilter().put("cn", getResultFilter());
			queryConfig.setFilterComparison(Comparison.CONTAINS);
		}
		dhcpSubnets.addAll(bc.findAll());
		return dhcpSubnets;
	}

	public String print() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("logo", Faces.getReportPath("images/ctic_logo.gif"));
			params.put("dhcpServer", sessionInfo.getDhcpServer().getCn());
			params.put("dhcpSharedNetwork", sessionInfo.getDhcpSharedNetwork().getCn());
			params.put("SUBREPORT_DIR", Faces.getReportPath("/"));
			populateDhcpHosts();
			byte[] buffer = report.export(getResultList(), params, Type.PDF);
			this.renderer.render(buffer, FileRenderer.ContentType.PDF, "dhcpSubnet.pdf");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Faces.validationFailed();
			Faces.addI18nMessage("fuselage.generic.report.error", SeverityType.ERROR);
		}
		return null;
	}

	private void populateDhcpHosts() {
		Map<String, List<DhcpHost>> indexBySubnet = new HashMap<String, List<DhcpHost>>();
		for (DhcpSubnet dhcpSubnet : getResultList())
			indexBySubnet.put(dhcpSubnet.getDn(), dhcpSubnet.getDhcpHosts());

		dhcpHostListMB.searchOnSharedNetwork();
		List<DhcpHost> dhcpHosts = dhcpHostListMB.getResultList();
		for (DhcpHost dhcpHost : dhcpHosts) {
			for (DhcpSubnet dhcpSubnet : getResultList()) {
				if (dhcpHost.getDn().toLowerCase().endsWith(dhcpSubnet.getDn().toLowerCase())) {
					indexBySubnet.get(dhcpSubnet.getDn()).add(dhcpHost);
					continue;
				}
			}
		}

	}

}

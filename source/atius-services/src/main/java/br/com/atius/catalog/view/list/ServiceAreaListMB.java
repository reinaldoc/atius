package br.com.atius.catalog.view.list;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.atius.catalog.business.ServiceAreaBC;
import br.com.atius.catalog.domain.ServiceArea;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.gov.frameworkdemoiselle.util.FileRenderer;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

@ViewController
public class ServiceAreaListMB extends AbstractListPageBean<ServiceArea, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceAreaBC bc;

	@Inject
	@Path("reports/catalog/catalog.jasper")
	private Report report;

	@Inject
	private FileRenderer renderer;

	@Override
	protected List<ServiceArea> handleResultList(QueryConfig<ServiceArea> queryConfig) {
		queryConfig.setSorting("name");
		return bc.findAll();
	}

	@RequestScoped
	public StreamedContent print() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("logo", Faces.getReportPath("images/AELIS2012.jpg"));
			// params.put("brasao", Faces.getReportPath("images/brasao.png"));
			params.put("SUBREPORT_DIR", Faces.getReportPath("catalog"));
			list();
			byte[] buffer = report.export(getResultList(), params, Type.PDF);
			return new DefaultStreamedContent(new ByteArrayInputStream(buffer), "application/pdf", "catalog.pdf");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return null;
	}

	@RequestScoped
	public StreamedContent printArea(Integer id) {
		try {
			List<ServiceArea> result = new ArrayList<ServiceArea>();
			result.add(bc.load(id));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SUBREPORT_DIR", Faces.getReportPath("catalog"));
			byte[] buffer = report.export(result, params, Type.PDF);
			return new DefaultStreamedContent(new ByteArrayInputStream(buffer), "application/pdf", "catalog.pdf");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Faces.validationFailed();
			Faces.addI18nMessage("atius.error.generic", SeverityType.ERROR);
		}
		return null;
	}

}

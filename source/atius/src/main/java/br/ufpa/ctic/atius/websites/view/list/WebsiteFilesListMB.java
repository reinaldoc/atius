package br.ufpa.ctic.atius.websites.view.list;

import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.ufpa.ctic.atius.websites.domain.WebsiteDomain;
import br.ufpa.ctic.atius.websites.domain.WebsiteFiles;

@ViewController
public class WebsiteFilesListMB extends AbstractListPageBean<WebsiteFiles, Long> {

	private static final long serialVersionUID = 1L;

	private WebsiteDomain websiteDomain;

	private UploadedFile file;

	@Override
	protected List<WebsiteFiles> handleResultList() {
		return null;
	}

	public String upload() {
		System.out.println("==> " + websiteDomain);
		if (file != null)
			System.out.println("==> " + file.getFileName());
		else
			System.out.println("==> " + null);
		return null;
	}

	public void upload(FileUploadEvent event) {
		System.out.println("==> " + websiteDomain);
		if (event.getFile() != null)
			System.out.println("==> " + event.getFile().getFileName());
		else
			System.out.println("==> " + null);

	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		System.out.println("=====> " + file);
		this.file = file;
	}

	public void selectWebsite(WebsiteDomain websiteDomain) {
		this.websiteDomain = websiteDomain;
	}

}

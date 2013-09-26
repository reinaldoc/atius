package br.com.atius.catalog.common;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.atius.catalog.business.ServiceGroupBC;
import br.com.atius.catalog.domain.ServiceGroup;
import br.gov.frameworkdemoiselle.util.Redirector;
import br.gov.frameworkdemoiselle.util.contrib.Faces;

/**
 * 
 * SessionCatalog is class to keep session information from Catalog module
 * 
 */
@Named
@SessionScoped
public class SessionCatalog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceGroupBC serviceGroupBC;

	private Integer groupId;

	private Map<Integer, ServiceGroup> groupCache = new HashMap<Integer, ServiceGroup>();

	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * Save on session bean the selected ServiceGroup
	 * 
	 * @param groupId
	 *            ServiceGroup id
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * A method to be called from f:event type="preRenderView" that redirect to
	 * welcome page if a ServiceGroup was not selected
	 */
	public void requireGroup() {
		if (groupId == null)
			Redirector.redirect("/pages/catalog/area.jsf");
	}

	/**
	 * Provide a streamed content to p:graphicImage from ServiceGroup id read
	 * from URL. Build a cache that require to be cleared by evictGroupCache
	 * method
	 * 
	 * @return
	 */
	public StreamedContent getGroupImageByParamId() {
		try {
			Integer id = Integer.valueOf(Faces.getFacesContext().getExternalContext().getRequestParameterMap().get("id"));
			ServiceGroup group = groupCache.get(id);
			if (group == null) {
				group = serviceGroupBC.load(Integer.valueOf(id));
				groupCache.put(id, group);
			}
			return new DefaultStreamedContent(new ByteArrayInputStream(group.getImage()), "image/png");
		} catch (Exception e) {
			return new DefaultStreamedContent();
		}
	}

	/**
	 * Remove a element from a ServiceGroup cache
	 * @param id
	 */
	public void evictGroupCache(Integer id) {
		groupCache.remove(id);
	}

}

package br.ufpa.ctic.atius.domain;

public class WebsiteCategory {

	private String name;

	private int order;
	
	public WebsiteCategory() {
		// TODO Auto-generated constructor stub
	}
	
	public WebsiteCategory(String name, int order) {
		this.name = name;
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}

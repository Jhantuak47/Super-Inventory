package com.superInvent.POJO;

public class Demo {
	private String paginations;
	private String tbody;
	
	public String getPaginations() {
		return paginations;
	}
	public void setPaginations(String paginations) {
		this.paginations = paginations;
	}
	
	
	public String getTbody() {
		return tbody;
	}
	public void setTbody(String tbody) {
		this.tbody = tbody;
	}
	@Override
	public String toString() {
		return "Demo [paginations=" + paginations + ", tbody=" + tbody + "]";
	}

	
	
}

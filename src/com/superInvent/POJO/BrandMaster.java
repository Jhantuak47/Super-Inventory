package com.superInvent.POJO;

import java.sql.Timestamp;

public class BrandMaster {
	private int id;
	private String name;
	private Timestamp created_at;
	private Timestamp updated_at;
	private int status = 0;
	private int is_deleted;
	
	
	
	public int getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	@Override
	public String toString() {
		return "BrandMaster [id=" + id + ", name=" + name + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ ", status=" + status + ", is_deleted=" + is_deleted + "]";
	}
	
}

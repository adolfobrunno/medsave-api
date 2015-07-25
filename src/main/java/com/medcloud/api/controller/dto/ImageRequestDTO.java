package com.medcloud.api.controller.dto;




public class ImageRequestDTO {

	private String iid;
	private String creation_date;
	private String size;
	private String cid;
	private String sid;
	private String sno;
	private String pid;
	private String modality;
	private String path;
	private String user_token;


	ImageRequestDTO(){
		
	}
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getUser_token() {
		return user_token;
	}
	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}
	
}

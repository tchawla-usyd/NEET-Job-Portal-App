package com.neet.jobsite.response;

public class S3UploadResponse {
	public boolean is_success;
	public String file_url;
	
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public boolean isIs_success() {
		return is_success;
	}
	public void setIs_success(boolean is_success) {
		this.is_success = is_success;
	}
	
	
}

package com.school.foot_patroling.register.model;

public class DeviceAuthModel {

	private String imeiNo;
	private boolean imeiAuth;
	private String registrationId;
	private String message;
	private String appName;

	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	public boolean isImeiAuth() {
		return imeiAuth;
	}
	public void setImeiAuth(boolean imeiAuth) {
		this.imeiAuth = imeiAuth;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}

}

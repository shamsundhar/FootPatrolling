package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "DeviceAuthModel")
public class DeviceAuthModel {

	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "imeiNo")
	private String imeiNo;
	@ColumnInfo(name = "imeiAuth")
	private boolean imeiAuth;
	@ColumnInfo(name = "registrationId")
	private String registrationId;
	@ColumnInfo(name = "message")
	private String message;
	@ColumnInfo(name = "appName")
	private String appName;

	public DeviceAuthModel() {
	}

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


package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "UserLoginDto")
public class UserLoginDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userLoginId")
    private String userLoginId;
    @ColumnInfo(name = "createdStamp")
    private String createdStamp;
    @ColumnInfo(name = "createdTxStamp")
    private String createdTxStamp;
    @ColumnInfo(name = "currentPassword")
    private String currentPassword;
    @ColumnInfo(name = "deviceId")
    private String deviceId;
    @ColumnInfo(name = "disabledDateTime")
    private String disabledDateTime;
    @ColumnInfo(name = "enabled")
    private String enabled;
    @ColumnInfo(name = "externalAuthId")
    private String externalAuthId;
    @ColumnInfo(name = "hasLoggedOut")
    private String hasLoggedOut;
    @ColumnInfo(name = "isSystem")
    private String isSystem;
    @ColumnInfo(name = "lastCurrencyUom")
    private String lastCurrencyUom;
    @ColumnInfo(name = "lastLocale")
    private String lastLocale;
    @ColumnInfo(name = "lastTimeZone")
    private String lastTimeZone;
    @ColumnInfo(name = "lastUpdatedStamp")
    private String lastUpdatedStamp;
    @ColumnInfo(name = "lastUpdatedTxStamp")
    private String lastUpdatedTxStamp;
    @ColumnInfo(name = "partyId")
    private String partyId;
    @ColumnInfo(name = "passwordHint")
    private String passwordHint;
    @ColumnInfo(name = "requirePasswordChange")
    private String requirePasswordChange;
    @ColumnInfo(name = "successiveFailedLogins")
    private String successiveFailedLogins;
    @ColumnInfo(name = "userLdapDn")
    private String userLdapDn;

    public UserLoginDto() {
    }

    public String getCreatedStamp() {
        return createdStamp;
    }

    public void setCreatedStamp(String createdStamp) {
        this.createdStamp = createdStamp;
    }

    public String getCreatedTxStamp() {
        return createdTxStamp;
    }

    public void setCreatedTxStamp(String createdTxStamp) {
        this.createdTxStamp = createdTxStamp;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDisabledDateTime() {
        return disabledDateTime;
    }

    public void setDisabledDateTime(String disabledDateTime) {
        this.disabledDateTime = disabledDateTime;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getExternalAuthId() {
        return externalAuthId;
    }

    public void setExternalAuthId(String externalAuthId) {
        this.externalAuthId = externalAuthId;
    }

    public String getHasLoggedOut() {
        return hasLoggedOut;
    }

    public void setHasLoggedOut(String hasLoggedOut) {
        this.hasLoggedOut = hasLoggedOut;
    }

    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    public String getLastCurrencyUom() {
        return lastCurrencyUom;
    }

    public void setLastCurrencyUom(String lastCurrencyUom) {
        this.lastCurrencyUom = lastCurrencyUom;
    }

    public String getLastLocale() {
        return lastLocale;
    }

    public void setLastLocale(String lastLocale) {
        this.lastLocale = lastLocale;
    }

    public String getLastTimeZone() {
        return lastTimeZone;
    }

    public void setLastTimeZone(String lastTimeZone) {
        this.lastTimeZone = lastTimeZone;
    }

    public String getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    public void setLastUpdatedStamp(String lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }

    public String getLastUpdatedTxStamp() {
        return lastUpdatedTxStamp;
    }

    public void setLastUpdatedTxStamp(String lastUpdatedTxStamp) {
        this.lastUpdatedTxStamp = lastUpdatedTxStamp;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    public String getRequirePasswordChange() {
        return requirePasswordChange;
    }

    public void setRequirePasswordChange(String requirePasswordChange) {
        this.requirePasswordChange = requirePasswordChange;
    }

    public String getSuccessiveFailedLogins() {
        return successiveFailedLogins;
    }

    public void setSuccessiveFailedLogins(String successiveFailedLogins) {
        this.successiveFailedLogins = successiveFailedLogins;
    }

    public String getUserLdapDn() {
        return userLdapDn;
    }

    public void setUserLdapDn(String userLdapDn) {
        this.userLdapDn = userLdapDn;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

}

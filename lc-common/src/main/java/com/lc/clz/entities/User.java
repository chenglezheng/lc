package com.lc.clz.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private String userPwd;

    private Long userPhone;

    private BigDecimal userQq;

    private String userWechat;

    private String userEmail;

    private Integer userSex;

    private Short userAge;

    private String userAddress;

    private Integer userRole;

    private BigDecimal userLeftMoney;

    private String userRealName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public BigDecimal getUserQq() {
        return userQq;
    }

    public void setUserQq(BigDecimal userQq) {
        this.userQq = userQq;
    }

    public String getUserWechat() {
        return userWechat;
    }

    public void setUserWechat(String userWechat) {
        this.userWechat = userWechat == null ? null : userWechat.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Short getUserAge() {
        return userAge;
    }

    public void setUserAge(Short userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public BigDecimal getUserLeftMoney() {
        return userLeftMoney;
    }

    public void setUserLeftMoney(BigDecimal userLeftMoney) {
        this.userLeftMoney = userLeftMoney;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName == null ? null : userRealName.trim();
    }
}
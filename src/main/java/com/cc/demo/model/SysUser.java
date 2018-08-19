package com.cc.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "SYS_USER")
public class SysUser implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "LOGIN_NAME")
    private String loginName;

    @Column(name = "LOGIN_PASSWORD")
    private String loginPassword;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    @Column(name = "ENABLE")
    private Short enable;

    @Column(name = "STATUS")
    private Short status;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;

    @Column(name = "OFFICE_PHONE")
    private String officePhone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "REMARK")
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return CODE
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * @return LOGIN_NAME
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * @return LOGIN_PASSWORD
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * @param loginPassword
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return LAST_LOGIN_TIME
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return ENABLE
     */
    public Short getEnable() {
        return enable;
    }

    /**
     * @param enable
     */
    public void setEnable(Short enable) {
        this.enable = enable;
    }

    /**
     * @return STATUS
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * @return POSITION
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * @return MOBILE_PHONE
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * @return OFFICE_PHONE
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /**
     * @param officePhone
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone == null ? null : officePhone.trim();
    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
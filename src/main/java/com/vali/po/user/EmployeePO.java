package com.vali.po.user;

import java.util.Date;

/**
 * Created by vali on 15-8-15.
 */

public class EmployeePO {

    private Integer id;

    /**
     * 姓名
     */
    private String chineseName;

    /**
     * 英文名
     */
    private String firstName;

    /**
     * 英文名
     */
    private String middleName;

    /**
     * 英文名
     */
    private String lastName;

    /**
     * 部门代码
     */
    private Integer departMent;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 入职时间
     */
    private Date joinDate;

    /**
     * 员工状态
     */
    private int status;

    /**
     * 离职时间
     */
    private Date departureDate;

    /**
     * 直接上级
     */
    private Integer mangerId;

    private Date addTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getDepartMent() {
        return departMent;
    }

    public Integer getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public int getStatus() {
        return status;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Integer getMangerId() {
        return mangerId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartMent(Integer departMent) {
        this.departMent = departMent;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setMangerId(Integer mangerId) {
        this.mangerId = mangerId;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

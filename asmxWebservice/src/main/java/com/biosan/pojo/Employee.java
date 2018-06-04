package com.biosan.pojo;

import java.util.Date;

public class Employee {
    private Integer employeeid;

    private String employeename;

    private Integer departmentid;

    private Integer roleid;

    private Integer issign;

    private Integer mdepartmentid;

    private String employeecode;

    private Date operattime;

    private String email;

    private String password;

    private String employeeguid;

    private Integer state;

    private Integer isshow;

    private Integer isedit;

    private Date codetime;

    private Integer errornum;

    private Date locktime;

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename == null ? null : employeename.trim();
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getIssign() {
        return issign;
    }

    public void setIssign(Integer issign) {
        this.issign = issign;
    }

    public Integer getMdepartmentid() {
        return mdepartmentid;
    }

    public void setMdepartmentid(Integer mdepartmentid) {
        this.mdepartmentid = mdepartmentid;
    }

    public String getEmployeecode() {
        return employeecode;
    }

    public void setEmployeecode(String employeecode) {
        this.employeecode = employeecode == null ? null : employeecode.trim();
    }

    public Date getOperattime() {
        return operattime;
    }

    public void setOperattime(Date operattime) {
        this.operattime = operattime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmployeeguid() {
        return employeeguid;
    }

    public void setEmployeeguid(String employeeguid) {
        this.employeeguid = employeeguid == null ? null : employeeguid.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public Integer getIsedit() {
        return isedit;
    }

    public void setIsedit(Integer isedit) {
        this.isedit = isedit;
    }

    public Date getCodetime() {
        return codetime;
    }

    public void setCodetime(Date codetime) {
        this.codetime = codetime;
    }

    public Integer getErrornum() {
        return errornum;
    }

    public void setErrornum(Integer errornum) {
        this.errornum = errornum;
    }

    public Date getLocktime() {
        return locktime;
    }

    public void setLocktime(Date locktime) {
        this.locktime = locktime;
    }
}
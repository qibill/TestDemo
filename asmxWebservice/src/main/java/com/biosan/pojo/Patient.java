package com.biosan.pojo;

import java.util.Date;

public class Patient {
	
    private Integer patientid;

    private String identitycard;

    private String patientname;

    private Date birthday;

    private Integer race;

    private Integer addressid;

    private String address;

    private String postalcode;

    private String domicile;

    private Integer domicileid;

    private String domicileplace;

    private String pregnancynum;

    private Integer operator;

    private Date operattime;

    private Date lmpdate;

    private Integer isregular;

    private Integer lmpfrom;

    private Integer lmpto;

    private String cardnum;

    private Integer fdomicileid;

    private String openid;

    private Integer abmark;

    public Integer getPatientid() {
        return patientid;
    }

    public void setPatientid(Integer patientid) {
        this.patientid = patientid;
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard == null ? null : identitycard.trim();
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname == null ? null : patientname.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getRace() {
        return race;
    }

    public void setRace(Integer race) {
        this.race = race;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode == null ? null : postalcode.trim();
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile == null ? null : domicile.trim();
    }

    public Integer getDomicileid() {
        return domicileid;
    }

    public void setDomicileid(Integer domicileid) {
        this.domicileid = domicileid;
    }

    public String getDomicileplace() {
        return domicileplace;
    }

    public void setDomicileplace(String domicileplace) {
        this.domicileplace = domicileplace == null ? null : domicileplace.trim();
    }

    public String getPregnancynum() {
        return pregnancynum;
    }

    public void setPregnancynum(String pregnancynum) {
        this.pregnancynum = pregnancynum == null ? null : pregnancynum.trim();
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Date getOperattime() {
        return operattime;
    }

    public void setOperattime(Date operattime) {
        this.operattime = operattime;
    }

    public Date getLmpdate() {
        return lmpdate;
    }

    public void setLmpdate(Date lmpdate) {
        this.lmpdate = lmpdate;
    }

    public Integer getIsregular() {
        return isregular;
    }

    public void setIsregular(Integer isregular) {
        this.isregular = isregular;
    }

    public Integer getLmpfrom() {
        return lmpfrom;
    }

    public void setLmpfrom(Integer lmpfrom) {
        this.lmpfrom = lmpfrom;
    }

    public Integer getLmpto() {
        return lmpto;
    }

    public void setLmpto(Integer lmpto) {
        this.lmpto = lmpto;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    public Integer getFdomicileid() {
        return fdomicileid;
    }

    public void setFdomicileid(Integer fdomicileid) {
        this.fdomicileid = fdomicileid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getAbmark() {
        return abmark;
    }

    public void setAbmark(Integer abmark) {
        this.abmark = abmark;
    }
}
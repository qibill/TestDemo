package com.biosan.pojo;

import java.util.Date;

public class Newtouchtsscresult {
    private Integer tsscresultid;

    private Integer sampleid;

    private Integer tsscresult;

    private Date pdfdate;

    private Date sendattime;

    public Integer getTsscresultid() {
        return tsscresultid;
    }

    public void setTsscresultid(Integer tsscresultid) {
        this.tsscresultid = tsscresultid;
    }

    public Integer getSampleid() {
        return sampleid;
    }

    public void setSampleid(Integer sampleid) {
        this.sampleid = sampleid;
    }

    public Integer getTsscresult() {
        return tsscresult;
    }

    public void setTsscresult(Integer tsscresult) {
        this.tsscresult = tsscresult;
    }

    public Date getPdfdate() {
        return pdfdate;
    }

    public void setOperattime(Date pdfdate) {
        this.pdfdate = pdfdate;
    }

    public Date getSendattime() {
        return sendattime;
    }

    public void setSendattime(Date sendattime) {
        this.sendattime = sendattime;
    }
}
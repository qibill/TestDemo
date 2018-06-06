package com.biosan.pojo;

import java.util.Date;

public class Newtouchtsscresult {
    private Integer tsscresultid;

    private Integer sampleid;

    //1.发送成功    2.发送失败	 3.发送撤销命令失败
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

	@Override
	public String toString() {
		return "Newtouchtsscresult [tsscresultid=" + tsscresultid + ", sampleid=" + sampleid
				+ ", tsscresult=" + tsscresult + ", pdfdate=" + pdfdate + ", sendattime="
				+ sendattime + "]";
	}
    
}
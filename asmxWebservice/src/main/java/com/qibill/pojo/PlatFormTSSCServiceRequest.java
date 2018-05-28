package com.qibill.pojo;

import java.lang.reflect.Field;

import com.qibill.utils.XMLUtil;

public class PlatFormTSSCServiceRequest {

	private String hashKey = "c2d442a2-d458-434a-a650-e19ea1f35a79";
	private String invoketype = "01";
	private String ReportType = "1";
	private String Patid;
	private String PatNo;
	private String CardNo;
	private String Mpi;
	private String HospDiv;
	private String VistType;
	private String RecordID;
	private String PublishDate;
	private String CheckDate;
	private String DoctorAdvID;
	private String ReportNo;
		
	private Content content;
	
	private String FCD;
	private String Reqno;
	private String BarCode;
	private String czqf;
	private String PatName;
	private String PY;
	private String PrintDatetime;
	private String isPrint;
	public String getHashKey() {
		return hashKey;
	}
	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}
	public String getInvoketype() {
		return invoketype;
	}
	public void setInvoketype(String invoketype) {
		this.invoketype = invoketype;
	}
	public String getReportType() {
		return ReportType;
	}
	public void setReportType(String reportType) {
		ReportType = reportType;
	}
	public String getPatid() {
		return Patid;
	}
	public void setPatid(String patid) {
		Patid = patid;
	}
	public String getPatNo() {
		return PatNo;
	}
	public void setPatNo(String patNo) {
		PatNo = patNo;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getMpi() {
		return Mpi;
	}
	public void setMpi(String mpi) {
		Mpi = mpi;
	}
	public String getHospDiv() {
		return HospDiv;
	}
	public void setHospDiv(String hospDiv) {
		HospDiv = hospDiv;
	}
	public String getVistType() {
		return VistType;
	}
	public void setVistType(String vistType) {
		VistType = vistType;
	}
	public String getRecordID() {
		return RecordID;
	}
	public void setRecordID(String recordID) {
		RecordID = recordID;
	}
	public String getPublishDate() {
		return PublishDate;
	}
	public void setPublishDate(String publishDate) {
		PublishDate = publishDate;
	}
	public String getCheckDate() {
		return CheckDate;
	}
	public void setCheckDate(String checkDate) {
		CheckDate = checkDate;
	}
	public String getDoctorAdvID() {
		return DoctorAdvID;
	}
	public void setDoctorAdvID(String doctorAdvID) {
		DoctorAdvID = doctorAdvID;
	}
	public String getReportNo() {
		return ReportNo;
	}
	public void setReportNo(String reportNo) {
		ReportNo = reportNo;
	}
	public String getFCD() {
		return FCD;
	}
	public void setFCD(String fCD) {
		FCD = fCD;
	}
	public String getReqno() {
		return Reqno;
	}
	public void setReqno(String reqno) {
		Reqno = reqno;
	}
	public String getBarCode() {
		return BarCode;
	}
	public void setBarCode(String barCode) {
		BarCode = barCode;
	}
	public String getCzqf() {
		return czqf;
	}
	public void setCzqf(String czqf) {
		this.czqf = czqf;
	}
	public String getPatName() {
		return PatName;
	}
	public void setPatName(String patName) {
		PatName = patName;
	}
	public String getPY() {
		return PY;
	}
	public void setPY(String pY) {
		PY = pY;
	}
	public String getPrintDatetime() {
		return PrintDatetime;
	}
	public void setPrintDatetime(String printDatetime) {
		PrintDatetime = printDatetime;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public String toXml() {
		StringBuffer xml = new StringBuffer("<root>");
		xml.append("<head>");
		xml.append("<parameter>");
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields) {
			if ("content".equals(field.getName())) {
				xml.append(getContent().toXml());
				continue;
			}
			xml.append(XMLUtil.addElement(field.getName(), getClass(), this));
		}
		xml.append("</parameter>");
		xml.append("</head>");
		xml.append("<body />");
		xml.append("</root>");
		return xml.toString();				
	}

}

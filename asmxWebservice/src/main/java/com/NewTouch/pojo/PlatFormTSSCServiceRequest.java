package com.NewTouch.pojo;

import java.lang.reflect.Field;

import com.biosan.utils.XMLUtil;

public class PlatFormTSSCServiceRequest {

	/** 平台内部编码* 固定值 */
	private String hashKey = "c2d442a2-d458-434a-a650-e19ea1f35a79";
	/** 01 添加一条检验报告记录* 固定值 */
	private String invoketype = "01";
	/** 报告类型 * 固定值 */
	private String ReportType = "1";
	/** 患者ID * */
	private String Patid;
	private String PatNo;
	/** 就诊卡号 * */
	private String CardNo;
	private String Mpi;
	/** 院区编码 (0 西院,1 南院,2 东院) * */
	private String HospDiv = "0";
	/** 就诊类型 (1 门诊 2 住院)* */
	private String VistType = "1";
	private String RecordID;
	/** 发布时间/报告时间* */
	private String PublishDate;
	/** 检查时间* */
	private String CheckDate;
	private String DoctorAdvID;
	/** 报告唯一编号* */
	private String ReportNo;
		
	/** 报告内容* */
	private Content content;
	
	/** 当期操作时间* */
	private String FCD;
	/** 报告唯一编号* */
	private String Reqno;
	private String BarCode;
	/** 操作编码（1 新增 3 撤销）* */
	private String czqf;
	/** 患者姓名* */
	private String PatName;
	private String PY;
	private String PrintDatetime;
	private String isPrint;
	
	public PlatFormTSSCServiceRequest() {}
	public PlatFormTSSCServiceRequest(Content content) {
		this.content = content;
	}
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
		StringBuilder xml = new StringBuilder("<root>");
		xml.append("<head>");
		xml.append("<parameter>");
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields) {
			if ("content".equals(field.getName())) {
				xml.append(getContent().toXml());
				continue;
			}
			xml.append(XMLUtil.addElement(field.getName(), this));
		}
		xml.append("</parameter>");
		xml.append("</head>");
		xml.append("<body />");
		xml.append("</root>");
		return xml.toString();				
	}

}

package com.newtouch.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "row")
public class PatientDetailInfo {

	/** 患者唯一标识 */
	private String Patid;
	/** 就诊卡号 */
	private String CardNo;
//	private String OutPatiNo;
	/** 姓名 */
	private String PatName;
	/** 性别 */
	private String SexName;
	/** 末次月经 */
	private String LastMense;
	/** 年龄 */
	private String Age;
	/** 身份证号码 */
	private String IdCardNo;
	/** 联系方式 */
	private String Tel;
	/** 手机号 */
	private String Mobile;
	/** 身高 */
	private String Height;
	/** 体重 */
	private String Weight;
	/** 生育史 */
	private String ChildHistory;
	/** 过去史 */
	private String MedicalHistory;

	@JsonProperty("reserved2")
	public String getPatid() {
		return Patid;
	}

	public void setPatid(String patid) {
		Patid = patid;
	}
	
	@JsonProperty("cardnum")
	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	@JsonProperty("patientname")
	public String getPatName() {
		return PatName;
	}

//	public String getOutPatiNo() {
//		return OutPatiNo;
//	}
//
//	public void setOutPatiNo(String outPatiNo) {
//		OutPatiNo = outPatiNo;
//	}

	public void setPatName(String patName) {
		PatName = patName;
	}

	public String getSexName() {
		return SexName;
	}

	public void setSexName(String sexName) {
		SexName = sexName;
	}

	@JsonProperty("lmpdate")
	public String getLastMense() {
		return LastMense;
	}

	public void setLastMense(String lastMense) {
		LastMense = lastMense;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	@JsonProperty("identitycard")
	public String getIdCardNo() {
		return IdCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		IdCardNo = idCardNo;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	@JsonProperty("mobile")
	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getHeight() {
		return Height;
	}

	public void setHeight(String height) {
		Height = height;
	}

	public String getWeight() {
		return Weight;
	}

	public void setWeight(String weight) {
		Weight = weight;
	}

	public String getChildHistory() {
		return ChildHistory;
	}

	public void setChildHistory(String childHistory) {
		ChildHistory = childHistory;
	}

	public String getMedicalHistory() {
		return MedicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		MedicalHistory = medicalHistory;
	}
	
}

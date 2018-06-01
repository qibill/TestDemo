package com.NewTouch.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlRootElement(name = "row")    
public class PatientDetailInfo {

	/** 患者唯一标识 */
	private String Patid;
	/** 门诊号 */
	private String OutPatiNo;
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
	/** 查询结果标识（0 无数据返回，1 有数据返回 ） */
	private String Flag;
	/** 结果说明（数据返回成功为空，否则为错误提示） */
	private String Msg;
	
	public String getPatid() {
		return Patid;
	}
	public void setPatid(String patid) {
		Patid = patid;
	}
	public String getOutPatiNo() {
		return OutPatiNo;
	}
	public void setOutPatiNo(String outPatiNo) {
		OutPatiNo = outPatiNo;
	}
	public String getPatName() {
		return PatName;
	}
	public void setPatName(String patName) {
		PatName = patName;
	}
	public String getSexName() {
		return SexName;
	}
	public void setSexName(String sexName) {
		SexName = sexName;
	}
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
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	
	
}

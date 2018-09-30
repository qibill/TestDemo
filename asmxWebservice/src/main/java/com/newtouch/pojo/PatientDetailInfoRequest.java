package com.newtouch.pojo;

public class PatientDetailInfoRequest {

	/** 系统识别码(固定值) 16a839ed-b5f1-4148-b9a3-dab942a072e4 */
	private String hashKey = "16a839ed-b5f1-4148-b9a3-dab942a072e4";
	/** 就诊卡号 */
	private String CardNo = "";
	/** 身份证号 */
	private String IDCard = "999999999999999999";

	public String getHashKey() {
		return hashKey;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String toXml() {
		String xml = "<root>" + 
				"<head>" + 
				"<parameter>" + 
				"<hashKey>" + getHashKey() + "</hashKey>" + 
				"<CardNo>" + getCardNo() + "</CardNo>" + 
				"<IDCard>" + getIDCard() + "</IDCard>" + 
				"</parameter>" + 
				"</head>" + 
				"<body />" + 
				"</root>";
		return xml;
	}
}

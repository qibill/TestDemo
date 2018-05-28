package com.qibill.pojo;

public class PatientDetailInfoRequest {

	// 系统识别码(固定值) 16a839ed-b5f1-4148-b9a3-dab942a072e4
	private String hashKey = "16a839ed-b5f1-4148-b9a3-dab942a072e4";
	// 卡号
	private String CardNo;

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public String toXML() {
		String xml = "<?xml version=\"1.1\"  encoding=\"utf-8\"?>" + 
				"<root>" + 
				"<head>" + 
				"<parameter>" + 
				"<!-- 系统识别码 -->" + 
				"<hashKey>" + getHashKey() + "</hashKey>" + 
				"<!-- 就诊卡号 -->" + 
				"<CardNo>" + getCardNo() + "</CardNo>" + 
				"</parameter>" + 
				"</head>" + 
				"<body />" + 
				"</root>";
		return xml;
	}
}

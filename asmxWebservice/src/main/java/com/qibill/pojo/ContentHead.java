package com.qibill.pojo;

import java.lang.reflect.Field;

import com.qibill.utils.XMLUtil;

public class ContentHead {

	private String hashKey;
	private String yqqf;
	private String yzlb;
	private String czqf;
	private String bglx;
	private String jyrq;
	private String bgdh;
	
	public String getHashKey() {
		return hashKey;
	}
	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}
	public String getYqqf() {
		return yqqf;
	}
	public void setYqqf(String yqqf) {
		this.yqqf = yqqf;
	}
	public String getYzlb() {
		return yzlb;
	}
	public void setYzlb(String yzlb) {
		this.yzlb = yzlb;
	}
	public String getCzqf() {
		return czqf;
	}
	public void setCzqf(String czqf) {
		this.czqf = czqf;
	}
	public String getBglx() {
		return bglx;
	}
	public void setBglx(String bglx) {
		this.bglx = bglx;
	}
	public String getJyrq() {
		return jyrq;
	}
	public void setJyrq(String jyrq) {
		this.jyrq = jyrq;
	}
	public String getBgdh() {
		return bgdh;
	}
	public void setBgdh(String bgdh) {
		this.bgdh = bgdh;
	}
	
	public String toXml() {
		StringBuffer xml = new StringBuffer("<head>");
		xml.append("<parameter>");
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields) {
			xml.append(XMLUtil.addElement(field.getName(), getClass(), this));
		}
		xml.append("</parameter>");
		xml.append("</head>");
		return xml.toString();
	}

}

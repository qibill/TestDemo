package com.newtouch.pojo;

import java.lang.reflect.Field;

import com.biosan.utils.XMLUtil;

public class ContentHead {

	/** 平台内部编码* 固定值 */
	private String hashKey ="16a839ed-b5f1-4148-b9a3-dab942a072e4";
	/** 院区编码 (0 西院,1 南院,2 东院) * */
	private String yqqf;
	private String yzlb;
	/** 操作编码（1 新增 3 撤销）* */
	private Integer czqf;
	private String bglx;
	/** 检验日期* */
	private String jyrq;
	/** 报告单号（若无则使用 报告唯一编号）* */
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
	public Integer getCzqf() {
		return czqf;
	}
	public void setCzqf(Integer czqf) {
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
		StringBuilder xml = new StringBuilder("<head>");
		xml.append("<parameter>");
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields) {
			xml.append(XMLUtil.addElement(field.getName(), this));
		}
		xml.append("</parameter>");
		xml.append("</head>");
		return xml.toString();
	}
	@Override
	public String toString() {
		return "ContentHead [hashKey=" + hashKey + ", yqqf=" + yqqf + ", yzlb=" + yzlb + ", czqf="
				+ czqf + ", bglx=" + bglx + ", jyrq=" + jyrq + ", bgdh=" + bgdh + "]";
	}

}

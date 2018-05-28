package com.qibill.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.qibill.utils.XMLUtil;

public class ContentItem {

	/** 医院收费代码 */
	private String YYSFDM;
	/** 医保收费代码 */
	private String YBSFDM;
	/** 检查指标代码 */
	private String JCZBDM;
	/** 检查方法 */
	private String JCFF;
	/** 检查指标名称 */
	private String JCZBMC;
	/** 检查指标结果 */
	private String JCZBJG;
	/** 参考值 */
	private String CKZ;
	/** 记录参考值单位 */
	private String JLCKZDW;
	/** 异常提示 */
	private String YCTS;
	/** 设备编码 */
	private String SBBM;
	/** 仪器编码 */
	private String YQBM;
	/** 仪器名称 */
	private String YQMC;
	/** 检验日期 */
	private String JYRQ;
	/** 报告单号 */
	private String BGDH;
	/** 序列号 */
	private String REQNO;
	/** 项目序号  */
	private String SEQNO;
	
	public String getYYSFDM() {
		return YYSFDM;
	}
	public void setYYSFDM(String yYSFDM) {
		YYSFDM = yYSFDM;
	}
	public String getYBSFDM() {
		return YBSFDM;
	}
	public void setYBSFDM(String yBSFDM) {
		YBSFDM = yBSFDM;
	}
	public String getJCZBDM() {
		return JCZBDM;
	}
	public void setJCZBDM(String jCZBDM) {
		JCZBDM = jCZBDM;
	}
	public String getJCFF() {
		return JCFF;
	}
	public void setJCFF(String jCFF) {
		JCFF = jCFF;
	}
	public String getJCZBMC() {
		return JCZBMC;
	}
	public void setJCZBMC(String jCZBMC) {
		JCZBMC = jCZBMC;
	}
	public String getJCZBJG() {
		return JCZBJG;
	}
	public void setJCZBJG(String jCZBJG) {
		JCZBJG = jCZBJG;
	}
	public String getCKZ() {
		return CKZ;
	}
	public void setCKZ(String cKZ) {
		CKZ = cKZ;
	}
	public String getJLCKZDW() {
		return JLCKZDW;
	}
	public void setJLCKZDW(String jLCKZDW) {
		JLCKZDW = jLCKZDW;
	}
	public String getYCTS() {
		return YCTS;
	}
	public void setYCTS(String yCTS) {
		YCTS = yCTS;
	}
	public String getSBBM() {
		return SBBM;
	}
	public void setSBBM(String sBBM) {
		SBBM = sBBM;
	}
	public String getYQBM() {
		return YQBM;
	}
	public void setYQBM(String yQBM) {
		YQBM = yQBM;
	}
	public String getYQMC() {
		return YQMC;
	}
	public void setYQMC(String yQMC) {
		YQMC = yQMC;
	}
	public String getJYRQ() {
		return JYRQ;
	}
	public void setJYRQ(String jYRQ) {
		JYRQ = jYRQ;
	}
	public String getBGDH() {
		return BGDH;
	}
	public void setBGDH(String bGDH) {
		BGDH = bGDH;
	}
	public String getREQNO() {
		return REQNO;
	}
	public void setREQNO(String rEQNO) {
		REQNO = rEQNO;
	}
	public String getSEQNO() {
		return SEQNO;
	}
	public void setSEQNO(String sEQNO) {
		SEQNO = sEQNO;
	}
	
	public String toXml() {
		StringBuffer xml = new StringBuffer("<ROWS>");
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields) {
			xml.append(XMLUtil.addElement(field.getName(), getClass(), this));
		}
		xml.append("</ROWS>");
		return xml.toString();
	}
	
}

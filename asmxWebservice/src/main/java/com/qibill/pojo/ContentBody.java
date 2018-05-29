package com.qibill.pojo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.qibill.utils.XMLUtil;

public class ContentBody {

	/** 门诊住院标志 (1 门诊 2 住院，同VistType)* */
	private String MZZYBZ;
	private String BRANCHNO;
	/** 患者ID* */
	private String PATID;
	private String BLH;
	/** 报告单号（若无则使用 报告唯一编号）* */
	private String BGDH;
	/** 报告日期* */
	private String BGRQ;
	private String ADVID;
	/** 卡号* */
	private String KH;
	/** 患者姓名* */
	private String BRXM;
	private String BRXB;
	private String BRNL;
	private String NLDW;
	private String SQRGH;
	private String SQRXM;
	/** 报告人工号* */
	private String BGRGH;
	/** 报告人姓名* */
	private String BGRXM;
	/** 审核人工号* */
	private String SHRGH;
	/** 审核人姓名* */
	private String SHRXM;
	private String SQKS;
	private String BQ;
	private String CH;
	private String DYRQ;
	/** 申请日期* */
	private String SQRQ;
	/** 创建日期* */
	private String CJRQ;
	/** 检验日期* */
	private String JYRQ;
	/** 核实日期* */
	private String HSRQ;
	/** 标本代码* */
	private String BBDM;
	/** 标本名称* */
	private String BBMC;
	private String JZBZ;
	private String JYMD;
	private String ZXKS;
	private String LCZD;
	private String REMARK_INFO;
	private String JGZT;
	private String REQNO;
	/** 报告单名称* */
	private String BGDMC;
	
	private List<ContentItem> contentItems;

	public String getMZZYBZ() {
		return MZZYBZ;
	}

	public void setMZZYBZ(String mZZYBZ) {
		MZZYBZ = mZZYBZ;
	}

	public String getBRANCHNO() {
		return BRANCHNO;
	}

	public void setBRANCHNO(String bRANCHNO) {
		BRANCHNO = bRANCHNO;
	}

	public String getPATID() {
		return PATID;
	}

	public void setPATID(String pATID) {
		PATID = pATID;
	}

	public String getBLH() {
		return BLH;
	}

	public void setBLH(String bLH) {
		BLH = bLH;
	}

	public String getBGDH() {
		return BGDH;
	}

	public void setBGDH(String bGDH) {
		BGDH = bGDH;
	}

	public String getBGRQ() {
		return BGRQ;
	}

	public void setBGRQ(String bGRQ) {
		BGRQ = bGRQ;
	}

	public String getADVID() {
		return ADVID;
	}

	public void setADVID(String aDVID) {
		ADVID = aDVID;
	}

	public String getKH() {
		return KH;
	}

	public void setKH(String kH) {
		KH = kH;
	}

	public String getBRXM() {
		return BRXM;
	}

	public void setBRXM(String bRXM) {
		BRXM = bRXM;
	}

	public String getBRXB() {
		return BRXB;
	}

	public void setBRXB(String bRXB) {
		BRXB = bRXB;
	}

	public String getBRNL() {
		return BRNL;
	}

	public void setBRNL(String bRNL) {
		BRNL = bRNL;
	}

	public String getNLDW() {
		return NLDW;
	}

	public void setNLDW(String nLDW) {
		NLDW = nLDW;
	}

	public String getSQRGH() {
		return SQRGH;
	}

	public void setSQRGH(String sQRGH) {
		SQRGH = sQRGH;
	}

	public String getSQRXM() {
		return SQRXM;
	}

	public void setSQRXM(String sQRXM) {
		SQRXM = sQRXM;
	}

	public String getBGRGH() {
		return BGRGH;
	}

	public void setBGRGH(String bGRGH) {
		BGRGH = bGRGH;
	}

	public String getBGRXM() {
		return BGRXM;
	}

	public void setBGRXM(String bGRXM) {
		BGRXM = bGRXM;
	}

	public String getSHRGH() {
		return SHRGH;
	}

	public void setSHRGH(String sHRGH) {
		SHRGH = sHRGH;
	}

	public String getSHRXM() {
		return SHRXM;
	}

	public void setSHRXM(String sHRXM) {
		SHRXM = sHRXM;
	}

	public String getSQKS() {
		return SQKS;
	}

	public void setSQKS(String sQKS) {
		SQKS = sQKS;
	}

	public String getBQ() {
		return BQ;
	}

	public void setBQ(String bQ) {
		BQ = bQ;
	}

	public String getCH() {
		return CH;
	}

	public void setCH(String cH) {
		CH = cH;
	}

	public String getDYRQ() {
		return DYRQ;
	}

	public void setDYRQ(String dYRQ) {
		DYRQ = dYRQ;
	}

	public String getSQRQ() {
		return SQRQ;
	}

	public void setSQRQ(String sQRQ) {
		SQRQ = sQRQ;
	}

	public String getCJRQ() {
		return CJRQ;
	}

	public void setCJRQ(String cJRQ) {
		CJRQ = cJRQ;
	}

	public String getJYRQ() {
		return JYRQ;
	}

	public void setJYRQ(String jYRQ) {
		JYRQ = jYRQ;
	}

	public String getHSRQ() {
		return HSRQ;
	}

	public void setHSRQ(String hSRQ) {
		HSRQ = hSRQ;
	}

	public String getBBDM() {
		return BBDM;
	}

	public void setBBDM(String bBDM) {
		BBDM = bBDM;
	}

	public String getBBMC() {
		return BBMC;
	}

	public void setBBMC(String bBMC) {
		BBMC = bBMC;
	}

	public String getJZBZ() {
		return JZBZ;
	}

	public void setJZBZ(String jZBZ) {
		JZBZ = jZBZ;
	}

	public String getJYMD() {
		return JYMD;
	}

	public void setJYMD(String jYMD) {
		JYMD = jYMD;
	}

	public String getZXKS() {
		return ZXKS;
	}

	public void setZXKS(String zXKS) {
		ZXKS = zXKS;
	}

	public String getLCZD() {
		return LCZD;
	}

	public void setLCZD(String lCZD) {
		LCZD = lCZD;
	}

	public String getREMARK_INFO() {
		return REMARK_INFO;
	}

	public void setREMARK_INFO(String rEMARK_INFO) {
		REMARK_INFO = rEMARK_INFO;
	}

	public String getJGZT() {
		return JGZT;
	}

	public void setJGZT(String jGZT) {
		JGZT = jGZT;
	}

	public String getREQNO() {
		return REQNO;
	}

	public void setREQNO(String rEQNO) {
		REQNO = rEQNO;
	}

	public String getBGDMC() {
		return BGDMC;
	}

	public void setBGDMC(String bGDMC) {
		BGDMC = bGDMC;
	}

	public List<ContentItem> getContentItems() {
		return contentItems == null ? new ArrayList<ContentItem>() : contentItems;
	}

	public void setContentITEMS(List<ContentItem> contentItems) {
		this.contentItems = contentItems;
	}
	
	public String toXml() {
		StringBuffer xml = new StringBuffer("<body>");
		xml.append("<result>OK</result>");
		xml.append("<DataTable>");
		xml.append("<ROW>");
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields) {
			if ("contentItems".equals(field.getName())) {
				continue;
			}
			xml.append(XMLUtil.addElement(field.getName(), getClass(), this));
		}
		xml.append("</ROW>");
		if (getContentItems().size() > 0) {
			xml.append("<ITEMS>");
			for (ContentItem contentItem : getContentItems()) {
				xml.append(contentItem.toXml());
			}
			xml.append("</ITEMS>");			
		} else {
			xml.append("<ITEMS />");
		}
		xml.append("</DataTable>");
		xml.append("</body>");
		return xml.toString();
	}
	
}

package com.qibill.pojo;

public class ContentITEM {

	private String YYSFDM;
	private String YBSFDM;
	private String JCZBDM;
	private String JCFF;
	private String JCZBMC;
	private String JCZBJG;
	private String CKZ;
	private String JLCKZDW;
	private String YCTS;
	private String SBBM;
	private String YQBM;
	private String YQMC;
	private String JYRQ;
	private String BGDH;
	private String REQNO;
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
		String xml = "<ROWS>" + 
					"<YYSFDM>" + getYYSFDM() + "</YYSFDM>" + 
					"<YBSFDM />" + 
					"<JCZBDM>" + getJCZBDM() + "</JCZBDM>" + 
					"<JCFF />" + 
					"<JCZBMC>" + getJCZBMC() + "</JCZBMC>" + 
					"<JCZBJG>" + getJCZBJG() + "</JCZBJG>" + 
					"<CKZ />" + 
					"<JLCKZDW>" + getJLCKZDW() + "</JLCKZDW>" + 
					"<YCTS />" + 
					"<SBBM>" + getSBBM() + "</SBBM>" + 
					"<YQBM>" + getYQBM() + "</YQBM>" + 
					"<YQMC>" + getYQMC() + "</YQMC>" + 
					"<JYRQ>" + getJYRQ() + "</JYRQ>" + 
					"<BGDH>" + getBGDH() + "</BGDH>" + 
					"<REQNO>" + getREQNO() + "</REQNO>" + 
					"<SEQNO>" + getSEQNO() + "</SEQNO>" + 
				"</ROWS>";
		return xml;
	}
}

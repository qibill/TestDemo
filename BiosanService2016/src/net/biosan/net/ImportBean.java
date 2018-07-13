package net.biosan.net;

public class ImportBean {
	String barcode = "";
	String collectenum = "";
	String bednum = "";
	String contacter = "";
	String reserved1="";
	String reserved0="";
	String reserved2="";
	String reserved3="";
	String reserved6="";
	String reserved7="";
	String domicile="";
	
	String samplestring = "";
	String collectnum = "";
	String patientname = "";
	String birthday = "";
	String mobile = "";
	String telephone = "";
	String sex = "";
	String preg_realcycle = "";
	String childname = "";
	String address = "";
	String weight = "";
	
	String target_msms = "";
	String msms_result = "";
	
	String doc = "";
	String receivedate = "";
	String pre_num = "";
	String born_num = "";
	String operatedate = "";
	String phe_lab_date = "";
	String ohp_lab_date = "";
	String tsh_lab_date = "";
	String g6pd_lab_date = "";
	String msms_lab_date = "";
	String key = "";
	String ill_thyroid = "";
	String sampleid = "";
	String labno = "";
	String printcount = "";
	String isrepeat = "";
	String msms_attribute = "";
	String phe_reason = "";
	String tsh_reason = "";
	String ohp_reason = "";
	String g6pd_reason = "";
	String msms_reason = "";
	String conclusion_phe__result = "";
	String conclusion_msms__result = "";
	String barcodestring = "";
	String area_ul = "";
	String issend = "";
	String pregcarenum = "";
	String illhisguid = "";
	String firstdeliverydate = "";
	String isverify = "";
	String pdfaddress = "";
	String isfree = "";
	String isdeaf = "";
	String business = "";
	String departmentid = "";
	String operatname = "";
	String checkdate = "";
	String attribute = "";
	String dateinterval = "";
	String centername = "";

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	String admissionnum = "";
	String experimentitem = "";
	String collectedate = "";
	String operdate = "";
	String area = "";
	String hos = "";
	String household = "";
	String housetype = "";
	String remark = "";
	String paramer1 = "";
	String paramer2 = "";
	String paramer3 = "";
	String paramer4 = "";
	String paramer5 = "";
	String paramer6 = "";
	String bron_type = "";
	String apgar = "";
	String antibiotic = "";
	String istosix = "";
	
	String collector = "";  // 采血人
	String postcode = "";  // 邮编 

	String ZLRQ = ""; 		// 实际治疗日期
	String ZLCS = "";  		// 治疗次数
	String ZLYYMC = "";  	// 确诊单位
	String ZLYS = "";  		// 治疗医生
	String ZDJG = "";  		// 检测结果
	String ZLFF = "";  		// 检测方法
	String ZLJG = "";  		// 治疗方案
	String XCZLYYRQ = "";	// 下次治疗预约日期
	String BL1 = "";		// 用药和食谱
	String BL2 = "";		// 医嘱
	
	String DIAGNOSE_DATE = "";   // 诊断日期
	String CHECK_DEPARTMENT = "";// 检测单位
	String DIAGNOSE_RESULT = ""; // 诊断结论
	String DIAGNOSE_METHOD = ""; // 诊断方法
	String DIAGNOSE_REMARK = ""; // 诊断备注
	String DIAGNOSE_VALUE = "";  // 诊断值
	String BZHONG = "";    	  	 // 疾病
	
	String notice_date = "";
	String reserve_date = "";
	String notice_content = "";
	
	String labdata = "";
	
	String expensetype = "";
	
	public String getReserved0() {
		return reserved0;
	}

	public void setReserved0(String reserved0) {
		this.reserved0 = reserved0;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getReserved6() {
		return reserved6;
	}

	public void setReserved6(String reserved6) {
		this.reserved6 = reserved6;
	}

	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}

	public String getExpensetype() {
		return expensetype;
	}

	public void setExpensetype(String expensetype) {
		this.expensetype = expensetype;
	}

	public String getBZHONG() {
		return BZHONG;
	}

	public void setBZHONG(String bZHONG) {
		BZHONG = bZHONG;
	}

	public String getLabdata() {
		return labdata;
	}

	public void setLabdata(String labdata) {
		this.labdata = labdata;
	}

	public String getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(String noticeDate) {
		notice_date = noticeDate;
	}

	public String getReserve_date() {
		return reserve_date;
	}

	public void setReserve_date(String reserveDate) {
		reserve_date = reserveDate;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String noticeContent) {
		notice_content = noticeContent;
	}

	public String getDIAGNOSE_DATE() {
		return DIAGNOSE_DATE;
	}

	public void setDIAGNOSE_DATE(String dIAGNOSEDATE) {
		DIAGNOSE_DATE = dIAGNOSEDATE;
	}

	public String getCHECK_DEPARTMENT() {
		return CHECK_DEPARTMENT;
	}

	public void setCHECK_DEPARTMENT(String cHECKDEPARTMENT) {
		CHECK_DEPARTMENT = cHECKDEPARTMENT;
	}

	public String getDIAGNOSE_RESULT() {
		return DIAGNOSE_RESULT;
	}

	public void setDIAGNOSE_RESULT(String dIAGNOSERESULT) {
		DIAGNOSE_RESULT = dIAGNOSERESULT;
	}

	public String getDIAGNOSE_METHOD() {
		return DIAGNOSE_METHOD;
	}

	public void setDIAGNOSE_METHOD(String dIAGNOSEMETHOD) {
		DIAGNOSE_METHOD = dIAGNOSEMETHOD;
	}

	public String getDIAGNOSE_REMARK() {
		return DIAGNOSE_REMARK;
	}

	public void setDIAGNOSE_REMARK(String dIAGNOSEREMARK) {
		DIAGNOSE_REMARK = dIAGNOSEREMARK;
	}

	public String getDIAGNOSE_VALUE() {
		return DIAGNOSE_VALUE;
	}

	public void setDIAGNOSE_VALUE(String dIAGNOSEVALUE) {
		DIAGNOSE_VALUE = dIAGNOSEVALUE;
	}

	public String getZLRQ() {
		return ZLRQ;
	}

	public void setZLRQ(String zLRQ) {
		ZLRQ = zLRQ;
	}

	public String getZLCS() {
		return ZLCS;
	}

	public void setZLCS(String zLCS) {
		ZLCS = zLCS;
	}

	public String getZLYYMC() {
		return ZLYYMC;
	}

	public void setZLYYMC(String zLYYMC) {
		ZLYYMC = zLYYMC;
	}

	public String getZLYS() {
		return ZLYS;
	}

	public void setZLYS(String zLYS) {
		ZLYS = zLYS;
	}

	public String getZDJG() {
		return ZDJG;
	}

	public void setZDJG(String zDJG) {
		ZDJG = zDJG;
	}

	public String getZLFF() {
		return ZLFF;
	}

	public void setZLFF(String zLFF) {
		ZLFF = zLFF;
	}

	public String getZLJG() {
		return ZLJG;
	}

	public void setZLJG(String zLJG) {
		ZLJG = zLJG;
	}

	public String getXCZLYYRQ() {
		return XCZLYYRQ;
	}

	public void setXCZLYYRQ(String xCZLYYRQ) {
		XCZLYYRQ = xCZLYYRQ;
	}

	public String getBL1() {
		return BL1;
	}

	public void setBL1(String bL1) {
		BL1 = bL1;
	}

	public String getBL2() {
		return BL2;
	}

	public void setBL2(String bL2) {
		BL2 = bL2;
	}

	public String getCollector() {
		return collector;
	}

	public void setCollector(String collector) {
		this.collector = collector;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getApgar() {
		return apgar;
	}

	public void setApgar(String apgar) {
		this.apgar = apgar;
	}

	public String getAntibiotic() {
		return antibiotic;
	}

	public void setAntibiotic(String antibiotic) {
		this.antibiotic = antibiotic;
	}

	public String getIstosix() {
		return istosix;
	}

	public void setIstosix(String istosix) {
		this.istosix = istosix;
	}

	public String getBron_type() {
		return bron_type;
	}

	public void setBron_type(String bronType) {
		bron_type = bronType;
	}

	public String getParamer1() {
		return paramer1;
	}

	public void setParamer1(String paramer1) {
		this.paramer1 = paramer1;
	}

	public String getParamer2() {
		return paramer2;
	}

	public void setParamer2(String paramer2) {
		this.paramer2 = paramer2;
	}

	public String getParamer3() {
		return paramer3;
	}

	public void setParamer3(String paramer3) {
		this.paramer3 = paramer3;
	}

	public String getParamer4() {
		return paramer4;
	}

	public void setParamer4(String paramer4) {
		this.paramer4 = paramer4;
	}

	public String getParamer5() {
		return paramer5;
	}

	public void setParamer5(String paramer5) {
		this.paramer5 = paramer5;
	}

	public String getParamer6() {
		return paramer6;
	}

	public void setParamer6(String paramer6) {
		this.paramer6 = paramer6;
	}

	public String getSamplestring() {
		return samplestring;
	}

	public void setSamplestring(String samplestring) {
		this.samplestring = samplestring;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCollectenum() {
		return collectenum;
	}

	public void setCollectenum(String collectenum) {
		this.collectenum = collectenum;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPreg_realcycle() {
		return preg_realcycle;
	}

	public void setPreg_realcycle(String preg_realcycle) {
		this.preg_realcycle = preg_realcycle;
	}

	public String getChildname() {
		return childname;
	}

	public void setChildname(String childname) {
		this.childname = childname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBednum() {
		return bednum;
	}

	public void setBednum(String bednum) {
		this.bednum = bednum;
	}

	public String getAdmissionnum() {
		return admissionnum;
	}

	public void setAdmissionnum(String admissionnum) {
		this.admissionnum = admissionnum;
	}

	public String getExperimentitem() {
		return experimentitem;
	}

	public void setExperimentitem(String experimentitem) {
		this.experimentitem = experimentitem;
	}

	public String getCollectedate() {
		return collectedate;
	}

	public void setCollectedate(String collectedate) {
		this.collectedate = collectedate;
	}

	public String getOperdate() {
		return operdate;
	}

	public void setOperdate(String operdate) {
		this.operdate = operdate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getHos() {
		return hos;
	}

	public void setHos(String hos) {
		this.hos = hos;
	}

	public String getHousehold() {
		return household;
	}

	public void setHousehold(String household) {
		this.household = household;
	}

	public String getHousetype() {
		return housetype;
	}

	public void setHousetype(String housetype) {
		this.housetype = housetype;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	String serviceid = "";

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	String target_phe = "";
	String target_tsh = "";
	String target_17_a_ohp = "";
	String target_g6pd = "";
	String phe_result = "";
	String tsh_result = "";
	String g6pd_result = "";
	String ohp_result = "";
	String pdfdate = "";
	String labitemname = "";

	public String getLabitemname() {
		return labitemname;
	}

	public void setLabitemname(String labitemname) {
		this.labitemname = labitemname;
	}

	public String getTarget_phe() {
		return target_phe;
	}

	public void setTarget_phe(String targetPhe) {
		target_phe = targetPhe;
	}

	public String getTarget_tsh() {
		return target_tsh;
	}

	public void setTarget_tsh(String targetTsh) {
		target_tsh = targetTsh;
	}

	public String getTarget_17_a_ohp() {
		return target_17_a_ohp;
	}

	public void setTarget_17_a_ohp(String target_17AOhp) {
		target_17_a_ohp = target_17AOhp;
	}

	public String getTarget_g6pd() {
		return target_g6pd;
	}

	public void setTarget_g6pd(String targetG6pd) {
		target_g6pd = targetG6pd;
	}

	public String getPhe_result() {
		return phe_result;
	}

	public void setPhe_result(String pheResult) {
		phe_result = pheResult;
	}

	public String getTsh_result() {
		return tsh_result;
	}

	public void setTsh_result(String tshResult) {
		tsh_result = tshResult;
	}

	public String getG6pd_result() {
		return g6pd_result;
	}

	public void setG6pd_result(String g6pdResult) {
		g6pd_result = g6pdResult;
	}

	public String getOhp_result() {
		return ohp_result;
	}

	public void setOhp_result(String ohpResult) {
		ohp_result = ohpResult;
	}

	public String getPdfdate() {
		return pdfdate;
	}

	public void setPdfdate(String pdfdate) {
		this.pdfdate = pdfdate;
	}

	String cardtypeid = "";
	String cardtype = "";

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardtypeid() {
		return cardtypeid;
	}

	public void setCardtypeid(String cardtypeid) {
		this.cardtypeid = cardtypeid;
	}

	/**
	 * @return the collectnum
	 */
	public String getCollectnum() {
		return collectnum;
	}

	/**
	 * @param collectnum the collectnum to set
	 */
	public void setCollectnum(String collectnum) {
		this.collectnum = collectnum;
	}

	/**
	 * @return the target_msms
	 */
	public String getTarget_msms() {
		return target_msms;
	}

	/**
	 * @param targetMsms the target_msms to set
	 */
	public void setTarget_msms(String targetMsms) {
		target_msms = targetMsms;
	}

	/**
	 * @return the msms_result
	 */
	public String getMsms_result() {
		return msms_result;
	}

	/**
	 * @param msmsResult the msms_result to set
	 */
	public void setMsms_result(String msmsResult) {
		msms_result = msmsResult;
	}

	/**
	 * @return the doc
	 */
	public String getDoc() {
		return doc;
	}

	/**
	 * @param doc the doc to set
	 */
	public void setDoc(String doc) {
		this.doc = doc;
	}

	/**
	 * @return the receivedate
	 */
	public String getReceivedate() {
		return receivedate;
	}

	/**
	 * @param receivedate the receivedate to set
	 */
	public void setReceivedate(String receivedate) {
		this.receivedate = receivedate;
	}

	/**
	 * @return the pre_num
	 */
	public String getPre_num() {
		return pre_num;
	}

	/**
	 * @param preNum the pre_num to set
	 */
	public void setPre_num(String preNum) {
		pre_num = preNum;
	}
	
	public String getBorn_num() {
		return born_num;
	}

	public void setBorn_num(String bornNum) {
		born_num = bornNum;
	}

	/**
	 * @return the operatedate
	 */
	public String getOperatedate() {
		return operatedate;
	}

	/**
	 * @param operatedate the operatedate to set
	 */
	public void setOperatedate(String operatedate) {
		this.operatedate = operatedate;
	}

	/**
	 * @return the phe_lab_date
	 */
	public String getPhe_lab_date() {
		return phe_lab_date;
	}

	/**
	 * @param pheLabDate the phe_lab_date to set
	 */
	public void setPhe_lab_date(String pheLabDate) {
		phe_lab_date = pheLabDate;
	}

	/**
	 * @return the ohp_lab_date
	 */
	public String getOhp_lab_date() {
		return ohp_lab_date;
	}

	/**
	 * @param ohpLabDate the ohp_lab_date to set
	 */
	public void setOhp_lab_date(String ohpLabDate) {
		ohp_lab_date = ohpLabDate;
	}

	/**
	 * @return the tsh_lab_date
	 */
	public String getTsh_lab_date() {
		return tsh_lab_date;
	}

	/**
	 * @param tshLabDate the tsh_lab_date to set
	 */
	public void setTsh_lab_date(String tshLabDate) {
		tsh_lab_date = tshLabDate;
	}

	/**
	 * @return the g6pd_lab_date
	 */
	public String getG6pd_lab_date() {
		return g6pd_lab_date;
	}

	/**
	 * @param g6pdLabDate the g6pd_lab_date to set
	 */
	public void setG6pd_lab_date(String g6pdLabDate) {
		g6pd_lab_date = g6pdLabDate;
	}

	/**
	 * @return the msms_lab_date
	 */
	public String getMsms_lab_date() {
		return msms_lab_date;
	}

	/**
	 * @param msmsLabDate the msms_lab_date to set
	 */
	public void setMsms_lab_date(String msmsLabDate) {
		msms_lab_date = msmsLabDate;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the ill_thyroid
	 */
	public String getIll_thyroid() {
		return ill_thyroid;
	}

	/**
	 * @param illThyroid the ill_thyroid to set
	 */
	public void setIll_thyroid(String illThyroid) {
		ill_thyroid = illThyroid;
	}

	/**
	 * @return the sampleid
	 */
	public String getSampleid() {
		return sampleid;
	}

	/**
	 * @param sampleid the sampleid to set
	 */
	public void setSampleid(String sampleid) {
		this.sampleid = sampleid;
	}

	/**
	 * @return the labno
	 */
	public String getLabno() {
		return labno;
	}

	/**
	 * @param labno the labno to set
	 */
	public void setLabno(String labno) {
		this.labno = labno;
	}

	/**
	 * @return the printcount
	 */
	public String getPrintcount() {
		return printcount;
	}

	/**
	 * @param printcount the printcount to set
	 */
	public void setPrintcount(String printcount) {
		this.printcount = printcount;
	}

	/**
	 * @return the isrepeat
	 */
	public String getIsrepeat() {
		return isrepeat;
	}

	/**
	 * @param isrepeat the isrepeat to set
	 */
	public void setIsrepeat(String isrepeat) {
		this.isrepeat = isrepeat;
	}

	/**
	 * @return the msms_attribute
	 */
	public String getMsms_attribute() {
		return msms_attribute;
	}

	/**
	 * @param msmsAttribute the msms_attribute to set
	 */
	public void setMsms_attribute(String msmsAttribute) {
		msms_attribute = msmsAttribute;
	}

	/**
	 * @return the phe_reason
	 */
	public String getPhe_reason() {
		return phe_reason;
	}

	/**
	 * @param pheReason the phe_reason to set
	 */
	public void setPhe_reason(String pheReason) {
		phe_reason = pheReason;
	}

	/**
	 * @return the tsh_reason
	 */
	public String getTsh_reason() {
		return tsh_reason;
	}

	/**
	 * @param tshReason the tsh_reason to set
	 */
	public void setTsh_reason(String tshReason) {
		tsh_reason = tshReason;
	}

	/**
	 * @return the ohp_reason
	 */
	public String getOhp_reason() {
		return ohp_reason;
	}

	/**
	 * @param ohpReason the ohp_reason to set
	 */
	public void setOhp_reason(String ohpReason) {
		ohp_reason = ohpReason;
	}

	/**
	 * @return the g6pd_reason
	 */
	public String getG6pd_reason() {
		return g6pd_reason;
	}

	/**
	 * @param g6pdReason the g6pd_reason to set
	 */
	public void setG6pd_reason(String g6pdReason) {
		g6pd_reason = g6pdReason;
	}

	/**
	 * @return the msms_reason
	 */
	public String getMsms_reason() {
		return msms_reason;
	}

	/**
	 * @param msmsReason the msms_reason to set
	 */
	public void setMsms_reason(String msmsReason) {
		msms_reason = msmsReason;
	}

	/**
	 * @return the conclusion_phe__result
	 */
	public String getConclusion_phe__result() {
		return conclusion_phe__result;
	}

	/**
	 * @param conclusionPheResult the conclusion_phe__result to set
	 */
	public void setConclusion_phe__result(String conclusionPheResult) {
		conclusion_phe__result = conclusionPheResult;
	}

	/**
	 * @return the conclusion_msms__result
	 */
	public String getConclusion_msms__result() {
		return conclusion_msms__result;
	}

	/**
	 * @param conclusionMsmsResult the conclusion_msms__result to set
	 */
	public void setConclusion_msms__result(String conclusionMsmsResult) {
		conclusion_msms__result = conclusionMsmsResult;
	}

	/**
	 * @return the barcodestring
	 */
	public String getBarcodestring() {
		return barcodestring;
	}

	/**
	 * @param barcodestring the barcodestring to set
	 */
	public void setBarcodestring(String barcodestring) {
		this.barcodestring = barcodestring;
	}

	/**
	 * @return the area_ul
	 */
	public String getArea_ul() {
		return area_ul;
	}

	/**
	 * @param areaUl the area_ul to set
	 */
	public void setArea_ul(String areaUl) {
		area_ul = areaUl;
	}

	/**
	 * @return the issend
	 */
	public String getIssend() {
		return issend;
	}

	/**
	 * @param issend the issend to set
	 */
	public void setIssend(String issend) {
		this.issend = issend;
	}

	/**
	 * @return the pregcarenum
	 */
	public String getPregcarenum() {
		return pregcarenum;
	}

	/**
	 * @param pregcarenum the pregcarenum to set
	 */
	public void setPregcarenum(String pregcarenum) {
		this.pregcarenum = pregcarenum;
	}

	/**
	 * @return the illhisguid
	 */
	public String getIllhisguid() {
		return illhisguid;
	}

	/**
	 * @param illhisguid the illhisguid to set
	 */
	public void setIllhisguid(String illhisguid) {
		this.illhisguid = illhisguid;
	}

	/**
	 * @return the firstdeliverydate
	 */
	public String getFirstdeliverydate() {
		return firstdeliverydate;
	}

	/**
	 * @param firstdeliverydate the firstdeliverydate to set
	 */
	public void setFirstdeliverydate(String firstdeliverydate) {
		this.firstdeliverydate = firstdeliverydate;
	}

	/**
	 * @return the isverify
	 */
	public String getIsverify() {
		return isverify;
	}

	/**
	 * @param isverify the isverify to set
	 */
	public void setIsverify(String isverify) {
		this.isverify = isverify;
	}

	/**
	 * @return the pdfaddress
	 */
	public String getPdfaddress() {
		return pdfaddress;
	}

	/**
	 * @param pdfaddress the pdfaddress to set
	 */
	public void setPdfaddress(String pdfaddress) {
		this.pdfaddress = pdfaddress;
	}

	/**
	 * @return the isfree
	 */
	public String getIsfree() {
		return isfree;
	}

	/**
	 * @param isfree the isfree to set
	 */
	public void setIsfree(String isfree) {
		this.isfree = isfree;
	}

	/**
	 * @return the isdeaf
	 */
	public String getIsdeaf() {
		return isdeaf;
	}

	/**
	 * @param isdeaf the isdeaf to set
	 */
	public void setIsdeaf(String isdeaf) {
		this.isdeaf = isdeaf;
	}

	/**
	 * @return the business
	 */
	public String getBusiness() {
		return business;
	}

	/**
	 * @param business the business to set
	 */
	public void setBusiness(String business) {
		this.business = business;
	}

	/**
	 * @return the departmentid
	 */
	public String getDepartmentid() {
		return departmentid;
	}

	/**
	 * @param departmentid the departmentid to set
	 */
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	/**
	 * @return the operatname
	 */
	public String getOperatname() {
		return operatname;
	}

	/**
	 * @param operatname the operatname to set
	 */
	public void setOperatname(String operatname) {
		this.operatname = operatname;
	}

	/**
	 * @return the checkdate
	 */
	public String getCheckdate() {
		return checkdate;
	}

	/**
	 * @param checkdate the checkdate to set
	 */
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return the dateinterval
	 */
	public String getDateinterval() {
		return dateinterval;
	}

	/**
	 * @param dateinterval the dateinterval to set
	 */
	public void setDateinterval(String dateinterval) {
		this.dateinterval = dateinterval;
	}

	/**
	 * @return the centername
	 */
	public String getCentername() {
		return centername;
	}

	/**
	 * @param centername the centername to set
	 */
	public void setCentername(String centername) {
		this.centername = centername;
	}
	
	

	public String getReserved7() {
		return reserved7;
	}

	public void setReserved7(String reserved7) {
		this.reserved7 = reserved7;
	}

	@Override
	public String toString() {
		return "ImportBean [BL1=" + BL1 + ", BL2=" + BL2 + ", BZHONG=" + BZHONG
				+ ", CHECK_DEPARTMENT=" + CHECK_DEPARTMENT + ", DIAGNOSE_DATE=" + DIAGNOSE_DATE
				+ ", DIAGNOSE_METHOD=" + DIAGNOSE_METHOD + ", DIAGNOSE_REMARK=" + DIAGNOSE_REMARK
				+ ", DIAGNOSE_RESULT=" + DIAGNOSE_RESULT + ", DIAGNOSE_VALUE=" + DIAGNOSE_VALUE
				+ ", XCZLYYRQ=" + XCZLYYRQ + ", ZDJG=" + ZDJG + ", ZLCS=" + ZLCS + ", ZLFF=" + ZLFF
				+ ", ZLJG=" + ZLJG + ", ZLRQ=" + ZLRQ + ", ZLYS=" + ZLYS + ", ZLYYMC=" + ZLYYMC
				+ ", address=" + address + ", admissionnum=" + admissionnum + ", antibiotic="
				+ antibiotic + ", apgar=" + apgar + ", area=" + area + ", area_ul=" + area_ul
				+ ", attribute=" + attribute + ", barcode=" + barcode + ", barcodestring="
				+ barcodestring + ", bednum=" + bednum + ", birthday=" + birthday + ", born_num="
				+ born_num + ", bron_type=" + bron_type + ", business=" + business + ", cardtype="
				+ cardtype + ", cardtypeid=" + cardtypeid + ", centername=" + centername
				+ ", checkdate=" + checkdate + ", childname=" + childname + ", collectedate="
				+ collectedate + ", collectenum=" + collectenum + ", collectnum=" + collectnum
				+ ", collector=" + collector + ", conclusion_msms__result="
				+ conclusion_msms__result + ", conclusion_phe__result=" + conclusion_phe__result
				+ ", contacter=" + contacter + ", dateinterval=" + dateinterval + ", departmentid="
				+ departmentid + ", doc=" + doc + ", domicile=" + domicile + ", expensetype="
				+ expensetype + ", experimentitem=" + experimentitem + ", firstdeliverydate="
				+ firstdeliverydate + ", g6pd_lab_date=" + g6pd_lab_date + ", g6pd_reason="
				+ g6pd_reason + ", g6pd_result=" + g6pd_result + ", hos=" + hos + ", household="
				+ household + ", housetype=" + housetype + ", ill_thyroid=" + ill_thyroid
				+ ", illhisguid=" + illhisguid + ", isdeaf=" + isdeaf + ", isfree=" + isfree
				+ ", isrepeat=" + isrepeat + ", issend=" + issend + ", istosix=" + istosix
				+ ", isverify=" + isverify + ", key=" + key + ", labdata=" + labdata
				+ ", labitemname=" + labitemname + ", labno=" + labno + ", mobile=" + mobile
				+ ", msms_attribute=" + msms_attribute + ", msms_lab_date=" + msms_lab_date
				+ ", msms_reason=" + msms_reason + ", msms_result=" + msms_result
				+ ", notice_content=" + notice_content + ", notice_date=" + notice_date
				+ ", ohp_lab_date=" + ohp_lab_date + ", ohp_reason=" + ohp_reason + ", ohp_result="
				+ ohp_result + ", operatedate=" + operatedate + ", operatname=" + operatname
				+ ", operdate=" + operdate + ", paramer1=" + paramer1 + ", paramer2=" + paramer2
				+ ", paramer3=" + paramer3 + ", paramer4=" + paramer4 + ", paramer5=" + paramer5
				+ ", paramer6=" + paramer6 + ", patientname=" + patientname + ", pdfaddress="
				+ pdfaddress + ", pdfdate=" + pdfdate + ", phe_lab_date=" + phe_lab_date
				+ ", phe_reason=" + phe_reason + ", phe_result=" + phe_result + ", postcode="
				+ postcode + ", pre_num=" + pre_num + ", preg_realcycle=" + preg_realcycle
				+ ", pregcarenum=" + pregcarenum + ", printcount=" + printcount + ", receivedate="
				+ receivedate + ", remark=" + remark + ", reserve_date=" + reserve_date
				+ ", reserved0=" + reserved0 + ", reserved1=" + reserved1 + ", reserved2="
				+ reserved2 + ", reserved3=" + reserved3 + ", reserved6=" + reserved6
				+ ", sampleid=" + sampleid + ", samplestring=" + samplestring + ", serviceid="
				+ serviceid + ", sex=" + sex + ", target_17_a_ohp=" + target_17_a_ohp
				+ ", target_g6pd=" + target_g6pd + ", target_msms=" + target_msms + ", target_phe="
				+ target_phe + ", target_tsh=" + target_tsh + ", telephone=" + telephone
				+ ", tsh_lab_date=" + tsh_lab_date + ", tsh_reason=" + tsh_reason + ", tsh_result="
				+ tsh_result + ", weight=" + weight + "]";
	}

	
}

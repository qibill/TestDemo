package com.biosan.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.webservice.PatientBasicInfoSoap;
import com.newtouch.webservice.TestReportReleaseSoap;

@Service
public class CXFWebservice implements NewTouchService {

	// TODO 加入@Autowired 根据wsdl2java生成的客户端代码修改
	private PatientBasicInfoSoap patientBasicInfo;

	private TestReportReleaseSoap testReportRelease;

	@Override
    public String patientDetailInfo(String request) {
//        String patientDetailInfo = patientBasicInfo.patientDetailInfo(request);
    	String patientDetailInfo = "<root>" + 
    			"  <head>" + 
    			"    <parameter>" + 
    			"      <hashKey>16a839ed-b5f1-XXXX-b9a3-XXXXXXX72e4</hashKey>" + 
    			"      <CardNo>135403000800151</CardNo>" + 
    			"    </parameter>" + 
    			"  </head>" + 
    			"  <body>" + 
    			"    <result>OK</result>" + 
    			"    <DataTable>" + 
    			"      <row>" + 
    			"        <Patid>1000000108</Patid>" + 
    			"        <OutPatiNo>YL00800151</OutPatiNo>" + 
    			"        <PatName>测试151</PatName>" + 
    			"        <SexName>女</SexName>" + 
    			"        <LastMense>2017-12-01</LastMense>" + 
    			"        <Age>8</Age>" + 
    			"        <IdCardNo>310103XXXXXXXX5610</IdCardNo>" + 
    			"        <Tel />" + 
    			"        <Mobile>13111111111</Mobile>" + 
    			"        <Height />" + 
    			"        <Weight />" + 
    			"        <ChildHistory />" + 
    			"        <MedicalHistory />" + 
    			"        <Flag>1</Flag>" + 
    			"        <Msg />" + 
    			"      </row>" + 
    			"    </DataTable>" + 
    			"  </body>" + 
    			"</root>" + 
    			"";
        return patientDetailInfo;
    }

	@Override
    public String PlatForm_TSSC_Service(String request) {
//        String platForm_TSSC_Service = testReportRelease.PlatForm_TSSC_Service(request);
    	String platForm_TSSC_Service= "  <root>" + 
    			"  <head>" + 
    			"    <parameter>" + 
    			"      <hashKey>c2d442a2-d458-434a-a650-e19ea1f35a79</hashKey>" + 
    			"      <invoketype>01</invoketype>" + 
    			"      <ReportType>1</ReportType>" + 
    			"	  <Patid>1000174397</Patid>" + 
    			"	  <PatNo>D00030425</PatNo>" + 
    			"	  <CardNo>135403000306131</CardNo>" + 
    			"	  <Mpi>6048512A-E698-4B38-A9CB-90DBD5228B07</Mpi>" + 
    			"	  <HospDiv>2</HospDiv>" + 
    			"	  <VistType>2</VistType>" + 
    			"	  <RecordID>1000055162</RecordID>" + 
    			"	  <PublishDate>2015-05-20 13:55:42.260</PublishDate>" + 
    			"	  <CheckDate>2015-05-20 00:00:00.000</CheckDate>" + 
    			"	  <DoctorAdvID>1000988961</DoctorAdvID>" + 
    			"	  <ReportNo>LC0004</ReportNo>" + 
    			"	  <Content>" + 
    			"		<root>" + 
    			"  <head>" + 
    			"    <parameter>" + 
    			"      <hashKey>c2d442a2-d458-434a-a650-e19ea1f35a79</hashKey>" + 
    			"      <yqqf>2</yqqf>" + 
    			"      <yzlb>1</yzlb>" + 
    			"      <czqf>1</czqf>" + 
    			"      <bglx>1</bglx>" + 
    			"      <jyrq>2015-04-01</jyrq>" + 
    			"      <bgdh>ST0001</bgdh>" + 
    			"    </parameter>" + 
    			"  </head>" + 
    			"  <body>" + 
    			"    <result>OK</result>" + 
    			"    <DataTable>" + 
    			"      <ROW>" + 
    			"        <MZZYBZ>门诊</MZZYBZ>" + 
    			"        <BRANCHNO>2</BRANCHNO>" + 
    			"        <PATID>1000136093</PATID>" + 
    			"        <BLH />" + 
    			"        <BGDH>ST0001</BGDH>" + 
    			"        <BGRQ>2015-04-01T14:03:09.963</BGRQ>" + 
    			"        <ADVID>1002168878</ADVID>" + 
    			"        <KH>135403000910906</KH>" + 
    			"        <BRXM>邓丽琴</BRXM>" + 
    			"        <BRXB>女</BRXB>" + 
    			"        <BRNL>29</BRNL>" + 
    			"        <NLDW>岁</NLDW>" + 
    			"        <SQRGH>522</SQRGH>" + 
    			"        <SQRXM>庄璟怡</SQRXM>" + 
    			"        <BGRGH>2001</BGRGH>" + 
    			"        <BGRXM>施琳</BGRXM>" + 
    			"        <SHRGH>643</SHRGH>" + 
    			"        <SHRXM>骆敏</SHRXM>" + 
    			"        <SQKS>2101006</SQKS>" + 
    			"        <BQ />" + 
    			"        <CH />" + 
    			"        <DYRQ>2015-04-01T14:03:09.963</DYRQ>" + 
    			"        <SQRQ>2015-02-26T08:18:48</SQRQ>" + 
    			"        <CJRQ>2015-03-26T09:41:09.190</CJRQ>" + 
    			"        <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"        <HSRQ>1900-01-01T00:00:00</HSRQ>" + 
    			"        <BBDM>1</BBDM>" + 
    			"        <BBMC>血</BBMC>" + 
    			"        <JZBZ />" + 
    			"        <JYMD />" + 
    			"        <ZXKS>10</ZXKS>" + 
    			"        <LCZD />" + 
    			"        <REMARK_INFO />" + 
    			"        <JGZT />" + 
    			"        <REQNO>20150401ST0001</REQNO>" + 
    			"        <BGDMC>中唐筛查单(S)</BGDMC>" + 
    			"      </ROW>" + 
    			"      <ITEMS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>TSSC_NO</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>唐氏筛查NO</JCZBMC>" + 
    			"          <JCZBJG>S1501087</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>1</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>UE3浓度</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>UE3浓度</JCZBMC>" + 
    			"          <JCZBJG>6.45</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW>nmol/L</JLCKZDW>" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>100</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>UE3校正MOM值</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>UE3校正MOM值</JCZBMC>" + 
    			"          <JCZBJG>0.91</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>150</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>生日</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>生日</JCZBMC>" + 
    			"          <JCZBJG>1986-12-27</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>2</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>采样日期</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>采样日期</JCZBMC>" + 
    			"          <JCZBJG>2015-03-26</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>20</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>T21年龄风险</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>T21年龄风险</JCZBMC>" + 
    			"          <JCZBJG>1104</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>210</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>T21风险值</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>T21风险值</JCZBMC>" + 
    			"          <JCZBJG>1769</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>220</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>T21风险结果</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>T21风险结果</JCZBMC>" + 
    			"          <JCZBJG>低风险</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>230</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>送检日期</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>送检日期</JCZBMC>" + 
    			"          <JCZBJG>2015-03-30</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>30</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>T18年龄风险</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>T18年龄风险</JCZBMC>" + 
    			"          <JCZBJG>9935</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>310</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>T18风险值</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>T18风险值</JCZBMC>" + 
    			"          <JCZBJG>100000</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>320</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>T18风险结果</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>T18风险结果</JCZBMC>" + 
    			"          <JCZBJG>低风险</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>330</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>末次月经</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>末次月经</JCZBMC>" + 
    			"          <JCZBJG>2014-11-17</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>4</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>体重</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>体重</JCZBMC>" + 
    			"          <JCZBJG>50</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>40</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>NTD风险结果</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>NTD风险结果</JCZBMC>" + 
    			"          <JCZBJG>低风险</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>430</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>B超日期</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>B超日期</JCZBMC>" + 
    			"          <JCZBJG>2015-02-26</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>480</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>BPD</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>BPD</JCZBMC>" + 
    			"          <JCZBJG>27</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>490</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>预产年龄</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>预产年龄</JCZBMC>" + 
    			"          <JCZBJG>28.66</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>6</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>HCGB浓度</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>HCGB浓度</JCZBMC>" + 
    			"          <JCZBJG>17.23</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW>ng/mL</JLCKZDW>" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>60</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>HCGB校正MOM值</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>HCGB校正MOM值</JCZBMC>" + 
    			"          <JCZBJG>1.30</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>65</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>风险计算基于项</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>孕周计算基于</JCZBMC>" + 
    			"          <JCZBJG>BPD</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>8</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>AFP浓度</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>AFP浓度</JCZBMC>" + 
    			"          <JCZBJG>34.62</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW>U/mL</JLCKZDW>" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>90</SEQNO>" + 
    			"        </ROWS>" + 
    			"        <ROWS>" + 
    			"          <YYSFDM>100300000189</YYSFDM>" + 
    			"          <YBSFDM />" + 
    			"          <JCZBDM>AFP校正MOM值</JCZBDM>" + 
    			"          <JCFF />" + 
    			"          <JCZBMC>AFP校正MOM值</JCZBMC>" + 
    			"          <JCZBJG>0.73</JCZBJG>" + 
    			"          <CKZ />" + 
    			"          <JLCKZDW />" + 
    			"          <YCTS />" + 
    			"          <SBBM>ZT1</SBBM>" + 
    			"          <YQBM>ZT1</YQBM>" + 
    			"          <YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"          <JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"          <BGDH>ST0001</BGDH>" + 
    			"          <REQNO>20150401ST0001</REQNO>" + 
    			"          <SEQNO>95</SEQNO>" + 
    			"        </ROWS>" + 
    			"      </ITEMS>" + 
    			"    </DataTable>" + 
    			"  </body>" + 
    			"</root>" + 
    			"	  </Content>" + 
    			"	  <FCD>2015-05-20 13:56:27.000</FCD>" + 
    			"	  <Reqno>20150520LC0004</Reqno>" + 
    			"	  <BarCode>LC0004</BarCode>" + 
    			"	  <czqf>1</czqf>" + 
    			"	  <PatName>周海燕</PatName>" + 
    			"	  <PY>ZHY</PY>" + 
    			"	  <PrintDatetime></PrintDatetime>" + 
    			"	  <isPrint></isPrint>" + 
    			"    </parameter>" + 
    			"  </head>" + 
    			"  <body>" + 
    			"  <result>OK</result> " + 
    			"	<DataTable>" + 
    			"		<flag>1</flag>" + 
    			"	</DataTable>   " + 
    			"</body>" + 
    			"</root>" + 
    			"";
        return platForm_TSSC_Service;
    }

}

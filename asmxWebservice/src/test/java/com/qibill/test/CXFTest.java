package com.qibill.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.newtouch.webservice.patientbasicinfo.PatientBasicInfoSoap;
import com.newtouch.webservice.testreportrelease.TestReportReleaseSoap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:CXF.xml")
public class CXFTest {

	
	@Autowired
	private PatientBasicInfoSoap patientBasicInfoSoap;
	@Autowired
	private TestReportReleaseSoap testReportReleaseSoap;
	
    @Test
    public void patientBasicInfoSoapTest() {
    	String xml = "<root>" + 
    			"	<head>" + 
    			"		<parameter>" + 
    			"			<hashKey>16a839ed-b5f1-4148-b9a3-dab942a072e4</hashKey>" + 
    			"			<CardNo>135403000800151</CardNo>" + 
    			"		</parameter>" + 
    			"	</head>" + 
    			"	<body />" + 
    			"</root>";
		String patientDetailInfo = patientBasicInfoSoap.patientDetailInfo(xml );
		System.out.println(patientDetailInfo);
    }
    
    @Test
    public void testReportReleaseSoapTest() {
    	String xml = "<root>" + 
    			"	<head>" + 
    			"		<parameter>" + 
    			"			<!-- 平台内部编码* -->" + 
    			"			<hashKey>16a839ed-b5f1-4148-b9a3-dab942a072e4</hashKey>" + 
    			"			<!-- 01 添加一条检验报告记录* -->" + 
    			"			<invoketype>01</invoketype>" + 
    			"			<!-- 报告类型 -->" + 
    			"			<ReportType>1</ReportType>" + 
    			"			<!-- 患者ID -->" + 
    			"			<Patid>1000174397</Patid>" + 
    			"			<!-- 证件号码 -->" + 
    			"			<PatNo>D00030425</PatNo>" + 
    			"			<!-- 就诊卡号 -->" + 
    			"			<CardNo>135403000306131</CardNo>" + 
    			"			<!-- MPI -->" + 
    			"			<Mpi>6048512A-E698-4B38-A9CB-90DBD5228B07</Mpi>" + 
    			"			<!-- 院区编码 -->" + 
    			"			<HospDiv>2</HospDiv>" + 
    			"			<!-- 就诊类型 -->" + 
    			"			<VistType>2</VistType>" + 
    			"			<!-- 记录ID -->" + 
    			"			<RecordID>1000055162</RecordID>" + 
    			"			<!-- 发布时间 -->" + 
    			"			<PublishDate>2015-05-20 13:55:42.260</PublishDate>" + 
    			"			<!-- 检查时间 -->" + 
    			"			<CheckDate>2015-05-20 00:00:00.000</CheckDate>" + 
    			"			<!-- 开医嘱医生ID -->" + 
    			"			<DoctorAdvID>1000988961</DoctorAdvID>" + 
    			"			<!-- 报告号 -->" + 
    			"			<ReportNo>LC0004</ReportNo>" + 
    			"			<!-- 报告内容 -->" + 
    			"			<Content>" + 
    			"				<root>" + 
    			"					<head>" + 
    			"						<parameter>" + 
    			"							<hashKey>c2d442a2-d458-434a-a650-e19ea1f35a79</hashKey>" + 
    			"							<yqqf>2</yqqf>" + 
    			"							<yzlb>1</yzlb>" + 
    			"							<czqf>1</czqf>" + 
    			"							<bglx>1</bglx>" + 
    			"							<jyrq>2015-04-01</jyrq>" + 
    			"							<bgdh>ST0001</bgdh>" + 
    			"						</parameter>" + 
    			"					</head>" + 
    			"					<body>" + 
    			"						<result>OK</result>" + 
    			"						<DataTable>" + 
    			"							<ROW>" + 
    			"								<!-- 门诊住院标志 -->" + 
    			"								<MZZYBZ>门诊</MZZYBZ>" + 
    			"								<!-- 院区编码 -->" + 
    			"								<BRANCHNO>2</BRANCHNO>" + 
    			"								<!-- 患者ID -->" + 
    			"								<PATID>1000136093</PATID>" + 
    			"								<!-- 病历号 -->" + 
    			"								<BLH />" + 
    			"								<!-- 报告单号 -->" + 
    			"								<BGDH>ST0001</BGDH>" + 
    			"								<!-- 报告日期 -->" + 
    			"								<BGRQ>2015-04-01T14:03:09.963</BGRQ>" + 
    			"								<!-- 医嘱ID -->" + 
    			"								<ADVID>1002168878</ADVID>" + 
    			"								<!-- 卡号 -->" + 
    			"								<KH>135403000910906</KH>" + 
    			"								<!-- 患者姓名 -->" + 
    			"								<BRXM>邓丽琴</BRXM>" + 
    			"								<!-- 患者性别 -->" + 
    			"								<BRXB>女</BRXB>" + 
    			"								<!-- 患者年龄 -->" + 
    			"								<BRNL>29</BRNL>" + 
    			"								<!-- 年龄单位 -->" + 
    			"								<NLDW>岁</NLDW>" + 
    			"								<!-- 申请人工号 -->" + 
    			"								<SQRGH>522</SQRGH>" + 
    			"								<!-- 申请人姓名 -->" + 
    			"								<SQRXM>庄璟怡</SQRXM>" + 
    			"								<!-- 报告人工号 -->" + 
    			"								<BGRGH>2001</BGRGH>" + 
    			"								<!-- 报告人姓名 -->" + 
    			"								<BGRXM>施琳</BGRXM>" + 
    			"								<!-- 审核人工号 -->" + 
    			"								<SHRGH>643</SHRGH>" + 
    			"								<!-- 审核人姓名 -->" + 
    			"								<SHRXM>骆敏</SHRXM>" + 
    			"								<!-- 申请科室 -->" + 
    			"								<SQKS>2101006</SQKS>" + 
    			"								<!-- 病区 -->" + 
    			"								<BQ />" + 
    			"								<!-- 床号 -->" + 
    			"								<CH />" + 
    			"								<!-- 打印日期 -->" + 
    			"								<DYRQ>2015-04-01T14:03:09.963</DYRQ>" + 
    			"								<!-- 申请日期 -->" + 
    			"								<SQRQ>2015-02-26T08:18:48</SQRQ>" + 
    			"								<!-- 创建日期 -->" + 
    			"								<CJRQ>2015-03-26T09:41:09.190</CJRQ>" + 
    			"								<!-- 检验日期 -->" + 
    			"								<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"								<!-- 核实日期 -->" + 
    			"								<HSRQ>1900-01-01T00:00:00</HSRQ>" + 
    			"								<!-- 标本代码 -->" + 
    			"								<BBDM>1</BBDM>" + 
    			"								<!-- 标本名称 -->" + 
    			"								<BBMC>血</BBMC>" + 
    			"								<!-- 结账标志 -->" + 
    			"								<JZBZ />" + 
    			"								<!-- 检验目的 -->" + 
    			"								<JYMD />" + 
    			"								<!-- 执行科室 -->" + 
    			"								<ZXKS>10</ZXKS>" + 
    			"								<!-- 临床诊断 -->" + 
    			"								<LCZD />" + 
    			"								<!-- 备注信息 -->" + 
    			"								<REMARK_INFO />" + 
    			"								<!-- 结果状态 -->" + 
    			"								<JGZT />" + 
    			"								<!-- 序号 -->" + 
    			"								<REQNO>20150401ST0001</REQNO>" + 
    			"								<!-- 报告单名称 -->" + 
    			"								<BGDMC>中唐筛查单(S)</BGDMC>" + 
    			"							</ROW>" + 
    			"							<ITEMS>" + 
    			"								<ROWS>" + 
    			"									<!-- 医院收费代码 -->" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<!-- 医保收费代码 -->" + 
    			"									<YBSFDM />" + 
    			"									<!-- 检查指标代码 -->" + 
    			"									<JCZBDM>TSSC_NO</JCZBDM>" + 
    			"									<!-- 检查方法 -->" + 
    			"									<JCFF />" + 
    			"									<!-- 检查指标名称 -->" + 
    			"									<JCZBMC>唐氏筛查NO</JCZBMC>" + 
    			"									<!-- 检查指标结果 -->" + 
    			"									<JCZBJG>S1501087</JCZBJG>" + 
    			"									<!-- 参考值 -->" + 
    			"									<CKZ />" + 
    			"									<!-- 记录参考值单位 -->" + 
    			"									<JLCKZDW />" + 
    			"									<!-- 异常提示 -->" + 
    			"									<YCTS />" + 
    			"									<!-- 设备编码 -->" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<!-- 仪器编码 -->" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<!-- 仪器名称 -->" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<!-- 检验日期 -->" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<!-- 报告单号 -->" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<!-- 序列号 -->" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<!-- 项目序号 -->" + 
    			"									<SEQNO>1</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>UE3浓度</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>UE3浓度</JCZBMC>" + 
    			"									<JCZBJG>6.45</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW>nmol/L</JLCKZDW>" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>100</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>UE3校正MOM值</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>UE3校正MOM值</JCZBMC>" + 
    			"									<JCZBJG>0.91</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>150</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>生日</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>生日</JCZBMC>" + 
    			"									<JCZBJG>1986-12-27</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>2</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>采样日期</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>采样日期</JCZBMC>" + 
    			"									<JCZBJG>2015-03-26</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>20</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>T21年龄风险</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>T21年龄风险</JCZBMC>" + 
    			"									<JCZBJG>1104</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>210</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>T21风险值</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>T21风险值</JCZBMC>" + 
    			"									<JCZBJG>1769</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>220</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>T21风险结果</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>T21风险结果</JCZBMC>" + 
    			"									<JCZBJG>低风险</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>230</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>送检日期</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>送检日期</JCZBMC>" + 
    			"									<JCZBJG>2015-03-30</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>30</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>T18年龄风险</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>T18年龄风险</JCZBMC>" + 
    			"									<JCZBJG>9935</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>310</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>T18风险值</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>T18风险值</JCZBMC>" + 
    			"									<JCZBJG>100000</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>320</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>T18风险结果</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>T18风险结果</JCZBMC>" + 
    			"									<JCZBJG>低风险</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>330</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>末次月经</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>末次月经</JCZBMC>" + 
    			"									<JCZBJG>2014-11-17</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>4</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>体重</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>体重</JCZBMC>" + 
    			"									<JCZBJG>50</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>40</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>NTD风险结果</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>NTD风险结果</JCZBMC>" + 
    			"									<JCZBJG>低风险</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>430</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>B超日期</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>B超日期</JCZBMC>" + 
    			"									<JCZBJG>2015-02-26</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>480</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>BPD</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>BPD</JCZBMC>" + 
    			"									<JCZBJG>27</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>490</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>预产年龄</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>预产年龄</JCZBMC>" + 
    			"									<JCZBJG>28.66</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>6</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>HCGB浓度</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>HCGB浓度</JCZBMC>" + 
    			"									<JCZBJG>17.23</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW>ng/mL</JLCKZDW>" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>60</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>HCGB校正MOM值</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>HCGB校正MOM值</JCZBMC>" + 
    			"									<JCZBJG>1.30</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>65</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>风险计算基于项</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>孕周计算基于</JCZBMC>" + 
    			"									<JCZBJG>BPD</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>8</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>AFP浓度</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>AFP浓度</JCZBMC>" + 
    			"									<JCZBJG>34.62</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW>U/mL</JLCKZDW>" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>90</SEQNO>" + 
    			"								</ROWS>" + 
    			"								<ROWS>" + 
    			"									<YYSFDM>100300000189</YYSFDM>" + 
    			"									<YBSFDM />" + 
    			"									<JCZBDM>AFP校正MOM值</JCZBDM>" + 
    			"									<JCFF />" + 
    			"									<JCZBMC>AFP校正MOM值</JCZBMC>" + 
    			"									<JCZBJG>0.73</JCZBJG>" + 
    			"									<CKZ />" + 
    			"									<JLCKZDW />" + 
    			"									<YCTS />" + 
    			"									<SBBM>ZT1</SBBM>" + 
    			"									<YQBM>ZT1</YQBM>" + 
    			"									<YQMC>早唐筛查单(Ft)</YQMC>" + 
    			"									<JYRQ>2015-04-01T00:00:00</JYRQ>" + 
    			"									<BGDH>ST0001</BGDH>" + 
    			"									<REQNO>20150401ST0001</REQNO>" + 
    			"									<SEQNO>95</SEQNO>" + 
    			"								</ROWS>" + 
    			"							</ITEMS>" + 
    			"						</DataTable>" + 
    			"					</body>" + 
    			"				</root>" + 
    			"			</Content>" + 
    			"			<!-- 生成时间 -->" + 
    			"			<FCD>2015-05-20 13:56:27.000</FCD>" + 
    			"			<!-- 序号 -->" + 
    			"			<Reqno>20150520LC0004</Reqno>" + 
    			"			<!-- 条形码 -->" + 
    			"			<BarCode>LC0004</BarCode>" + 
    			"			<!-- 操作编码（1 新增 3 撤销） -->" + 
    			"			<czqf>1</czqf>" + 
    			"			<!-- 患者姓名 -->" + 
    			"			<PatName>周海燕</PatName>" + 
    			"			<!-- 拼音 -->" + 
    			"			<PY>ZHY</PY>" + 
    			"			<!-- 打印时间 -->" + 
    			"			<PrintDatetime></PrintDatetime>" + 
    			"			<!-- 打印标志 -->" + 
    			"			<isPrint></isPrint>" + 
    			"		</parameter>" + 
    			"	</head>" + 
    			"	<body />" + 
    			"</root>" + 
    			"";
		String patientDetailInfo = testReportReleaseSoap.platFormTSSCService(xml );
		System.out.println(patientDetailInfo);
    }
}

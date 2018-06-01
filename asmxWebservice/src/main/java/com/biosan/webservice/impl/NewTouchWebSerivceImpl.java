package com.biosan.webservice.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.NewTouch.pojo.Content;
import com.NewTouch.pojo.ContentBody;
import com.NewTouch.pojo.ContentHead;
import com.NewTouch.pojo.ContentItem;
import com.NewTouch.pojo.PatientDetailInfoRequest;
import com.NewTouch.pojo.PlatFormTSSCServiceRequest;
import com.biosan.utils.JdbcOperation;
import com.biosan.utils.ResultSetMapper;
import com.biosan.utils.Webservice;
import com.biosan.webservice.NewTouchWebSerivce;

public class NewTouchWebSerivceImpl implements NewTouchWebSerivce{

	private JdbcOperation jdbcOperation;

	@Override
	public String getPatientDetailInfo(String CardNo) {

		PatientDetailInfoRequest request = new PatientDetailInfoRequest(CardNo);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("parameter", request.toXml());

		Webservice webservice = new Webservice();
		String reponse = webservice.doAxisWebservice(
				"http://172.20.1.35/WebService/PatientBasicInfo.asmx", "", "patientDetailInfo",
				parameter, String.class);
		return reponse;
	}

	@Override
	public void sendPlatFormTSSCService(int sampleid, String czqf) {
		PlatFormTSSCServiceRequest request = creatPlatFormTSSCServiceRequest(sampleid, czqf);

		Map<String, Object> parameter = new HashMap<>();
		
		System.out.println(request.toXml());
		parameter.put("parameter", request.toXml());

		Webservice webservice = new Webservice();
		webservice.doAxisWebservice("http://172.20.1.35/WebService/TestReportRelease.asmx", "",
				"PlatForm_TSSC_Service ", parameter, String.class);
	}

	/**
	 * 根据 创建PlatFormTSSCServiceRequest
	 * 
	 * @param Patid 样品ID
	 * @return
	 * @author qibill 2018年5月31日上午10:05:55
	 */
	public PlatFormTSSCServiceRequest creatPlatFormTSSCServiceRequest(int sampleid, String czqf) {
		List<ContentItem> items = new ArrayList<>();
		items.addAll(selectContentItemList(sampleid));
		ContentBody body = selectContentBody(sampleid);
		body.setContentItems(items);
		ContentHead head = selectContentHead(sampleid);
		Content content = new Content(head, body);
		PlatFormTSSCServiceRequest request = selectPlatFormTSSCServiceRequest(sampleid);
		request.setContent(content);
		// 设置操作
		request.setCzqf(czqf);
		request.getContent().getContentHead().setCzqf(czqf);
		return request;
	}

	private Collection<ContentItem> selectContentItemList(int sampleid) {
		StringBuilder sql = new StringBuilder("SELECT p.patientname 姓名,");
		sql.append("p.lmpdate 末次月经,");
		sql.append("p.birthday 出生日期,");
		sql.append("s.cycletype 孕周计算基于,");
		sql.append("s.preg_age 预产年龄,");
		sql.append("s.samplestring 样品编号,");
		sql.append("s.collectdate 采样日期,");
		sql.append("s.senddate 送检日期,");
		sql.append("s.weight 体重,");
		sql.append("CONCAT(s.cycle_week,'周',s.cycle_day,'天') 采样时孕周,");
		sql.append("r.target_afpmom AFP,");
		sql.append("r.target_hcgbmom hCGb,");
		sql.append("r.target_ue3mom uE3,");
		sql.append("r.age_t21 '21-三体年龄风险',");
		sql.append("r.analysis_t21 '21-三体风险值',");
		sql.append("r.result_t21 '21-三体风险结果',");
		sql.append("r.age_t18 '18-三体年龄风险',");
		sql.append("r.analysis_t18 '18-三体风险值',");
		sql.append("r.result_t18 '18-三体风险结果',");
		sql.append("R.result_ntd NTD,");
		sql.append("s.labdate JYRQ,");
		sql.append("s.samplestring BGDH,");
		sql.append("s.samplestring REQNO,");
		sql.append("s.pdfdate 报告日期,");
		sql.append("s.pdfchecktor 审核者");
		sql.append(" FROM sample s, sampleresult r,patient p");
		sql.append(" WHERE s.sampleid = r.sampleid");
		sql.append(" AND s.patientid = p.patientid");
		sql.append(" AND s.sampleid = ?");
		sql.append(" ORDER BY r.operattime DESC ");
		sql.append(" LIMIT 1;");
		Object[] params = { sampleid };
		List<ContentItem> ContentItems = new ArrayList<>();
		try {
			Map<String, Object> map = jdbcOperation.queryForMap(sql.toString(), params).get(0);
			for (String key : map.keySet()) {
				if ("JYRQ".equals(key) || "BGDH".equals(key) || "REQNO".equals(key)) {
					continue;
				}
				ContentItem item = new ContentItem();
				item.setJCZBMC(key);
				item.setJCZBDM(key);
				item.setJCZBJG(map.get(key));
				item.setJYRQ((Date) map.get("JYRQ"));
				item.setBGDH((String) map.get("BGDH"));
				item.setREQNO((String) map.get("REQNO"));
				ContentItems.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ContentItems;
		}
		return ContentItems;
	}

	private ContentBody selectContentBody(int sampleid) {
		StringBuilder sql = new StringBuilder("SELECT s.pdfdate BGRQ,");
		sql.append("s.samplestring BGDH,");
		sql.append("s.samplestring PATID,");
		sql.append("s.samplestring REQNO,");
		sql.append("s.backfillnum KH,");
		sql.append("p.patientname BRXM,");
		sql.append("s.laboperator BGRGH,");
		sql.append("s.laboperator BGRXM,");
		sql.append("s.pdfchecktor SHRGH,");
		sql.append("s.pdfchecktor SHRXM,");
		sql.append("s.addtime SQRQ,");
		sql.append("s.addtime CJRQ,");
		sql.append("s.labdate JYRQ,");
		sql.append("s.pdfdate HSRQ");
		sql.append(" FROM sample s,patient p");
		sql.append(" WHERE s.patientid = p.patientid");
		sql.append(" AND s.sampleid = ?;");
		Object[] params = { sampleid };
		ContentBody contentBody = new ContentBody();
		try {
			contentBody = jdbcOperation
					.queryForBean(sql.toString(), params, new ResultSetMapper<ContentBody>() {
					}).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return contentBody;
		}
		return contentBody;
	}

	private ContentHead selectContentHead(int sampleid) {
		StringBuilder sql = new StringBuilder("SELECT s.labdate jyrq,");
		sql.append("s.samplestring bgdh");
		sql.append(" FROM sample s");
		sql.append(" WHERE s.sampleid = ?;");
		Object[] params = { sampleid };
		ContentHead contentHead = new ContentHead();
		try {
			contentHead = jdbcOperation
					.queryForBean(sql.toString(), params, new ResultSetMapper<ContentHead>() {
					}).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return contentHead;
		}
		return contentHead;
	}

	private PlatFormTSSCServiceRequest selectPlatFormTSSCServiceRequest(int sampleid) {
		StringBuilder sql = new StringBuilder("SELECT s.samplestring Patid, ");
		sql.append("p.identitycard PatNo,");
		sql.append("s.backfillnum CardNo,");
		sql.append("s.pdfdate PublishDate,");
		sql.append("s.labdate CheckDate,");
		sql.append("s.samplestring ReportNo,");
		sql.append("p.patientname PatName,");
		sql.append(" FROM sample s,patient p");
		sql.append(" WHERE s.patientid = p.patientid");
		sql.append(" AND s.sampleid = ?;");
		Object[] params = { sampleid };
		PlatFormTSSCServiceRequest request = new PlatFormTSSCServiceRequest();
		try {
			request = jdbcOperation.queryForBean(sql.toString(), params,
					new ResultSetMapper<PlatFormTSSCServiceRequest>() {
					}).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return request;
		}
		return request;
	}

	public JdbcOperation getJdbcOperation() {
		return jdbcOperation;
	}

	public void setJdbcOperation(JdbcOperation jdbcOperation) {
		this.jdbcOperation = jdbcOperation;
	}

}

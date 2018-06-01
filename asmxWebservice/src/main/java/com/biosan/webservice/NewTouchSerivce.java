package com.biosan.webservice;

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
import com.biosan.utils.JdbcUtil;
import com.biosan.utils.ResultSetMapper;
import com.biosan.utils.Webservice;

public class NewTouchSerivce {

	private JdbcOperation jdbcOperation = new JdbcUtil();

	/**
	 * @param CardNo
	 * @return
	 * @author qibill 2018年5月31日上午10:16:00
	 */
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

	/**
	 * @param Patid 患者ID
	 * @param czqf czqf 操作编码（1 新增 3 撤销）
	 * @author qibill 2018年5月31日上午10:17:04
	 */
	public void sendPlatFormTSSCService(int Patid, String czqf) {
		PlatFormTSSCServiceRequest request = creatPlatFormTSSCServiceRequest(Patid, czqf);

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("parameter", request.toXml());

		Webservice webservice = new Webservice();
		webservice.doAxisWebservice("http://172.20.1.35/WebService/TestReportRelease.asmx", "",
				"PlatForm_TSSC_Service ", parameter, String.class);
	}

	/**
	 * 根据 创建PlatFormTSSCServiceRequest
	 * 
	 * @param Patid 患者ID
	 * @return
	 * @author qibill 2018年5月31日上午10:05:55
	 */
	public PlatFormTSSCServiceRequest creatPlatFormTSSCServiceRequest(int Patid, String czqf) {
		List<ContentItem> items = new ArrayList<>();
		items.addAll(selectContentItemList(Patid));
		ContentBody body = selectContentBody(Patid);
		body.setContentItems(items);
		ContentHead head = selectContentHead(Patid);
		Content content = new Content(head, body);
		PlatFormTSSCServiceRequest request = selectPlatFormTSSCServiceRequest(Patid);
		request.setContent(content);
		// 设置操作
		request.setCzqf(czqf);
		request.getContent().getContentHead().setCzqf(czqf);
		return request;
	}

	private Collection<ContentItem> selectContentItemList(int Patid) {
		StringBuilder sql = new StringBuilder("SELECT s.samplestring 样品编号,");
		sql.append("s.collectdate 采样日期,");
		sql.append("s.senddate 送检日期,");
		sql.append("s.weight 体重,");
		sql.append("s.cycletype 采样时孕周,");
		sql.append("s.labdate JYRQ,");
		sql.append("s.samplestring BGDH");
		sql.append(" FROM sample s");
		sql.append(" WHERE s.patientid = ?");
		Object[] params = { Patid };
		List<ContentItem> ContentItems = new ArrayList<>();
		try {
			Map<String, Object> map = jdbcOperation.queryForMap(sql.toString(), params).get(0);
			for (String key : map.keySet()) {
				if ("JYRQ".equals(key) || "BGDH".equals(key)) {
					continue;
				}
				ContentItem item = new ContentItem();
				item.setJCZBMC(key);
				item.setJCZBDM(key);
				item.setJCZBJG(map.get(key));
				item.setJYRQ((Date) map.get("JYRQ"));
				item.setBGDH((String) map.get("BGDH"));
				ContentItems.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ContentItems;
		}
		return ContentItems;
	}

	private ContentBody selectContentBody(int Patid) {
		StringBuilder sql = new StringBuilder("SELECT s.pdfdate BGRQ,");
		sql.append("s.samplestring BGDH,");
		sql.append("s.samplestring PATID,");
		sql.append("s.samplestring REQNO,");
		sql.append("s.backfillnum KH,");
		sql.append("p.patientname BRXM,");
		sql.append("s.pdfchecktor BGRGH,");
		sql.append("s.laboperator BGRXM,");
		sql.append("s.pdfchecktor SHRGH,");
		sql.append("s.pdfchecktor SHRXM,");
		sql.append("s.addtime SQRQ,");
		sql.append("s.addtime CJRQ,");
		sql.append("s.labdate JYRQ,");
		sql.append("s.pdfdate HSRQ");
		sql.append(" FROM sample s,patient p");
		sql.append(" WHERE s.patientid = p.patientid");
		sql.append(" AND p.patientid = ?;");
		Object[] params = { Patid };
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

	private ContentHead selectContentHead(int Patid) {
		StringBuilder sql = new StringBuilder("SELECT s.labdate jyrq,");
		sql.append("s.samplestring bgdh");
		sql.append(" FROM sample s");
		sql.append(" WHERE s.patientid = ?;");
		Object[] params = { Patid };
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

	private PlatFormTSSCServiceRequest selectPlatFormTSSCServiceRequest(int Patid) {
		StringBuilder sql = new StringBuilder("SELECT s.samplestring Patid, ");
		sql.append("p.identitycard PatNo,");
		sql.append("s.backfillnum CardNo,");
		sql.append("s.pdfdate PublishDate,");
		sql.append("s.labdate CheckDate,");
		sql.append("s.samplestring ReportNo,");
		sql.append("p.patientname PatName,");
		sql.append("s.pdfdate FCD");
		sql.append(" FROM sample s,patient p");
		sql.append(" WHERE s.patientid = p.patientid");
		sql.append(" AND p.patientid = ?;");
		Object[] params = { Patid };
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

}

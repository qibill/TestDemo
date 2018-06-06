package com.biosan.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.biosan.pojo.Newtouchtsscresult;
import com.biosan.utils.BiosanResult;
import com.biosan.utils.RequestUtil;
import com.newtouch.mapper.ContentBodyMapper;
import com.newtouch.mapper.ContentHeadMapper;
import com.newtouch.mapper.ContentItemMapper;
import com.newtouch.mapper.EmployeeMapper;
import com.newtouch.mapper.NewtouchtsscresultMapper;
import com.newtouch.mapper.PlatFormTSSCServiceRequestMapper;
import com.newtouch.pojo.Content;
import com.newtouch.pojo.ContentBody;
import com.newtouch.pojo.ContentHead;
import com.newtouch.pojo.ContentItem;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;
import com.newtouch.webservice.testreportrelease.TestReportReleaseSoap;

@Service
public class TSSCServiceImpl implements TSSCService {

    private static final Logger logger = Logger.getLogger(TSSCServiceImpl.class);

	@Autowired
	private ContentHeadMapper contentHeadMapper;
	@Autowired
	private ContentItemMapper contentItemMapper;
	@Autowired
	private ContentBodyMapper contentBodyMapper;
	@Autowired
	private PlatFormTSSCServiceRequestMapper tSSCServiceRequestMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private TestReportReleaseSoap testReportRelease;
	@Autowired
	private NewtouchtsscresultMapper newtouchtsscresultMapper;
	@Value("${NUM}")
	private Integer NUM;
	@Override
	public PlatFormTSSCServiceRequest creator(Integer sampleid, Integer czqf) {
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

	private Collection<ContentItem> selectContentItemList(Integer sampleid) {
		List<ContentItem> ContentItems = new ArrayList<>();
		Map<String, Object> map = contentItemMapper.queryForMap(74734);
		for (String key : map.keySet()) {
			if ("JYRQ".equals(key) || "BGDH".equals(key) || "REQNO".equals(key)) {
				continue;
			}
			
			ContentItem item = new ContentItem();
			item.setJYRQ((Date) map.get("JYRQ"));
			item.setBGDH((String) map.get("BGDH"));
			item.setREQNO((String) map.get("REQNO"));
			item.setJCZBMC(key);
			item.setJCZBDM(key);
			if ("审核者".equals(key)) {
				item.setJCZBJG(employeeMapper.selectEmployeename((Integer) map.get(key)).get(0));
			} else {
				item.setJCZBJG(map.get(key));
			}
			ContentItems.add(item);
		}
		return ContentItems;
	}

	private ContentBody selectContentBody(Integer sampleid) {

		ContentBody contentBody = new ContentBody();
		contentBody = contentBodyMapper.selectContentBody(sampleid).get(0);
		if (contentBody.getBGRGH() != null) {
			contentBody.setBGRXM(employeeMapper.selectEmployeename(contentBody.getBGRGH()).get(0));
			
		}
		if (contentBody.getSHRGH() != null) {
			contentBody.setSHRXM(employeeMapper.selectEmployeename(contentBody.getSHRGH()).get(0));
			
		}
		return contentBody;
	}

	private ContentHead selectContentHead(Integer sampleid) {
		List<ContentHead> list = contentHeadMapper.selectContentHead(sampleid);
		return list.get(0);
	}

	private PlatFormTSSCServiceRequest selectPlatFormTSSCServiceRequest(Integer sampleid) {

		Object[] params = { sampleid };
		PlatFormTSSCServiceRequest request = new PlatFormTSSCServiceRequest();
		request = tSSCServiceRequestMapper.selectPlatFormTSSCServiceRequest(sampleid).get(0);
		// 当期操作时间
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		request.setFCD(df.format(day));

		return request;
	}

	@Override
	public BiosanResult sendPlatFormTSSCService(Newtouchtsscresult newtouchtsscresult) {
		BiosanResult result = new BiosanResult();
		newtouchtsscresult.setSendattime(new Date());
		logger.debug(newtouchtsscresult.toString());
		// 未发过 表newtouchtsscresult没有数据
		if (newtouchtsscresult.getTsscresult() == null) {
			logger.info("样品ID为" + newtouchtsscresult.getSampleid() + "未发送过报告");
			result = doWebservice(newtouchtsscresult.getSampleid(), 1);
			newtouchtsscresult.setTsscresult(result.getStatus());
			newtouchtsscresultMapper.insert(newtouchtsscresult);
		} else {
			// 表newtouchtsscresult有数据
			// 更新 pdfdate报告日期
			// 成功发送过
			if (newtouchtsscresult.getTsscresult() == 1) {
				logger.info("样品ID为" + newtouchtsscresult.getSampleid() + "重新风险计算了，发送撤销命令");
				result = doWebservice(newtouchtsscresult.getSampleid(), 3);

				if (result.getStatus() == 1) {
					result = doWebservice(newtouchtsscresult.getSampleid(), 1);

				}
				newtouchtsscresult.setTsscresult(result.getStatus());
				logger.debug(newtouchtsscresult.toString());
				newtouchtsscresultMapper.updateByBean(newtouchtsscresult);
			}
			if (newtouchtsscresult.getTsscresult() == 2) {
				// 发送失败的 pdfdate报告日期在一天之内
				logger.info("样品ID为" + newtouchtsscresult.getSampleid() + "之前发送报告失败");
				result = doWebservice(newtouchtsscresult.getSampleid(), 1);
				newtouchtsscresult.setTsscresult(result.getStatus());
				newtouchtsscresultMapper.updateByBean(newtouchtsscresult);
			} else {
				logger.info("样品ID为" + newtouchtsscresult.getSampleid() + "之前发送撤销命令失败");
				result = doWebservice(newtouchtsscresult.getSampleid(), 3);

				if (result.getStatus() == 1) {
					result = doWebservice(newtouchtsscresult.getSampleid(), 1);

				}
				newtouchtsscresult.setTsscresult(result.getStatus());
				logger.debug(newtouchtsscresult.toString());
				newtouchtsscresultMapper.updateByBean(newtouchtsscresult);
			}

		}
		return result;
	}
	
	
	private BiosanResult sendNum(Integer sampleid, Integer czqf) {
		BiosanResult result = new BiosanResult();
		for (int i = 0; i < NUM; i++) {
			logger.info("第" + (i + 1) + "次发送");
			result = doWebservice(sampleid, czqf);
			if (result.getStatus() == 1) {
				return result;
			}
		}
		return result;
	} 

	public BiosanResult doWebservice(Integer sampleid, Integer czqf) {
        String report = czqf == 1 ? "报告信息" : "撤销命令";
        logger.info("样品ID为" + sampleid + "开始发送" + report);
        //TODO
//        PlatFormTSSCServiceRequest request = creator(sampleid, czqf);
//        String tSSCReponse = testReportRelease.platFormTSSCService(request.toXml());
//        BiosanResult biosanResult = RequestUtil.getTSSCRequest(tSSCReponse);
        
        
        Random random = new Random(47);
        BiosanResult biosanResult =new BiosanResult();
        if (random.nextInt(5) < 4) {
        		biosanResult.setStatus(czqf == 1 ? 2 : 3);
		} else {
			biosanResult = BiosanResult.isOk();
		}
        
        
        if (biosanResult.getStatus() == 1) {
        	logger.info("样品ID为" + sampleid + report + "发送成功");
		} else {
	        logger.error("样品ID为" + sampleid + report + "发送失败");
		}
        return biosanResult;
	}
}

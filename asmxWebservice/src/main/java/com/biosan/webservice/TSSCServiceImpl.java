package com.biosan.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.newtouch.mapper.NewtouchtsscresultMapper;
import com.newtouch.mapper.PlatFormTSSCServiceRequestMapper;
import com.newtouch.pojo.Content;
import com.newtouch.pojo.ContentBody;
import com.newtouch.pojo.ContentHead;
import com.newtouch.pojo.ContentItem;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;

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
	private NewTouchService newTouchService;
	@Autowired
	private NewtouchtsscresultMapper newtouchtsscresultMapper;
	@Value("${NUM}")
	private String NUM;
	
	
	@Override
	public PlatFormTSSCServiceRequest creator(Integer sampleid, Integer czqf) {
		logger.debug("生成ContentItem");
		List<ContentItem> items = selectContentItemList(sampleid);
		logger.debug("生成ContentBody");
		ContentBody body = selectContentBody(sampleid);
		body.setContentItems(items);
		logger.debug("生成ContentHead");
		ContentHead head = selectContentHead(sampleid);
		Content content = new Content(head, body);
		logger.debug("生成PlatFormTSSCServiceRequest");
		PlatFormTSSCServiceRequest request = selectPlatFormTSSCServiceRequest(sampleid);
		request.setContent(content);
		// 设置操作
		request.setCzqf(czqf);
		request.getContent().getContentHead().setCzqf(czqf);
		return request;
	}

	private List<ContentItem> selectContentItemList(Integer sampleid) {
		List<ContentItem> ContentItems = new LinkedList<>();
		logger.debug("搜索参数");
		Map<String, Object> map = contentItemMapper.queryForMap(sampleid);
		logger.debug(map.size());
		logger.debug(map.toString());
		ContentItem TSSC = new ContentItem();
		
		TSSC.setJCZBMC("唐氏筛查NO");
		TSSC.setJCZBDM("TSSC_NO");
		TSSC.setJYRQ((Date) map.get("JYRQ"));
		TSSC.setBGDH((String) map.get("BGDH"));
		TSSC.setREQNO((String) map.get("REQNO"));
		TSSC.setJCZBJG("S" + map.get("BGDH"));
		ContentItems.add(TSSC);
		
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
			item.setJCZBJG(map.get(key) == null ? "0" : map.get(key));
			ContentItems.add(item);
		}
		return ContentItems;
	}

	private ContentBody selectContentBody(Integer sampleid) {
		List<ContentBody> list = contentBodyMapper.selectContentBody(sampleid);
		logger.debug("搜索参数ContentBody");
		if (list.size() == 0) {
			logger.error("ContentBody没有值");
			return new ContentBody();
		}
		logger.debug(list.get(0).toString());
		return list.get(0);
	}

	private ContentHead selectContentHead(Integer sampleid) {
		logger.debug("搜索参数ContentHead");
		List<ContentHead> list = contentHeadMapper.selectContentHead(sampleid);
		if (list.size() == 0) {
			logger.error("ContentHead没有值");
			return new ContentHead();
		}
		logger.debug(list.get(0).toString());
		return list.get(0);
	}

	private PlatFormTSSCServiceRequest selectPlatFormTSSCServiceRequest(Integer sampleid) {
		PlatFormTSSCServiceRequest request = new PlatFormTSSCServiceRequest();
		logger.debug("搜索参数PlatFormTSSCServiceRequest");
		List<PlatFormTSSCServiceRequest> list = tSSCServiceRequestMapper.selectPlatFormTSSCServiceRequest(sampleid);
		if (list.size() > 0) {
			request = list.get(0);
		} else {
			logger.error("PlatFormTSSCServiceRequest没有值");
		}
		// 当期操作时间
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		request.setFCD(df.format(day));
		logger.debug(request.toString());
		return request;
	}

	@Override
	public BiosanResult sendPlatFormTSSCService(Newtouchtsscresult newtouchtsscresult) {
		BiosanResult result = new BiosanResult();
		//更新发送时间
		newtouchtsscresult.setSendattime(new Date());
		logger.debug(newtouchtsscresult.toString());
		// 未发过 表newtouchtsscresult没有数据
		if (newtouchtsscresult.getTsscresult() == null) {
			logger.info("样品ID为" + newtouchtsscresult.getSampleid() + "未发送过报告");
			result = sendNum(newtouchtsscresult.getSampleid(), 1);
			newtouchtsscresult.setTsscresult(result.getStatus());
			newtouchtsscresultMapper.insert(newtouchtsscresult);
		} else {
			// 表newtouchtsscresult有数据
			// 更新 pdfdate报告日期
			//tsscresult为2
			// 成功发送
			if (newtouchtsscresult.getTsscresult() == 1) {
				logger.info("样品ID为" + newtouchtsscresult.getSampleid() + "重新风险计算了，发送撤销命令");
				result = sendNum(newtouchtsscresult.getSampleid(), 3);

				if (result.getStatus() == 1) {
					result = sendNum(newtouchtsscresult.getSampleid(), 1);

				}
				newtouchtsscresult.setTsscresult(result.getStatus());
				logger.debug(newtouchtsscresult.toString());
				newtouchtsscresultMapper.updateByBean(newtouchtsscresult);
			}
			//tsscresult为2
			//发送失败
			if (newtouchtsscresult.getTsscresult() == 2) {
				// 发送失败的 pdfdate报告日期在一天之内
				logger.info("样品ID为" + newtouchtsscresult.getSampleid() + "之前发送报告失败");
				result = sendNum(newtouchtsscresult.getSampleid(), 1);
				newtouchtsscresult.setTsscresult(result.getStatus());
				newtouchtsscresultMapper.updateByBean(newtouchtsscresult);
			} 
			//tsscresult为3
			//发送撤销命令失败
			else {
				logger.info("样品ID为" + newtouchtsscresult.getSampleid() + "之前发送撤销命令失败");
				result = sendNum(newtouchtsscresult.getSampleid(), 3);

				if (result.getStatus() == 1) {
					result = sendNum(newtouchtsscresult.getSampleid(), 1);

				}
				newtouchtsscresult.setTsscresult(result.getStatus());
				logger.debug(newtouchtsscresult.toString());
				newtouchtsscresultMapper.updateByBean(newtouchtsscresult);
			}

		}
		return result;
	}
	
	
	/**
	 * 根据 参数NUM (resource.properties) 多次发送，成功则跳出，
	 * 失败NUM次跳出
	 * 
	 * @param sampleid 样品ID
	 * @param czqf 操作码	1 发送报告	2 撤销命令
	 * @return 
	 * 
	 * @author qibill
	 * 2018年6月7日上午9:05:55
	 */
	private BiosanResult sendNum(Integer sampleid, Integer czqf) {
		BiosanResult result = new BiosanResult();
		Integer number = Integer.valueOf(NUM);
		for (int i = 0; i < number; i++) {
			logger.info("第" + (i + 1) + "次发送");
			result = doWebservice(sampleid, czqf);
			if (result.getStatus() == 1) {
				return result;
			}
		}
		return result;
	} 

	/**
	 * 根据sampleid和czqf生成PlatFormTSSCServiceRequest,	转化成xml格式调用webservice。
	 * 根据返回的String提取结果BiosanResult，返回BiosanResult
	 * 
	 * @param sampleid 样品ID
	 * @param czqf 操作码	1 发送报告	2 撤销命令
	 * @return
	 * 
	 * @author qibill
	 * 2018年6月7日上午9:03:09
	 */
	private BiosanResult doWebservice(Integer sampleid, Integer czqf) {
        String report = czqf == 1 ? "报告信息" : "撤销命令";
        logger.debug("生成报告样板");
        PlatFormTSSCServiceRequest request = creator(sampleid, czqf);
        logger.info("样品ID为" + sampleid + "开始发送" + report);
        String tSSCReponse = newTouchService.platFormTSSCService(request.toXml());
        BiosanResult biosanResult = RequestUtil.getTSSCRequest(tSSCReponse);
        //判断
        if (biosanResult.getStatus() == 1) {
        	logger.info("样品ID为" + sampleid + report + "发送成功");
		} else {
			//失败
			//以czqf判断是发送报告失败，还是发送撤销命令失败
			biosanResult.setStatus(czqf == 1 ? 2 : 3);
	        logger.error("样品ID为" + sampleid + report + "发送失败");
		}
        return biosanResult;
	}
}

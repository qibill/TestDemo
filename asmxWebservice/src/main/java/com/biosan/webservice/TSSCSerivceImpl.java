package com.biosan.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.mapper.ContentBodyMapper;
import com.newtouch.mapper.ContentHeadMapper;
import com.newtouch.mapper.ContentItemMapper;
import com.newtouch.mapper.EmployeeMapper;
import com.newtouch.mapper.PlatFormTSSCServiceRequestMapper;
import com.newtouch.pojo.Content;
import com.newtouch.pojo.ContentBody;
import com.newtouch.pojo.ContentHead;
import com.newtouch.pojo.ContentItem;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;

@Service
public class TSSCSerivceImpl implements TSSCService {

	@Autowired
	private JdbcOperation jdbcOperation;
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

	@Override
	public PlatFormTSSCServiceRequest creatPlatFormTSSCServiceRequest(Integer sampleid, Integer czqf) {
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
		if (contentBody.getBGRGH() != null || "".equals(contentBody.getBGRGH())) {
			contentBody.setBGRXM(employeeMapper.selectEmployeename(contentBody.getBGRGH()).get(0));
			
		}
		if (contentBody.getSHRGH() != null || "".equals(contentBody.getSHRGH())) {
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

}

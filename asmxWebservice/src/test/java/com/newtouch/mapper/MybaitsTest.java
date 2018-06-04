package com.newtouch.mapper;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.biosan.utils.JsonUtils;
import com.biosan.webservice.TSSCService;
import com.newtouch.pojo.ContentBody;
import com.newtouch.pojo.ContentHead;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mybaits.xml")
public class MybaitsTest {
	
	@Autowired
	private ContentHeadMapper contentHeadMapper;
	@Autowired
	private ContentItemMapper contentItemMapper;
	@Autowired
	private ContentBodyMapper contentBodyMapper;
	@Autowired
	private PlatFormTSSCServiceRequestMapper tSSCServiceRequestMapper;
	@Autowired
	private SampleresultMapper sampleresultMapper;
	
	@Autowired
	private TSSCService service;
	@Autowired
	private EmployeeMapper employeeMapper;

	@Test
	public void employeeMapperTest() {
		String selectEmployeename = employeeMapper.selectEmployeename(10).get(0);
		System.out.println(selectEmployeename);
	}
	@Test
	public void sampleresultMapperTest() {
		List<Integer> list = sampleresultMapper.selectDaysSampleid(154);
		for (Integer integer : list) {
			System.out.println(integer);
			
		}
	}
	@Test
	public void serviceTest() {
		PlatFormTSSCServiceRequest creatPlatFormTSSCServiceRequest = service.creatPlatFormTSSCServiceRequest(74734, "1");
		System.out.println(creatPlatFormTSSCServiceRequest.toXml());
	}

	@Test
	public void contentHeadMapperTest() {
		List<ContentHead> contentHead = contentHeadMapper.selectContentHead(74734);
		for (ContentHead contentHead2 : contentHead) {
			System.out.println(JsonUtils.objectToJson(contentHead2));
		}
	}
	
	@Test
	public void contentItemMapperTest() {
		Map<String, Object> queryForMap = contentItemMapper.queryForMap(74734);
		for (String key : queryForMap.keySet()) {
			System.out.println(key + "=" + queryForMap.get(key));
		}
	}
	
	@Test
	public void contentBodyMapperTest() {
		List<ContentBody> list = contentBodyMapper.selectContentBody(74734);
		for (ContentBody contentBody : list) {
			System.out.println(JsonUtils.objectToJson(contentBody));
		}
	}
	@Test
	public void tSSCServiceRequestMapperTest() {
		List<PlatFormTSSCServiceRequest> list = tSSCServiceRequestMapper.selectPlatFormTSSCServiceRequest(74734);
		for (PlatFormTSSCServiceRequest contentBody : list) {
			System.out.println(JsonUtils.objectToJson(contentBody));
		}
	}
}

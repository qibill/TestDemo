package com.newtouch.mapper;

import java.util.List;

import com.biosan.pojo.Newtouchtsscresult;

public interface NewtouchtsscresultMapper {
	
	/**
	 * 插入数据
	 * 
	 * @param record
	 * @return
	 * 
	 * @author qibill
	 * 2018年6月6日下午4:25:49
	 */
	int insert(Newtouchtsscresult record);
	
	/**
	 * 搜索 [days]内生成的新报告 
	 * 
	 * @param days 天数
	 * @return
	 * 
	 * @author qibill
	 * 2018年6月6日下午4:26:03
	 */
	List<Newtouchtsscresult> selectNewSampleid(Integer days);

	/**
	 * 修改数据
	 * 
	 * @param record
	 * @return
	 * 
	 * @author qibill
	 * 2018年6月6日下午4:28:31
	 */
	int updateByBean(Newtouchtsscresult record);

	/**
	 * 搜索失败的报告结果
	 * 
	 * @return
	 * 
	 * @author qibill
	 * 2018年6月6日下午4:24:25
	 */
	List<Newtouchtsscresult> selectErr();
	
	/**
	 * 按照样品ID搜索发送报告结果
	 * 
	 * @param sampleid 样品ID
	 * @return
	 * 
	 * @author qibill
	 * 2018年6月6日下午4:23:20
	 */
	List<Newtouchtsscresult> selectBySampleid(Integer sampleid);
}
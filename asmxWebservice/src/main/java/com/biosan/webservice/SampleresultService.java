package com.biosan.webservice;days

import java.util.List;

public interface SampleresultService {

	/**   
	 * @Title: selectDaysSampleid   
	 * @Description: 查找规定时间内的Sampleid
	 * @param days 规定时间
	 * @return  List<Integer>
	 */
	List<Integer> selectDaysSampleid(Integer days);
}

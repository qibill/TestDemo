package com.biosan.webservice;

import java.util.List;

import com.biosan.pojo.Newtouchtsscresult;

public interface NewtouchtsscresultService {

	int insert(Newtouchtsscresult record);
	
	int updateByBean(Newtouchtsscresult record);

	List<Newtouchtsscresult> selectDaysSampleid(Integer days);

	List<Newtouchtsscresult> selectErr();
}

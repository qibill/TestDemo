package com.newtouch.mapper;

import java.util.List;

import com.biosan.pojo.Newtouchtsscresult;

public interface NewtouchtsscresultMapper {
	
	int insert(Newtouchtsscresult record);
	
	List<Newtouchtsscresult> selectDaysSampleid(Integer days);

	int updateByBean(Newtouchtsscresult record);

	List<Newtouchtsscresult> selectErr();
	
}
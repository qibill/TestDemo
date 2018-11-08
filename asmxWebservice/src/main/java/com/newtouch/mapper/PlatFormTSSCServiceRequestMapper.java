package com.newtouch.mapper;

import java.util.List;

import com.newtouch.pojo.PlatFormTSSCServiceRequest;

public interface PlatFormTSSCServiceRequestMapper {

	List<PlatFormTSSCServiceRequest> selectPlatFormTSSCServiceRequest(Integer sampleid);
	
	List<String> selectReqnoForInsert(Integer sampleid);

	List<String> selectReqnoForDelete(Integer sampleid);
}

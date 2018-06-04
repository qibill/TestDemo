package com.newtouch.mapper;

import java.util.List;

import com.newtouch.pojo.ContentBody;

public interface ContentBodyMapper {

	List<ContentBody> selectContentBody(Integer sampleid);
}

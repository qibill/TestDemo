package com.newtouch.mapper;

import java.util.List;

import com.newtouch.pojo.ContentHead;

public interface ContentHeadMapper {

	List<ContentHead> selectContentHead(Integer sampleid);
}

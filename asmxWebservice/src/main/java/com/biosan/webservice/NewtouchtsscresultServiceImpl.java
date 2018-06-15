package com.biosan.webservice;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biosan.pojo.Newtouchtsscresult;
import com.newtouch.mapper.NewtouchtsscresultMapper;

@Service
public class NewtouchtsscresultServiceImpl implements NewtouchtsscresultService {

	private static final Logger logger = Logger.getLogger(NewtouchtsscresultServiceImpl.class);

	@Autowired
	private NewtouchtsscresultMapper newtouchtsscresultMapper;
	
	@Override
	public int insert(Newtouchtsscresult record) {
		record.setSendattime(new Date());
		logger.debug(record.toString());
		return newtouchtsscresultMapper.insert(record);
	}

	@Override
	public int updateByBean(Newtouchtsscresult record) {
		
		record.setSendattime(new Date());
		logger.debug(record.toString());
		return newtouchtsscresultMapper.updateByBean(record);
	}

	@Override
	public List<Newtouchtsscresult> selectNewSampleid(Integer days) {
		List<Newtouchtsscresult> list = newtouchtsscresultMapper.selectNewSampleid(days);
		return list;
	}

	@Override
	public List<Newtouchtsscresult> selectErr() {
		List<Newtouchtsscresult> list = newtouchtsscresultMapper.selectErr();
		return list;
	}

	@Override
	public List<Newtouchtsscresult> selectBySampleid(Integer sampleid) {
		List<Newtouchtsscresult> list = newtouchtsscresultMapper.selectBySampleid(sampleid);
		return list;
	}

}

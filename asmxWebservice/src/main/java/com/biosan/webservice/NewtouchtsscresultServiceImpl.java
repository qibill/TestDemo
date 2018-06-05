package com.biosan.webservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biosan.pojo.Newtouchtsscresult;
import com.newtouch.mapper.NewtouchtsscresultMapper;

@Service
public class NewtouchtsscresultServiceImpl implements NewtouchtsscresultService {

	@Autowired
	private NewtouchtsscresultMapper newtouchtsscresultMapper;
	
	@Override
	public int insert(Newtouchtsscresult record) {
		record.setSendattime(new Date());
		return newtouchtsscresultMapper.insert(record);
	}

	@Override
	public int updateByBean(Newtouchtsscresult record) {
		record.setSendattime(new Date());
		return newtouchtsscresultMapper.updateByBean(record);
	}

	@Override
	public List<Newtouchtsscresult> selectDaysSampleid(Integer days) {
		List<Newtouchtsscresult> list = newtouchtsscresultMapper.selectDaysSampleid(days);
		return list;
	}

	@Override
	public List<Newtouchtsscresult> selectErr() {
		List<Newtouchtsscresult> list = newtouchtsscresultMapper.selectErr();
		return list;
	}

}

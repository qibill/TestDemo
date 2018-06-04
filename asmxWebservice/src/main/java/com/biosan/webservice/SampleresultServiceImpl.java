package com.biosan.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.mapper.SampleresultMapper;

@Service
public class SampleresultServiceImpl implements SampleresultService {

    @Autowired
    private SampleresultMapper sampleresultMapper;

    @Override
    public List<Integer> selectDaysSampleid(Integer days) {
        return sampleresultMapper.selectDaysSampleid(days);
    }

}

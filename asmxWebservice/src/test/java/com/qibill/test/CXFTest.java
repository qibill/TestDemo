package com.qibill.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.weather.ArrayOfString;
import com.weather.WeatherWebServiceSoap;

public class CXFTest {

    @Test
    public void doWeather() {
        //初始化spring的上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        WeatherWebServiceSoap  weatherInterface = (WeatherWebServiceSoap) context.getBean("weatherClient");
        ArrayOfString supportCity = weatherInterface.getSupportCity("浙江");
        for (String city : supportCity.getString()) {
            System.out.println(city);
        }

    }
}

package com.qibill.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateTest {

    @Test
    public void now() {
        Date day=new Date();    
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        System.out.println(df.format(day));   
    }
}

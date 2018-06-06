package com.qibill.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

public class DateTest {

    @Test
    public void now() {
        Date day=new Date();    
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        System.out.println(df.format(day));   
    }
    
    
    @Test
    public void radom() {
    	Random random = new Random();
    	System.out.println(random.nextInt(5));
    }
}

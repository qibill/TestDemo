package com.biosan.quartz;

import java.util.Date;

public class Job1Demo {
    public void execute() {  
        System.out.println(new Date() + " -> Hello, 我是任务 1");  
    }  
}

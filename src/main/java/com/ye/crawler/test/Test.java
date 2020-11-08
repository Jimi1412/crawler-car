package com.ye.crawler.test;

import com.ye.crawler.CarPipeLine;
import com.ye.crawler.GZPageProcessor;
import com.ye.crawler.YXPageProcessor;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        testGZPageProcessor();
        testYXPageProcessor();

    }
    public static void testYXPageProcessor(){
        List<String> urls=new ArrayList<>();
        for(int i=1;i<=10;i++){
            String url="https://www.xin.com/guangzhou/i"+i+"/";
            urls.add(url);
        }
        Spider.create(new YXPageProcessor())
                .addUrl(urls.toArray(new String[10]))
                .addPipeline(new CarPipeLine())
                .thread(5)
                .start();
    }
    public static void  testGZPageProcessor(){
        List<String> urls=new ArrayList<>();
        for(int i=1;i<=10;i++){
            String url="https://www.guazi.com/gz/buy/o"+i+"/#bread";
            urls.add(url);
        }
        Spider.create(new GZPageProcessor())
                .addUrl(urls.toArray(new String[10]))
                .addPipeline(new CarPipeLine())
                .thread(5)
                .start();
    }
}

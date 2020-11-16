package com.trans.rabbitmq;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;

public class Demo implements Runnable{

    private final CountDownLatch countDownLatch ;

    RestTemplate restTemplate = new RestTemplate();

    public Demo(CountDownLatch countDownLatch) {
        super();
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //do
//        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:9407/cnRegion/loadRegionInfosByCityCriName?cityName=北京市", String.class);
        long start=System.currentTimeMillis();
        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:9408/esHotel/recommend/areaName/郑州市", String.class);
        long end=System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+"调用时间:"+(end-start)/1000 +"ssss"+(end-start));
        System.out.println(result);
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        int failurecnt=0;
        for(int i=500;i>0;i--){
            long start=System.currentTimeMillis();
            new Thread(new Demo(countDownLatch)).start();
            long end=System.currentTimeMillis();
            System.out.println(i+"调用时间:"+(end-start)/1000 +"ssss"+(end-start));
        }
        countDownLatch.countDown();
    }

}
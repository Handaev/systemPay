package com.example.SystemPay.service;


import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ConcurrencyService {

    BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10,
            20,
            60L,
            TimeUnit.SECONDS,
            queue,
            new ThreadPoolExecutor.AbortPolicy());





}

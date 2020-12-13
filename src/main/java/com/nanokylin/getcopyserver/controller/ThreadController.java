package com.nanokylin.getcopyserver.controller;

import com.nanokylin.getcopyserver.common.Config;
import com.nanokylin.getcopyserver.common.Resources;
import com.nanokylin.getcopyserver.service.ThreadPoolService;
import com.nanokylin.getcopyserver.service.impl.ThreadPoolServiceImpl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadController {
    public void initThreadPool() {
        // 设置线程
        Resources.threadPool = new ThreadPoolServiceImpl((int) Config.getConfig("corePoolSize"), (int) Config.getConfig("maximumPoolSize"),
                Config.getLong("keepAliveTime"), TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>((int) Config.getConfig("queue")));
    }

    public ThreadPoolService getThreadPool() {
        return Resources.threadPool;
    }
}

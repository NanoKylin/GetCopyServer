package com.nanokylin.getcopyserver.controller;

import com.nanokylin.getcopyserver.service.ConsoleService;
import com.nanokylin.getcopyserver.service.impl.ConsoleServiceImpl;
import com.nanokylin.getcopyserver.utils.LogUtil;

import java.util.Scanner;

public class ConsoleController {
    public void initConsole(ThreadController threadController) {
        // 新建控制台线程
        Thread consoleThread = new ConsoleThread();
        threadController.getThreadPool().execute(consoleThread);
    }
}

class ConsoleThread extends Thread {
    private static final LogUtil log = new LogUtil();
    private static final ConsoleService consoleService = new ConsoleServiceImpl();

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        super.setName("ConsoleThread");
        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            String command = sc.nextLine();
            consoleService.execute(command);
        }
    }
}

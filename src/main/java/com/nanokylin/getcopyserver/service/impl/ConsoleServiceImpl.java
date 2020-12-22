package com.nanokylin.getcopyserver.service.impl;

import com.nanokylin.getcopyserver.service.ConsoleService;

public class ConsoleServiceImpl implements ConsoleService {
    @Override
    public void execute(String command) {
        if (command.contains("help")) {
            System.out.println("假装这是一个help");
        } else if (command.contains("exit")) {
            System.exit(0);
        }
    }
}

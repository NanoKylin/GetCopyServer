package com.nanokylin.getcopyserver.common.constant;

import java.util.Properties;

public class BaseInfo {
    static final Properties props = System.getProperties();
    //系统名称
    public static final String SYSTEM_NAME = props.getProperty("os.name");
    //构架类型
    public static final String SYSTEM_ARCH = props.getProperty("os.arch");
    //系统版本
    public static final String SYSTEM_VERSION = props.getProperty("os.version");
    //Java版本
    public static final String JAVA_VM_VERSION = props.getProperty("java.vm.version");
    //Java供应商
    public static final String JAVA_VM_VENDOR = props.getProperty("java.vm.vendor");
    //Java虚拟机实现名称
    public static final String JAVA_VM_NAME = props.getProperty("java.vm.name");
    //Java类格式版本号
    public static final String JAVA_CLASS_VERSION = props.getProperty("java.class.version");
    //Java类路径
    public static final String JAVA_CLASS_PATH = props.getProperty("java.class.path");
    //CatPawServer版本号
    public static final String GET_COPY_SERVER_VERSION = "1.0.0 GET COPY SERVER (Bate)";
    //CatPawServer构建时间
    public static final String GET_COPY_SERVER_BUILD_TIME = "2020/12/13 SUN Hanbings 3219065882@qq.com";
    //LOGO
    public static final String GET_COPY_SERVER_LOGO ="             \n" +
            "  ________        __   _________                      \n" +
            " /  _____/  _____/  |_ \\_   ___ \\  ____ ______ ___.__.\n" +
            "/   \\  ____/ __ \\   __\\/    \\  \\/ /  _ \\\\____ <   |  |\n" +
            "\\    \\_\\  \\  ___/|  |  \\     \\___(  <_> )  |_> >___  |\n" +
            " \\______  /\\___  >__|   \\______  /\\____/|   __// ____|\n" +
            "        \\/     \\/              \\/       |__|   \\/    ";
}


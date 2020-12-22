package com.nanokylin.getcopyserver.common.constant;

/**
 * 协议类
 * 存放协议
 */
public class Protocol {
    /////////////////// 协议数据包头 ///////////////////
    // 数据包头
    public static final String PACKAGE_HEAD = "GCS";
    // 数据包客户端标志位头
    public static final String CLIENT = "C";
    // 数据包服务端标志位头
    public static final String SERVER = "S";
    // 数据包尾
    public static final String PACKAGE_END = "EE";
    //////////////////////////////////////////////////

    //////////////////// 登陆部分 /////////////////////
    // 登陆协议
    public static final String CLIENT_LOGIN = "01";
    // 登陆协议
    public static final String SERVER_LOGIN = "01";
    // 登出
    public static final String CLIENT_LOG_OUT = "04";
    //////////////////////////////////////////////////

    //////////////////// 内容部分 /////////////////////
    // 获取内容
    public static final String CLIENT_PUT_CONTEXT = "02";
    // 获取内容
    public static final String SERVER_SEND_CONTEXT = "02";
    // 获取所有内容
    public static final String CLIENT_GET_ALL_CONTEXT = "03";
    //////////////////////////////////////////////////

}

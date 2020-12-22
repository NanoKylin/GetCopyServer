package com.nanokylin.getcopyserver.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nanokylin.getcopyserver.common.Config;
import com.nanokylin.getcopyserver.common.Resources;
import com.nanokylin.getcopyserver.common.constant.Protocol;
import com.nanokylin.getcopyserver.service.WebSocketPoolService;
import com.nanokylin.getcopyserver.service.impl.WebSocketPoolServiceImpl;
import com.nanokylin.getcopyserver.utils.LogUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class WebSocketController extends WebSocketServer {
    private static final LogUtil log = new LogUtil();
    private static final WebSocketPoolService webSocketPoolService = new WebSocketPoolServiceImpl();

    public WebSocketController() {
    }

    public WebSocketController(InetSocketAddress address) {
        super(address);

    }

    public void initWebSocket(ThreadController threadController) {
        // 实例化WebSocket服务
        //WebSocketService webSocketService = new WebSocketServiceImpl();
        // 新建控制台线程
        Thread WebSocketThread = new WebSocketThread();
        threadController.getThreadPool().execute(WebSocketThread);
    }

    @Override
    public void onOpen(WebSocket connect, ClientHandshake handshake) {
        log.info("新连接: " + connect.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket connect, int code, String reason, boolean remote) {
        log.info("关闭: " + connect.getRemoteSocketAddress() + " 退出代码: " + code + " 地址信息: " + reason);
        this.userLeave(connect);
    }

    @Override
    public void onMessage(WebSocket connect, String message) {
        if (message.length() > 6) {
            if (message.substring(0, 3).contains(Protocol.PACKAGE_HEAD)) {
                if (message.substring(3, 4).contains(Protocol.CLIENT)) {
                    String json = message.substring(6, message.length() - 2);
                    if (message.substring(4, 6).contains(Protocol.CLIENT_LOGIN)) {
                        //message.substring(6, message.length() - 2)
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(json);
                        JsonObject root = element.getAsJsonObject();
                        userJoin(connect, root.get("username").getAsString());
                        ArrayList<String> list = new ArrayList<>();
                        Resources.UserContext.put(root.get("username").getAsString(), list);
                        connect.send(Protocol.PACKAGE_HEAD + Protocol.SERVER + Protocol.SERVER_LOGIN + "{\"context\":\"LOGIN SUCCESSFUL\"}" + Protocol.PACKAGE_END);
                    }
                    if (message.substring(4, 6).contains(Protocol.CLIENT_PUT_CONTEXT)) {
                        //message.substring(6, message.length() - 2)
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(json);
                        JsonObject root = element.getAsJsonObject();
                        String username = root.get("username").getAsString();
                        if (Resources.UserContext.containsKey(username)) {
                            Resources.UserContext.get(username).add(root.get("context").getAsString());
                            connect.send(Protocol.PACKAGE_HEAD + Protocol.SERVER + Protocol.SERVER_SEND_CONTEXT + "{\"username\":\"hanbings\",\"context\":" + root.get("context").getAsString() + ",\"picture\":\"12345678\"}" + Protocol.PACKAGE_END);
                        }
                    }
                    if (message.substring(4, 6).contains(Protocol.CLIENT_GET_ALL_CONTEXT)) {
                        //message.substring(6, message.length() - 2)
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(json);
                        JsonObject root = element.getAsJsonObject();
                        String username = root.get("username").getAsString();
                        if (Resources.UserContext.containsKey(username)) {
                            for (int i = 0; i < Resources.UserContext.get(username).size(); i++) {
                                connect.send(Protocol.PACKAGE_HEAD + Protocol.SERVER + Protocol.SERVER_SEND_CONTEXT + "{\"username\":\"hanbings\",\"context\":" + Resources.UserContext.get(username).get(i) + ",\"picture\":\"12345678\"}" + Protocol.PACKAGE_END);
                            }
                        }
                    }
                    if (message.substring(4, 6).contains(Protocol.CLIENT_LOG_OUT)) {
                        //message.substring(6, message.length() - 2)
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(json);
                        JsonObject root = element.getAsJsonObject();
                        String username = root.get("username").getAsString();
                        Resources.UserContext.remove(username);
                    }
                }
            }
        }
    }

    @Override
    public void onMessage(WebSocket connect, ByteBuffer message) {
        log.info("已收到ByteBuffer来自: " + connect.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket connect, Exception ex) {
        log.info("连接时发生错误: " + connect.getRemoteSocketAddress() + ":" + ex);
    }

    @Override
    public void onStart() {
        log.info("WebSocket服务成功启动！");
    }

    /**
     * 去除掉失效的websocket链接
     */
    public void userLeave(WebSocket connect) {
        webSocketPoolService.removeUser(connect);
    }

    /**
     * 将websocket加入用户池
     */
    public void userJoin(WebSocket connect, String userName) {
        webSocketPoolService.addUser(userName, connect);
    }
}

class WebSocketThread extends Thread {
    private static final LogUtil log = new LogUtil();

    @Override
    public void run() {
        super.setName("WebSocketThread");
        String host = (String) Config.getConfig("ip");
        int port = (int) Config.getConfig("port");
        WebSocketServer s = new WebSocketController(new InetSocketAddress(host, port));
        s.run();
    }
}

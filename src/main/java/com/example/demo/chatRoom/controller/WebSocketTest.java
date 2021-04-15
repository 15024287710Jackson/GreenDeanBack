package com.example.demo.chatRoom.controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.example.demo.chatRoom.action.Msg;
import com.example.demo.chatRoom.action.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/test/{userId}")
@Component
public class WebSocketTest {
    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    private String userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        System.out.println("打开了一个连接");
        System.out.println("userId:"+userId);
        System.out.println("session.getId():"+session.getId());
        this.userId = userId;

        User user = new User();
        user.setUserMsg(new Msg(false,"有新人加入聊天",true));
        sendAll(user.toString());

        //将新用户存入在线的组
        clients.put(userId, session);
    }

    /**
     * 客户端关闭
     * @param session session
     */
    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        System.out.println("有用户断开了");
        System.out.println("userId:"+userId);
        System.out.println("session.getId():"+session.getId());

        User user = new User();
        user.setUserMsg(new Msg(false,"有用户断开聊天",true));
        sendAll(user.toString());

        //将掉线的用户移除在线的组里
        clients.remove(userId);
    }

    /**
     * 发生错误
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     * @param message  消息对象
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("服务端收到客户端发来的消息:"+message);
        User user = JSON.parseObject(message, User.class);
        user.getUserMsg().setSend(false);

        this.sendAll(user.toString());
    }

    /**
     * 群发消息
     * @param message 消息内容
     */
    private void sendAll(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            if (!sessionEntry.getKey().equals(userId)){
                sessionEntry.getValue().getAsyncRemote().sendText(message);
            }
        }
    }
}

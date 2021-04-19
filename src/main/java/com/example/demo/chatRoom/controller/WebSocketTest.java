package com.example.demo.chatRoom.controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.chatRoom.action.Msg;
import com.example.demo.chatRoom.action.User;
import com.example.demo.chatRoom.action.UserList;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
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
    private static Map<String, User> YongHuMen = new ConcurrentHashMap<>();


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
        YongHuMen.remove(userId);

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
    public void onMessage(String message) throws IOException {
        System.out.println("服务端收到客户端发来的消息:"+message);
        User user = JSON.parseObject(message, User.class);
        user.getUserMsg().setSend(false);

        if(user.getUserMsg().isPrivate())
        {
            if(clients.get(user.getUserId())!=null && clients.get(user.getUserId()).isOpen())
            {
                sendInfo(user.toString(),user.getUserMsg().getTargetUserID());
            }

        }

        if(user.getUserMsg().isConfi())//如果是配置语句
        {
            System.out.println("get config!");

            if(user.getUserMsg().getContent().equals("CloseTheSessionPLZ"))
            {

                if(clients.get(user.getUserId())!=null && clients.get(user.getUserId()).isOpen())
                {
                    System.out.println("关闭连接！");
                    clients.get(user.getUserId()).close();
                }
            }//当客户端退出聊天室时关闭连接
            if(user.getUserMsg().getContent().equals("CloseTheSessionPLZandOPENprivate"))
            {
                if(clients.get(user.getUserId())!=null && clients.get(user.getUserId()).isOpen())
                {
                    user.getUserMsg().setContent("okyoucanclose");
                    sendInfo(user.toString(),user.getUserId());
                    System.out.println("关闭连接！");
                    clients.get(user.getUserId()).close();
                }
            }//当客户切换到私聊时 关闭连接并发回允许




            //客户端发起私聊，请求在线用户名字头像
            if(user.getUserMsg().getContent().equals("ShEnQiNgLiEbIaO"))
                {
                System.out.println("StartSendList!");
                //Session session1 = clients.get(user.getUserId());
                //Msg msg888 = new Msg(true,"KaiShiChuanSong",false);
                //User tempUser888 = new User();
                //tempUser888.setUserMsg(msg888);
                //tempUser888.getUserMsg().setConfi(true);
                //session1.getAsyncRemote().sendText(tempUser888.toString());


                ArrayList<User> userArrayList= new ArrayList<User>();
                for (Map.Entry<String, User> sessionEntry : YongHuMen.entrySet())
                {
                    if (!sessionEntry.getKey().equals(user.getUserId())) {
                        User TempUser0000 = sessionEntry.getValue();
                        TempUser0000.getUserMsg().setConfi(true);
                        TempUser0000.getUserMsg().setContent("gEiNiShEnQiNgLiEbIaO");
                        userArrayList.add(TempUser0000);
                    }
                }
                if(clients.get(user.getUserId())!=null&&clients.get(user.getUserId()).isOpen()&&!userArrayList.isEmpty())
                {
                    JSONArray userlistjsonArray = JSONArray.parseArray(JSONObject.toJSONString(userArrayList));
                    System.out.println(userlistjsonArray.toJSONString());
                    sendInfo(userlistjsonArray.toJSONString(),user.getUserId());
                }
                else {
                    System.out.println("已关闭1");
                }
                return;


                /*UserList userlist = new UserList();

                for (Map.Entry<String, User> sessionEntry : YongHuMen.entrySet())
                {
                    if (!sessionEntry.getKey().equals(user.getUserId())) {
                        User TempUser0000 = sessionEntry.getValue();
                        TempUser0000.getUserMsg().setConfi(true);
                        TempUser0000.getUserMsg().setContent("gEiNiShEnQiNgLiEbIaO");
                        userlist.addUser(TempUser0000);
                        userlist.setEmpty(false);
                    }
                }
                userlist.setIsUserList(true);
                if(clients.get(user.getUserId())!=null&&clients.get(user.getUserId()).isOpen())
                {
                    sendInfo(userlist.toString(),user.getUserId());
                }
                else {
                    System.out.println("已关闭1");
                }
                //发送
                userlist.cleanuser();
                return;*/

            }



            //客户端第一次上传头像和名字
            if(user.getUserMsg().getContent().equals("PaSsThEtOuXiAnGhEmInGzI"))
            {
                YongHuMen.put(user.getUserId(),user);
                User tempnewuser = user;
                tempnewuser.getUserMsg().setConfi(true);
                tempnewuser.getUserMsg().setContent("NEWUSERCOME");

                this.sendAll(user.toString());
                return;
            }

            //配置语句直接返回
        }


        else this.sendAll(user.toString());//如果不是配置语句或者私聊语句就直接发送
    }




    /**
     * 群发消息
     * @param message 消息内容
     */
    private void sendAll(String message) {
        User user = JSON.parseObject(message, User.class);
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            if (!sessionEntry.getKey().equals(user.getUserId())){
                if(sessionEntry.getValue() != null && sessionEntry.getValue().isOpen())
                {
                    sessionEntry.getValue().getAsyncRemote().sendText(message);
                }

            }
        }
    }


    //private void sendPrivate(User user,String targetuserID) throws IOException {
        //Session session2 = clients.get(targetuserID);
    //    user.getUserMsg().setPrivate(true);
    //    clients.get(targetuserID).getAsyncRemote().sendText(user.toString());
    //}


    public static void sendInfo(String text,@PathParam("userId") String userId) {
        try {
            Session session = clients.get(userId);
            if (session != null && session.isOpen()) {
                session.getAsyncRemote().sendText(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

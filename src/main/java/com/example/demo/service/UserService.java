package com.example.demo.service;

import com.example.demo.duixiang.UserInfo;
import com.example.demo.mapper.UserInfomationMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    List<UserInfo> getAll();

    UserInfo getEachInfo(String id);

    List<UserInfo> checkUsers(String userName);

    UserInfo loginContr( String userName,String passWord);

    int Resister(String id,String userName, String passWord,String emailAddress,String telephoneNumber);
}

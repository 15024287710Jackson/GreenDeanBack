package com.example.demo.service.impl;

import com.example.demo.duixiang.UserInfo;
import com.example.demo.mapper.UserInfomationMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfomationMapper userInfomationMapper;
    @Override
    public List<UserInfo> getAll(){
        return userInfomationMapper.getAll();
    }

    @Override
    public UserInfo getEachInfo(String id) {
        return userInfomationMapper.getEachInfo(id);
    }

    @Override
    public List<UserInfo> checkUsers(String userName) {
        return userInfomationMapper.checkUsers(userName);
    }

    @Override
    public UserInfo loginContr(String userName, String passWord) {
        return userInfomationMapper.loginContr(userName,passWord);
    }

    @Override
    public int Resister(String id, String userName, String passWord, String emailAddress, String telephoneNumber) {
        return userInfomationMapper.Resister(id,userName,passWord,emailAddress,telephoneNumber);
    }

}


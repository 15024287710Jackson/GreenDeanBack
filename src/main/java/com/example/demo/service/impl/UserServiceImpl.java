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
    public List<UserInfo> getAll(){
        return userInfomationMapper.getAll();
    }
}


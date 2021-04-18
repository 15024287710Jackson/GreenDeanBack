package com.example.demo.service.impl;

import com.example.demo.duixiang.UserInfo;
import com.example.demo.mapper.UserInfomationMapper;
import com.example.demo.service.UserService;
import com.example.demo.upLoadFile.action.UserPicInfo;
import com.example.demo.upLoadFile.action.UserVideoInfo;
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

    @Override
    public int insertPicInfo(String number, String id, String message, String pictureUrl) {
        return userInfomationMapper.insertPicInfo(number,id,message,pictureUrl);
    }

    @Override
    public int insertVideoInfo(String number, String id, String message, String videoUrl) {
        return userInfomationMapper.insertVideoInfo(number,id,message,videoUrl);
    }

    @Override
    public List<UserPicInfo> selectUserPicInfo(String id) {
        return userInfomationMapper.selectUserPicInfo(id);
    }

    @Override
    public List<UserVideoInfo> selectUserVideoInfo(String id) {
        return userInfomationMapper.selectUserVideoInfo(id);
    }

    @Override
    public int delectPicInfo(String Number) {
        return userInfomationMapper.delectPicInfo(Number);
    }

    @Override
    public int delectVideoInfo(String Number) {
        return userInfomationMapper.delectVideoInfo(Number);
    }

    @Override
    public int updatePicInfo(String number, String id, String message, String pictureUrl) {
        return userInfomationMapper.updatePicInfo(number,id,message,pictureUrl);
    }

    @Override
    public int updateVideoInfo(String number, String id, String message, String videoUrl) {
        return userInfomationMapper.updateVideoInfo(number,id,message,videoUrl);
    }

    @Override
    public UserPicInfo randomSelectImage() {
        return userInfomationMapper.randomSelectImage();
    }

    @Override
    public UserVideoInfo randomSelectVideo() {
        return userInfomationMapper.randomSelectVideo();
    }

}


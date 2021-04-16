package com.example.demo.service;

import com.example.demo.duixiang.UserInfo;
import com.example.demo.mapper.UserInfomationMapper;
import com.example.demo.upLoadFile.action.UserPicInfo;
import com.example.demo.upLoadFile.action.UserVideoInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserService {

    List<UserInfo> getAll();

    UserInfo getEachInfo(String id);

    List<UserInfo> checkUsers(String userName);

    UserInfo loginContr( String userName,String passWord);

    int Resister(String id,String userName, String passWord,String emailAddress,String telephoneNumber);

    int insertPicInfo(String number,String id, String message, String pictureUrl);

    int insertVideoInfo( String number, String id, String message,String videoUrl);

    List<UserPicInfo> selectUserPicInfo(String id);

    List<UserVideoInfo> selectUserVideoInfo(String id);
}

package com.example.demo.mapper;

import com.example.demo.duixiang.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedList;
import java.util.List;

@Mapper
public interface UserInfomationMapper {


    @Select("SELECT * FROM userinfomation")
    List<UserInfo> getAll();
}

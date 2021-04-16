package com.example.demo.mapper;

import com.example.demo.duixiang.UserInfo;
import com.example.demo.upLoadFile.action.UserPicInfo;
import com.example.demo.upLoadFile.action.UserVideoInfo;
import org.apache.ibatis.annotations.*;

import java.util.LinkedList;
import java.util.List;

@Mapper
public interface UserInfomationMapper {

    @Select("SELECT * FROM userinfomation")
    List<UserInfo> getAll();

    @Select("SELECT * from userinfomation a where a.id=#{id,jdbcType=VARCHAR}")
    UserInfo getEachInfo(@Param("id") String id);
    //查找是否存在相同username
    @Select("SELECT * from userinfomation a where a.userName=#{userName,jdbcType=VARCHAR}")
    List<UserInfo> checkUsers(@Param("userName") String userName);
    //登录查找
    @Select("SELECT * from userinfomation a where a.userName=#{userName,jdbcType=VARCHAR} and a.passWord=#{passWord,jdbcType=VARCHAR}")
    UserInfo loginContr(@Param("userName") String userName,@Param("passWord") String passWord);

    @Insert("INSERT INTO userinfomation(id,userName,passWord,emailAddress,telephoneNumber) VALUES (#{id,jdbcType=VARCHAR}" +
            ",#{userName,jdbcType=VARCHAR},#{passWord,jdbcType=VARCHAR},#{emailAddress,jdbcType=VARCHAR},#{telephoneNumber,jdbcType=VARCHAR})")
    int Resister(@Param("id") String id,@Param("userName") String userName,
                 @Param("passWord") String passWord,@Param("emailAddress") String emailAddress,
                 @Param("telephoneNumber") String telephoneNumber);

    @Insert("insert into UserPicture (Number,id, message,pictureUrl) values (#{number,jdbcType=VARCHAR},#{id,jdbcType=VARCHAR},#{message,jdbcType=VARCHAR},#{pictureUrl,jdbcType=VARCHAR})")
    int insertPicInfo(@Param("number") String number,
                      @Param("id") String id,
                      @Param("message") String message,
                      @Param("pictureUrl") String pictureUrl);

    @Insert("insert into UserVideo (Number,id, message,videoUrl) values (#{number,jdbcType=VARCHAR},#{id,jdbcType=VARCHAR},#{message,jdbcType=VARCHAR},#{videoUrl,jdbcType=VARCHAR})")
    int insertVideoInfo(@Param("number") String number,
                      @Param("id") String id,
                      @Param("message") String message,
                      @Param("videoUrl") String videoUrl);

    @Select("SELECT * from UserPicture where id = #{id,jdbcType=VARCHAR}")
    List<UserPicInfo> selectUserPicInfo(@Param("id") String id);

    @Select("SELECT * from UserVideo where id = #{id,jdbcType=VARCHAR}")
    List<UserVideoInfo> selectUserVideoInfo(@Param("id") String id);

}

package com.example.demo.resgisterLogin.controller;

import com.example.demo.duixiang.Animal;
import com.example.demo.duixiang.UserInfo;
import com.example.demo.resgisterLogin.action.ResgisterResponse;
import com.example.demo.resgisterLogin.action.ResgisterResquest;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/resgisterLogin")
public class ResgisterLoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/resgisterUser")
    public ResgisterResponse getName(@RequestBody ResgisterResquest resgisterResquest){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String id =df.format(new Date());
        String userName = resgisterResquest.getUserName();
        String passWord = resgisterResquest.getPassWord();
        String emailAddress = resgisterResquest.getEmailAddress();
        String telephoneNumber = resgisterResquest.getTelephoneNumber();
        int result =0;
        ResgisterResponse resgisterResponse = new ResgisterResponse();
        List<UserInfo> userInfos = new LinkedList<>();
        try {
            userInfos = userServiceImpl.checkUsers(userName);
            if(userInfos.size()>0){
                resgisterResponse.setResult("N");
                resgisterResponse.setMsg("存在相同用户名");
            }else{
                result=userServiceImpl.Resister(id,userName,passWord,emailAddress,telephoneNumber);
            }
        }catch (Exception e){
            e.printStackTrace();
            resgisterResponse.setResult("N");
            resgisterResponse.setMsg("出错");
        }
        if(result == 1)
        {
            resgisterResponse.setResult("Y");
            resgisterResponse.setMsg("注册成功");
        }else {
            resgisterResponse.setResult("N");
            resgisterResponse.setMsg("注册失败，请重新注册");
        }
        return  resgisterResponse;
    }

    @PostMapping("/loginUser")
    public ResgisterResponse loginController(@RequestBody ResgisterResquest logResquest){
        String userName = logResquest.getUserName();
        String passWord = logResquest.getPassWord();
        ResgisterResponse resgisterResponse = new ResgisterResponse();
        UserInfo userInfos = new UserInfo();
        try{
            userInfos = userServiceImpl.loginContr(userName,passWord);

        }catch (Exception e){
            resgisterResponse.setResult("N");
            resgisterResponse.setMsg("登录失败");
            e.printStackTrace();
        }
        if(userInfos.getUserName().equals(userName) && userInfos.getPassWord().equals(passWord)){
            resgisterResponse.setResult("Y");
            resgisterResponse.setMsg("登录成功");
        }else{
            resgisterResponse.setResult("N");
            resgisterResponse.setMsg("登录失败");
        }
        return resgisterResponse;
    }
}

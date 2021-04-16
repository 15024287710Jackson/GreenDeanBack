package com.example.demo.controller;

import com.example.demo.duixiang.Animal;
import com.example.demo.duixiang.UserInfo;
import com.example.demo.service.impl.UserServiceImpl;
//import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/world")
    public Animal getName(){
        Animal animal = new Animal();
        animal.setName("Hui");
        animal.setAge(18);
        return animal;
    }

    @RequestMapping("/getWholeUserInfo")
    public List<UserInfo>  getWholeUserInfo(){
        List<UserInfo> userInfos = new LinkedList<>();
        userInfos=userServiceImpl.getAll();
        return userInfos;
    }

}

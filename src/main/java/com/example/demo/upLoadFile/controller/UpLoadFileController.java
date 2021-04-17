package com.example.demo.upLoadFile.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.upLoadFile.action.*;
import com.example.demo.utils.FileUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/upLoadFile")
public class UpLoadFileController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @PostMapping("/upLoadPic")


    public UpLoadPictureFileResquest uploadFile(@Param("file") MultipartFile file,
                                                @Param("id") String id,
                                                @Param("Message") String Message)  {
        UpLoadPictureFileResquest upLoadPictureFileResquest = new UpLoadPictureFileResquest();
        if (file.isEmpty()){
            upLoadPictureFileResquest.setMes("上传的文件不能为空！请重新上传");
            upLoadPictureFileResquest.setResult("N");
            return upLoadPictureFileResquest;
        }
        if (file.getSize()<=0){
            upLoadPictureFileResquest.setMes("上传的文件大小需要大于0kb");
            upLoadPictureFileResquest.setResult("N");
            return upLoadPictureFileResquest;
        }
        System.out.println(file.getContentType());//image/png
        Date date = new Date();
        Long time = date.getTime();
        String originFileName = file.getOriginalFilename();//获取文件原始的名称
        String newFileName = time+originFileName;
        //获取项目运行的绝对路径
//        String filePath = System.getProperty("user.dir");

        //由于我是创建的多模块项目，所以获取到的项目运行路径为外层的项目路径，
        // 这时候我们就需要在项目相对路径这里加上项目的名称demo-upload
//        String newFilePath = filePath+"\\GreenDeanBack\\src\\main\\resources\\static\\images\\";
        //String newFilePath = "E:\\GreenDeanUpLoad\\picture\\";
        String newFilePath = "/root/GreenDeanUpLoad/picture/";
        //当然你也可以自己设置一个绝对路径用于图片上传，文件上传。
        //比如说：D:\\images\\
        File file1 = new File(newFilePath);

        if (!file1.exists()){
            file1.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(newFilePath+newFileName);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String num =df.format(new Date());
            int result = userServiceImpl.insertPicInfo(num,id,Message,"http://101.37.75.202:8081/images/"+newFileName);
            if(result==1){
                upLoadPictureFileResquest.setMes("上传成功");
                upLoadPictureFileResquest.setResult("Y");
                upLoadPictureFileResquest.setUrl("http://101.37.75.202:8081/images/"+newFileName);
                upLoadPictureFileResquest.setNum(num);
            }else{
                upLoadPictureFileResquest.setMes("图片上传成功,但信息入库失败");
                upLoadPictureFileResquest.setResult("N");
                upLoadPictureFileResquest.setUrl("http://101.37.75.202:8081/images/"+newFileName);
            }
//            "localhost:8095/images/"+newFileName
            return upLoadPictureFileResquest;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        upLoadPictureFileResquest.setMes("上传失败");
        upLoadPictureFileResquest.setResult("N");
        return upLoadPictureFileResquest;
    }

    @PostMapping("/upLoadVideo")
    public UpLoadPictureFileResquest upLoadVideoFile(@Param("file") MultipartFile file,
                                                     @Param("id") String id,
                                                     @Param("Message") String Message){
        //    @Value("${web.upload-path}")
        //String path="E://GreenDeanUpLoad//video";
        String path="/root/GreenDeanUpLoad/video";
//    @Value("${web.image-path}")
        String imagePath="http://101.37.75.202:8081/video/";
        UpLoadPictureFileResquest upLoadPictureFileResquest = new UpLoadPictureFileResquest();
        try {
            if (file != null) {
                String fileName = FileUtil.upload(file, path, file.getOriginalFilename());
                if (fileName != null) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    String num =df.format(new Date());
                    int result = userServiceImpl.insertVideoInfo(num,id,Message,imagePath+fileName);
                    if(result==1){
                        upLoadPictureFileResquest.setMes("上传成功");
                        upLoadPictureFileResquest.setResult("Y");
                        upLoadPictureFileResquest.setUrl(imagePath+fileName);
                        upLoadPictureFileResquest.setNum(num);
                    }
                    return upLoadPictureFileResquest;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        upLoadPictureFileResquest.setMes("上传失败");
        upLoadPictureFileResquest.setResult("N");
        return upLoadPictureFileResquest;
    }


    @PostMapping("/selectUserPicInfos")
    public List<UserPicInfo>  selectUserPicInfos(@RequestBody CheckInfoResquest checkInfoResquest){
        String id = checkInfoResquest.getId();
        List<UserPicInfo> userPicInfoList = new LinkedList<>();
        userPicInfoList = userServiceImpl.selectUserPicInfo(id);
        return userPicInfoList;
    }

    @PostMapping("/selectUserVideoInfos")
    public List<UserVideoInfo> selectUserVideoInfos(@RequestBody CheckInfoResquest checkInfoResquest){
        String id = checkInfoResquest.getId();
        List<UserVideoInfo> userPicInfoList = new LinkedList<>();
        userPicInfoList = userServiceImpl.selectUserVideoInfo(id);
        return userPicInfoList;
    }

    @PostMapping("/deleteUserPicInfos")
    public JSONObject  deletePic(@RequestBody DeleteResquest deleteResquest){
        String num = deleteResquest.getNum();
        JSONObject jsonObject = new JSONObject();
        int result = userServiceImpl.delectPicInfo(num);
        if(result == 1){
            jsonObject.put("result","1");
        }else{
            jsonObject.put("result","0");
        }
        return jsonObject;
    }

    @PostMapping("/deleteUserVideoInfos")
    public JSONObject  deleteVideo(@RequestBody DeleteResquest deleteResquest){
        String num = deleteResquest.getNum();
        JSONObject jsonObject = new JSONObject();
        int result = userServiceImpl.delectVideoInfo(num);
        if(result == 1){
            jsonObject.put("result","1");
        }else{
            jsonObject.put("result","0");
        }
        return jsonObject;
    }

    @RequestMapping("/updatePicInfos")
    public JSONObject  updatePicInfos(@RequestBody UpdateResquest updateResquest){
        String number = updateResquest.getNumber();
        String id = updateResquest.getId();
        String message = updateResquest.getMessage();
        String pictureUrl = updateResquest.getPictureUrl();
        JSONObject jsonObject = new JSONObject();
        int result = userServiceImpl.updatePicInfo(number,id,message,pictureUrl);
        if(result == 1){
            jsonObject.put("result","1");
        }else{
            jsonObject.put("result","0");
        }
        return jsonObject;
    }

    @RequestMapping("/updateVideoInfos")
    public JSONObject  updateVideoInfos(@RequestBody UpdateResquest updateResquest){
        String number = updateResquest.getNumber();
        String id = updateResquest.getId();
        String message = updateResquest.getMessage();
        String pictureUrl = updateResquest.getPictureUrl();
        JSONObject jsonObject = new JSONObject();
        int result = userServiceImpl.updateVideoInfo(number,id,message,pictureUrl);
        if(result == 1){
            jsonObject.put("result","1");
        }else{
            jsonObject.put("result","0");
        }
        return jsonObject;
    }
}

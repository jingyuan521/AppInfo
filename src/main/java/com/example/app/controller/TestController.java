package com.example.app.controller;

import com.example.app.entity.AppInfo;
import com.example.app.entity.DTO.AppinfoDto;
import com.example.app.service.AppInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
@Controller
@RequestMapping("test")
@CrossOrigin
public class TestController {
    @Resource
    private AppInfoService appInfoService;
    //封装接口返回json数据
    @RequestMapping("/findlist")
    @ResponseBody
    public AppinfoDto findAppinfo(String id) throws Exception {
        AppInfo appInfo = appInfoService.getinfoByid(Integer.parseInt(id));
        AppinfoDto appinfoDto = new AppinfoDto();
        if (appInfo == null) {
            appinfoDto.setFail("false");
            appinfoDto.setMessage("查询失败");
            return appinfoDto;
        }else {
            appinfoDto.setSuccsess("ture");
            appinfoDto.setData(appInfo);
            return appinfoDto;
        }

    }
}

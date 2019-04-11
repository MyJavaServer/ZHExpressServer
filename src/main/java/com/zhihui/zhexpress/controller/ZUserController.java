package com.zhihui.zhexpress.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhihui.zhexpress.base.ZHResponse;
import com.zhihui.zhexpress.base.controller.ZHBaseController;
import com.zhihui.zhexpress.interfaces.Permission;
import com.zhihui.zhexpress.service.ZUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class ZUserController extends ZHBaseController {

    @Autowired
    private ZUserService mUserService;


    @PostMapping("/register")
    @ResponseBody
    @Permission(needToken = false)
    public Map register(@RequestBody JSONObject jsonParam){
        String phone = null;
        String iconUrl = null;
        String remarks = null;
        String nickName = null;
        String name = jsonParam.getString("name");
        String pwd = jsonParam.getString("password");

        if (name == null || pwd == null){
            return ZHResponse.failed("缺少用户名或密码！");
        }
        if (jsonParam.containsKey("phone"))
            phone = jsonParam.getString("phone");
        if (jsonParam.containsKey("iconUrl"))
            iconUrl = jsonParam.getString("iconUrl");
        if (jsonParam.containsKey("remarks"))
            remarks = jsonParam.getString("remarks");
        if (jsonParam.containsKey("nickName"))
            nickName = jsonParam.getString("nickName");
        return mUserService.register(name, pwd, phone, iconUrl, nickName, remarks);
    }

    @PostMapping("/login")
    @ResponseBody
    @Permission(needToken = false)
    public Map login(@RequestBody JSONObject jsonParam){
        String name = jsonParam.getString("name");
        String pwd = jsonParam.getString("password");
        if (name == null || pwd == null){
            return ZHResponse.failed("用户登录信息不完整！");
        }
        return mUserService.login(name, pwd);
    }

}

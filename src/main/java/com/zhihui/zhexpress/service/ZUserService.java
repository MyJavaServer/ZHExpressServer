package com.zhihui.zhexpress.service;

import com.zhihui.zhexpress.base.ZHResponse;
import com.zhihui.zhexpress.base.controller.ZHBaseController;
import com.zhihui.zhexpress.manager.RedisManager;
import com.zhihui.zhexpress.manager.UserTokenManager;
import com.zhihui.zhexpress.mapper.UserMapper;
import com.zhihui.zhexpress.model.User;
import com.zhihui.zhexpress.model.UserExample;
import com.zhihui.zhexpress.model.UserInfo;
import com.zhihui.zhexpress.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ZUserService {

    @Autowired
    private UserMapper mUserMapper;

    private UserTokenManager mUserTokenManager = UserTokenManager.getInstance();


    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @return
     */
    public Map login(String name, String pwd) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> users = mUserMapper.selectByExample(example);
        if (users == null || users.size() == 0)
            return ZHResponse.failed("该用户不存在，请确认信息！");
        User u = users.get(0);
        User user = mUserMapper.checkUserPwd(u.getId(), pwd);
        if (user == null)
            return ZHResponse.failed("账户名或密码错误，请重试！");

        //每次登录直接覆盖之前的token
        UserToken token = new UserToken(user.getId(), user.getName());
        String tokenStr = mUserTokenManager.createToken(RedisManager.KEY_TYPE_USER_TOKEN_ID, user.getId().toString(), token, 2, TimeUnit.HOURS);    //用户登录状态保持 2h
        UserInfo info = new UserInfo(user.getId(), user.getNum(), user.getPhone(), user.getName(), user.getNickname(), user.getType(), tokenStr);
        info.setResult("登录成功！");
        return ZHResponse.success(info);
    }


    /**
     * 注册
     *
     * @param name     必须
     * @param pwd      必须
     * @param phone
     * @param iconUrl
     * @param nickName
     * @param remarks
     * @return
     */
    public Map register(String name, String pwd, String phone, String iconUrl, String nickName, String remarks) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> users = mUserMapper.selectByExample(example);
        if (users != null && users.size() > 0)
            return ZHResponse.failed("该用户名已存在，请更换用户名！");
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);
        user.setPhone(phone);
        user.setIconurl(iconUrl);
        user.setNickname(nickName);
        user.setRemarks(remarks);
        user.setNum("用户" + new Date().getTime());
        int ok = mUserMapper.register(user);
        if (ok != 1) {
            return ZHResponse.failed("注册操作失败，请重试！");
        }
        return ZHResponse.success("注册成功！");
    }


}

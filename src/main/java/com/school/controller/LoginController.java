package com.school.controller;

import com.school.pojo.SchoolRole;
import com.school.pojo.SchoolUser;
import com.school.response.WebResponse;
import com.school.service.LoginService;
import com.school.util.PageListUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author mjz
 * @Date 2022/3/23 14:21
 * @Version 1.0
 */
//@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * 自动生成用户id
     * @return
     */
    @PostMapping("/getUserId")
    public WebResponse getUserId(){
        return loginService.getUserId();
    }

    /**
     * 查询用户角色
     * @return
     */
    @PostMapping("/getUserRole")
    public WebResponse getUserRole(){
        List<SchoolRole> schoolRoleList = loginService.getUserRole();
        if(CollectionUtils.isEmpty(schoolRoleList)){
            return WebResponse.error(-1,"未查到用户角色");
        }
        return WebResponse.success(schoolRoleList);
    }
    /**
     * 添加用户
     * @param schoolUser
     * @return
     */
    @PostMapping("/addUser")
    public WebResponse addUser(@RequestBody SchoolUser schoolUser){
        return loginService.addUser(schoolUser);
    }

    /**
     * 修改用户信息
     * @param schoolUser
     * @return
     */
    @PostMapping("/updateUser")
    public WebResponse updateUser(@RequestBody SchoolUser schoolUser){
        return loginService.updataUser(schoolUser);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping("/delUser")
    public WebResponse delUser(@RequestBody Map<String,String> map){
        String id=map.get("id");
        String userRole=map.get("userRole");
        if("1".equals(userRole)){
            int i = loginService.delUser(id);
            if(i<1){
                return WebResponse.error(-1,"删除用户失败");
            }
            return WebResponse.success();
        }
        return WebResponse.error(-1,"您没有权限删除");
    }

    /**
     * 分页查询用户信息
     * @param userClass
     * @param userRole
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @PostMapping("/getUserList")
    public WebResponse getUserList(@RequestBody Map<String,Object> map){
        String userClass=map.get("userClass").toString();
        String userRole=map.get("userRole").toString();
        String userId=map.get("userId").toString();
        Integer pageIndex=(int)map.get("pageIndex");
        Integer pageSize=(int)map.get("pageSize");
        PageListUtil<SchoolUser> userList = loginService.getUserList(userClass, userRole,userId, pageIndex, pageSize);
        if(ObjectUtils.isEmpty(userList)){
            return WebResponse.error(-1,"未查询出用户信息");
        }
        return WebResponse.success(userList);
    }

    /**
     * 登录
     * @param userId
     * @param userPwd
     * @return
     */
    @PostMapping("/login")
    public WebResponse login(@RequestBody Map<String,String> map){
        String userId=map.get("userId");
        String userPwd=map.get("userPwd");
        return loginService.login(userId, userPwd);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPwd
     * @return
     */
    @PostMapping("/updatePwd")
    public WebResponse updatePwd(@RequestBody Map<String,String> map){
        String userId=map.get("userId");
        String newPwd=map.get("newPwd");
        return loginService.updatePwd(userId,newPwd);
    }

    /**
     * 获取单条用户信息
     * @param userId
     * @return
     */
    @PostMapping("/getUser")
    public WebResponse getUser(@RequestBody Map<String,String> map){
        String userId=map.get("userId");
        return loginService.getUser(userId);
    }
}

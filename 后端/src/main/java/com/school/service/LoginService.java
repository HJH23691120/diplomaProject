package com.school.service;


import com.school.pojo.SchoolRole;
import com.school.pojo.SchoolUser;
import com.school.response.WebResponse;
import com.school.util.PageListUtil;

import java.util.List;

/**
 * @Author mjz
 * @Date 2022/3/23 14:22
 * @Version 1.0
 */
public interface LoginService {

    WebResponse getUserId();

    List<SchoolRole> getUserRole();

    WebResponse addUser(SchoolUser schoolUser);

    WebResponse updataUser(SchoolUser schoolUser);

    int delUser(String id);

    PageListUtil<SchoolUser> getUserList(String userClass, String userRole,String userId, Integer pageIndex, Integer pageSize);

    WebResponse login(String userId, String userPwd);

    WebResponse updatePwd(String userId,String newPwd);

    WebResponse getUser(String userId);
}

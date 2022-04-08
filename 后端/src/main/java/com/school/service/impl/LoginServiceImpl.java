package com.school.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.school.mapper.SchoolRoleMapper;
import com.school.mapper.SchoolUserMapper;
import com.school.pojo.SchoolRole;
import com.school.pojo.SchoolUser;
import com.school.response.WebResponse;
import com.school.service.LoginService;
import com.school.util.PageListUtil;
import com.school.util.UidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Author mjz
 * @Date 2022/3/23 14:24
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    SchoolRoleMapper schoolRoleMapper;
    @Autowired
    SchoolUserMapper schoolUserMapper;

    static int num=0;

    @Override
    public WebResponse getUserId() {
        String usetId = UidUtil.genCodes("s_",6);
        SchoolUser schoolUser = schoolUserMapper.selectOne(usetId);
        if(ObjectUtils.isEmpty(schoolUser)){
            return WebResponse.success(usetId);
        }else{
            for(;num<5;){
                num++;
                this.getUserId();
            }
            return WebResponse.error(-1,"多次申请新的用户Id失败，请联系管理员删除存量数据");
        }
    }

    @Override
    public List<SchoolRole> getUserRole() {
        List<SchoolRole> schoolRoleList = schoolRoleMapper.selectAll();
        return schoolRoleList;
    }

    @Override
    public WebResponse addUser(SchoolUser schoolUser) {
        if(StringUtils.isEmpty(schoolUser.getId())) {
            schoolUser.setId(UidUtil.genCodes("",15));
            schoolUser.setCreatDate(new Date());
            schoolUser.setUpdateDate(new Date());
        }
        int insert = schoolUserMapper.insert(schoolUser);
        if(insert<1){
            return WebResponse.error(-1,"添加用户失败");
        }
        SchoolUser schoolUser1 = schoolUserMapper.selectByPrimaryKey(schoolUser.getId());
        return WebResponse.success(schoolUser1);
    }

    @Override
    public WebResponse updataUser(SchoolUser schoolUser) {
        schoolUser.setUpdateDate(new Date());
        int i = schoolUserMapper.updateByPrimaryKey(schoolUser);
        if(i<1){
            return WebResponse.error(-1,"修改用户失败");
        }
        return WebResponse.success(schoolUser);
    }

    @Override
    public int delUser(String id) {
        int i = schoolUserMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public PageListUtil<SchoolUser> getUserList(String userClass, String userRole,String userId, Integer pageIndex, Integer pageSize) {
        if(pageIndex==null||pageIndex<1){
            pageIndex=1;
        }
        if(pageSize==null||pageSize<1){
            pageSize=10;
        }
        PageListUtil<SchoolUser> pageListUtil = new PageListUtil<>(pageIndex, pageSize);
        List<SchoolUser> schoolUserList = new ArrayList<>(pageSize);

        PageHelper.startPage(pageIndex,pageSize);
        Page<SchoolUser> schoolUsers=new Page<SchoolUser>();
        //校内老师查询
        if(!"admin".equals(userClass)&&!StringUtils.isEmpty(userRole)&&"admin".equals(userId)) {
            schoolUserList= schoolUserMapper.selectAll(userClass, userRole,"");
        }
        //企业导师查询
        if(!"admin".equals(userId)&&!StringUtils.isEmpty(userRole)&&"admin".equals(userClass)){
            SchoolUser schoolUser = schoolUserMapper.selectOne(userId);
            schoolUserList= schoolUserMapper.selectListByFirmTutor(userRole,schoolUser.getUserName());
        }
        //管理员查询
        if(!"admin".equals(userId)&&!"admin".equals(userClass)){
            schoolUserList= schoolUserMapper.selectAll(userClass, userRole,"");
        }


        pageListUtil.setList(schoolUserList);
        pageListUtil.setTotalSize(schoolUsers.getTotal());
        pageListUtil.setPageIndex(schoolUsers.getPageNum());
        pageListUtil.setPageSize(schoolUsers.getPageSize());
        pageListUtil.setTotalPage(schoolUsers.getPages());
        return pageListUtil;
    }

    @Override
    public WebResponse login(String userId, String userPwd) {
        SchoolUser schoolUser = schoolUserMapper.selectOne(userId);
        if(ObjectUtils.isEmpty(schoolUser)){
            return WebResponse.error(-1,"用户名输入有误，请重新输入");
        }
        if(!userPwd.equals(schoolUser.getUserPwd())){
            return WebResponse.error(-1,"密码输入有误，请重新输入");
        }
        return WebResponse.success(schoolUser);
    }

    @Override
    public WebResponse updatePwd(String userId, String newPwd) {
        SchoolUser schoolUser = schoolUserMapper.selectOne(userId);
        if(ObjectUtils.isEmpty(schoolUser.getUserPwd())){
            int i = schoolUserMapper.updatePwd(userId, newPwd,new Date());
            if(i>0){
                SchoolUser schoolUser1 = schoolUserMapper.selectByPrimaryKey(schoolUser.getId());
                return WebResponse.success(schoolUser1);
            }
        }
        return WebResponse.error(-1,"用户id输入有误");
    }

    @Override
    public WebResponse getUser(String userId) {
        SchoolUser schoolUser = schoolUserMapper.selectOne(userId);
        if(ObjectUtils.isEmpty(schoolUser)){
            return WebResponse.error(-1,"用户id输入有误");
        }
        return WebResponse.success(schoolUser);
    }

}

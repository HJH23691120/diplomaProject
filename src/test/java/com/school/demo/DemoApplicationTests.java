package com.school.demo;

import cn.hutool.core.io.FileUtil;
import com.school.Application;
import com.school.mapper.SchoolRoleMapper;
import com.school.mapper.SchoolUserMapper;
import com.school.pojo.SchoolRole;

import com.school.pojo.SchoolUser;
import com.school.util.UidUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.util.List;

@SpringBootTest(classes = Application.class)
public class DemoApplicationTests extends HttpServlet {
    @Autowired
    SchoolRoleMapper schoolRoleMapper;

    @Autowired
    SchoolUserMapper schoolUserMapper;

    static int num=0;

    @Test
    public void contextLoads() {
        List<SchoolRole> schoolRoles = schoolRoleMapper.selectAll();
        schoolRoles.forEach(x->{
            System.out.println(x);
        });

    }
    @Test
    public void testUsetId(){
        String usetId = UidUtil.genCodes("s_",6);
        SchoolUser schoolUser = schoolUserMapper.selectOne(usetId);
        if(ObjectUtils.isEmpty(schoolUser)){
            for(;num<5;){
                num++;
                System.out.println(usetId);
                this.testUsetId();
            }
            System.out.println(num);
        }
    }

    @Test
    public void testPath(){
//        boolean directory = FileUtil.isDirectory(System.getProperty("user.dir") + "/src/main/resources/files/test");
//        System.out.println(directory);
        String basePath = System.getProperty("user.dir") + "/src/main/resources/files/"; //定义文件上传的根路径
        List<String> fileNames = FileUtil.listFileNames(basePath);
        fileNames.forEach(x->{
            System.out.println(x);
        });
    }
}

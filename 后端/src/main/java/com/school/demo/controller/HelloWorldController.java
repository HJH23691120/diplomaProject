package com.school.demo.controller;

import com.school.mapper.SchoolRoleMapper;
import com.school.pojo.SchoolRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {
    @Autowired
    SchoolRoleMapper schoolRoleMapper;
    @RequestMapping("/index")
    public String sayHello(){
        List<SchoolRole> schoolRoles = schoolRoleMapper.selectAll();
        schoolRoles.forEach(x->{
            System.out.println(x);
        });
        return "index";
    }
}

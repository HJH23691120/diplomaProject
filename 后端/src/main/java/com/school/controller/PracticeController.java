package com.school.controller;

import com.school.pojo.SchoolPracticeApply;
import com.school.response.WebResponse;
import com.school.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author mjz
 * @Date 2022/3/23 18:48
 * @Version 1.0
 */
@RestController
public class PracticeController {

    @Autowired
    PracticeService practiceService;

    /**
     * 提交实习申请
     * @param schoolPracticeApply
     * @return
     */
    @RequestMapping("/applyPractice")
    public WebResponse applyPractice(@RequestBody SchoolPracticeApply schoolPracticeApply){
        return practiceService.applyPractice(schoolPracticeApply);
    }

    /**
     * 模糊查询企业导师名称
     * @param userName
     * @return
     */
    @RequestMapping("/getFirmTutro")
    public WebResponse getFirmTutro(@RequestBody Map<String,String> map){
        String userName=map.get("userName");
        List<Map<String, String>> firmTutro = practiceService.getFirmTutro(userName);
        if(CollectionUtils.isEmpty(firmTutro)){
            return WebResponse.error(-1,"未查询到企业导师");
        }
        return WebResponse.success(firmTutro);
    }

    /**
     * 查询实习申请
     * @param userId
     * @return
     */
    @RequestMapping("/getPracticeApply")
    public WebResponse getPracticeApply(@RequestBody Map<String,String> map){
        String userId=map.get("userId");
        SchoolPracticeApply practiceApply = practiceService.getPracticeApply(userId);
        if(ObjectUtils.isEmpty(practiceApply)){
            return WebResponse.error(-1,"未查询出实习申请");
        }
        return WebResponse.success(practiceApply);
    }

    /**
     * 修改实习申请
     * @param schoolPracticeApply
     * @return
     */
    @RequestMapping("/updatePracticeApply")
    public WebResponse updatePracticeApply(@RequestBody SchoolPracticeApply schoolPracticeApply){
        return practiceService.updatePracticeApply(schoolPracticeApply);
    }
}

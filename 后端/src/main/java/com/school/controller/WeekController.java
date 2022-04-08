package com.school.controller;

import com.school.pojo.SchoolWeekReport;
import com.school.response.WebResponse;
import com.school.service.WeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author mjz
 * @Date 2022/3/27 0:46
 * @Version 1.0
 */
@RestController
public class WeekController {
    @Autowired
    WeekService weekService;

    /**
     * 添加周记
     * @param schoolWeekReport
     * @return
     */
    @RequestMapping("/addWeek")
    public WebResponse addWeek(@RequestBody SchoolWeekReport schoolWeekReport){
        return weekService.addWeek(schoolWeekReport);
    }

    /**
     *
     * 修改周记/周记审核
     * @param schoolWeekReport
     * @return
     */
    @RequestMapping("/updateWeek")
    public WebResponse updateWeek(@RequestBody SchoolWeekReport schoolWeekReport){
        return weekService.updateWeek(schoolWeekReport);
    }

    /**
     * 查询周记审核并 转换审核老师id为名称
     * @param userId
     * @param weekNum
     * @return
     */
    @RequestMapping("/getWeek")
    public WebResponse getWeek(@RequestBody Map<String,String> map){
        String userId=map.get("userId");
        String weekNum=map.get("practiceWeek");
        SchoolWeekReport week = weekService.getWeek(userId, weekNum);
        if(ObjectUtils.isEmpty(week)){
            return WebResponse.error(-1,"第"+weekNum+"周的周记为空，请添加");
        }
        return WebResponse.success(week);
    }

    /**
     * 查询实习周记条数
     * @param usetId
     * @return
     */
    @RequestMapping("/getWeekNum")
    public WebResponse getWeekNum(@RequestBody Map<String,String> map){
        String userId=map.get("userId");
        return weekService.getWeekNum(userId);
    }
}

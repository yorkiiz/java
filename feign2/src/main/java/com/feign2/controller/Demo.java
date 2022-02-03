package com.feign2.controller;

import com.feign2.Entity.TimeVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class Demo {

    @RequestMapping("/time")
    public TimeVO gettime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月,dd天hh时mm分ss秒");
        String date = sdf.format(new Date());
        TimeVO timeVO = TimeVO.builder()
                .date(date)
                .result(true)
                .build();
        System.out.println("the controller /demo/time success");
        return timeVO;
    }

    @RequestMapping("/timeh5")
    public String timeh5(){
        return "time";
    }
}

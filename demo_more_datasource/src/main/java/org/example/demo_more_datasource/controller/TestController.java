package org.example.demo_more_datasource.controller;

import org.example.demo_more_datasource.entiy.UcRecord;
import org.example.demo_more_datasource.entiy.User;
import org.example.demo_more_datasource.service.MixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * *@ClassName sfs
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/22 21:57
 * *Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private MixedService mixedService;

    @PostMapping("/test")
    public String test() {
        User user = new User();
        user.setName("Alice");

        UcRecord record = new UcRecord();
        record.setId(1L);
        record.setValue("Updated");

        mixedService.insertAndUpdate(user, record);
        return "ok";
    }
}


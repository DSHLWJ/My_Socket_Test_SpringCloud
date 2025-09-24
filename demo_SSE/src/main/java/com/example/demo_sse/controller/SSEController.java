package com.example.demo_sse.controller;

/**
 * *@ClassName fsfs
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/24 15:11
 * *Version 1.0
 **/

import com.example.demo_sse.service.SseService;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.CompletableFuture;

//原文链接：https://blog.csdn.net/lzping_719/article/details/149503779



@RestController
@RequestMapping("/sse")
@Slf4j
@Validated
@CrossOrigin(origins = "http://localhost:5173") // 允许前端的域名
public class SSEController {

    @Autowired
    private SseService service;


    @GetMapping("/testSse")
    public SseEmitter testSse(@RequestParam("clientId") @NotBlank(message = "客户端id不能为空") String clientId) {
        final SseEmitter emitter = service.getConn(clientId);
        CompletableFuture.runAsync(() -> {
            try {
                log.info("建立连接成功！clientId = {}", clientId);
                service.send(clientId);
            } catch (Exception e) {
                log.error("推送数据异常");
            }
        });
        return emitter;
    }


    @GetMapping("/sseConection")
    public SseEmitter createConnection(@RequestParam("clientId") @NotBlank(message = "客户端id不能为空") String clientId) {
        return service.getConn(clientId);
    }

    @GetMapping("/sendMsg")
    public void sendMsg(@RequestParam("clientId") String clientId) {
        try {
            // 异步发送消息
            CompletableFuture.runAsync(() -> {
                try {
                    service.send(clientId);
                } catch (Exception e) {
                    log.error("推送数据异常");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/sendMsgToAll")
    public void sendMsgToAll() {
        try {
            //异步发送消息
            CompletableFuture.runAsync(() -> {
                try {
                    service.sendToAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("closeConn/{clientId}")
    public String closeConn(@PathVariable("clientId") @NotBlank(message = "客户端id不能为空") String clientId) {
        service.closeConn(clientId);
        return "连接已关闭";
    }
}
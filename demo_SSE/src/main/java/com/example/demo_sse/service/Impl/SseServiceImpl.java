package com.example.demo_sse.service.Impl;

import com.example.demo_sse.service.SseService;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * *@ClassName fsf
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/24 16:17
 * *Version 1.0
 **/
@Service
@Slf4j
public class SseServiceImpl implements SseService {

    private static final Map<String, SseEmitter> SSE_CACHE = new ConcurrentHashMap<>();

    @Override
    public SseEmitter getConn(@NotBlank String clientId) {

        final SseEmitter sseEmitter = SSE_CACHE.get(clientId);

        if (sseEmitter != null) {
            return sseEmitter;
        } else {
            // 设置连接超时时间，需要配合配置项 spring.mvc.async.request-timeout: 600000 一起使用
            final SseEmitter emitter = new SseEmitter(600_000L);
            // 注册超时回调，超时后触发
            emitter.onTimeout(() -> {
                log.info("连接已超时，正准备关闭，clientId = {}", clientId);
                SSE_CACHE.remove(clientId);
            });
            // 注册完成回调，调用 emitter.complete() 触发
            emitter.onCompletion(() -> {
                log.info("连接已关闭，正准备释放，clientId = {}", clientId);
                SSE_CACHE.remove(clientId);
                log.info("连接已释放，clientId = {}", clientId);
            });
            // 注册异常回调，调用 emitter.completeWithError() 触发
            emitter.onError(throwable -> {
                log.error("连接已异常，正准备关闭，clientId = {}", clientId, throwable);
                SSE_CACHE.remove(clientId);
            });

            SSE_CACHE.put(clientId, emitter);
            log.info("建立连接成功！clientId = {}", clientId);
            return emitter;
        }
    }

    /**
     * 模拟类似于 chatGPT 的流式推送回答
     *
     * @param clientId 客户端 id
     */
    @Override
    public void send(@NotBlank String clientId) {
        final SseEmitter emitter = SSE_CACHE.get(clientId);
        if (emitter == null) return;
        // 开始推送数据
        // todo 模拟推送数据
        for (int i = 0; i < 100; i++) {
            // 模拟大屏数据，包含时间和随机生成的数值
            long timestamp = System.currentTimeMillis();
            double value = Math.random() * 100;  // 随机生成一个数值

            String msg = "{\"time\": " + timestamp + ", \"value\": " + value + "}";

            try {
                this.sseSend(emitter, msg, clientId);  // 使用 SSE 推送数据
                Thread.sleep(2000);  // 每1000ms推送一次
            } catch (Exception e) {
                log.error("推送数据异常", e);
                break;
            }
        }
        log.info("推送数据结束，clientId = {}", clientId);
        // 结束推流
        emitter.complete();
    }

    /**
     * 发送数据给所有连接
     */
    public void sendToAll() {
        List<SseEmitter> emitters = new ArrayList<>(SSE_CACHE.values());
        for (int i = 0; i < 100; i++) {
            String msg = "SSE 测试数据";
            this.sseSend(emitters, msg);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeConn(@NotBlank String clientId) {
        final SseEmitter sseEmitter = SSE_CACHE.get(clientId);
        if (sseEmitter != null) {
            sseEmitter.complete();
        }
    }

    /**
     * 推送数据封装
     * @param emitter  sse长连接
     * @param data     发送数据
     * @param clientId 客户端id
     */
    private void sseSend(SseEmitter emitter, Object data, String clientId) {
        try {
            emitter.send(data);
            log.info("推送数据成功，clientId = {}", clientId);
        } catch (Exception e) {
            log.error("推送数据异常", e);
            throw new RuntimeException("推送数据异常");
        }
    }

    /**
     * 推送数据封装
     *
     * @param emitter sse长连接
     * @param data    发送数据
     */
    private void sseSend(List<SseEmitter> emitter, Object data) {

        emitter.forEach(e -> {
            try {
                e.send(data);
            } catch (IOException ioException) {
                log.error("推送数据异常", ioException);
            }
        });
        log.info("推送数据成功");
    }

}
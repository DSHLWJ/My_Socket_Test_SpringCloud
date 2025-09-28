package org.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.demo.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * *@ClassName AuthController
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/28 19:47
 * *Version 1.0
 **/
@RestController
@Tag(name = "认证接口")
public class AuthController {

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username,
                                     @RequestParam String password) {
        if ("admin".equals(username) && "123456".equals(password)) {
            String token = JwtUtil.generateToken(username);
            // swagger自带Bearer 后缀
            //return Map.of("token","Bearer "+token);
            return Map.of("token",token);
        }
        return Map.of("error", "用户名或密码错误");
    }
}

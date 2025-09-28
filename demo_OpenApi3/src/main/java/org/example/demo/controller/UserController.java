package org.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.demo.dao.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * *@ClassName UserController
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/28 19:21
 * *Version 1.0
 **/
@RestController
@RequestMapping("/users")
@Tag(name = "用户接口", description = "用户相关操作")
public class UserController {

    // 内存存储用户（模拟数据库）
    private final Map<Long, User> users = new HashMap<>();

    /**
     * security = @SecurityRequirement(name = "bearerAuth")
     * 要带上这个swaggerUI 页面测试请求的时候才会自动带上Authorization 请求头
     * **/
    @GetMapping
    @Operation(summary = "获取用户列表", description = "返回所有用户", security = @SecurityRequirement(name = "bearerAuth"))
    public Collection<User> listUsers() {
        return users.values();
    }


    /**
     * | 注解             | 作用      | 位置   | 示例用途                        |
     * | -------------- | ------- | ---- | --------------------------- |
     * | `@Tag`         | 分组/分类接口 | 类    | “用户管理”、“订单管理”               |
     * | `@Operation`   | 描述接口操作  | 方法   | 摘要、详细说明、认证、安全、返回信息          |
     * | `@Parameter`   | 描述请求参数  | 方法参数 | query、path、header、body 参数说明 |
     * | `@Schema`      | 描述数据模型  | 类/字段 | 字段说明、示例值、类型                 |
     * | `@ApiResponse` | 描述接口返回  | 方法   | HTTP 状态码、返回说明、返回体结构         |
     *
     * **/
    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取用户", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "找到用户",

            content = @Content(schema = @Schema(implementation = User.class)))
    public User getUser(
            @Parameter(description = "用户 ID", required = true, example = "1001")
            @PathVariable Long id
    ) {
        return users.getOrDefault(id, new User(id, "未知", "none@example.com"));
    }

    @PostMapping
    @Operation(summary = "新增用户", security = @SecurityRequirement(name = "bearerAuth"))
    public User createUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return user;
    }

    /**
     * @SecurityRequirement(name="bearerAuth") 放在接口上，告诉 Swagger UI 这个接口需要 token
     * **/
    @GetMapping("/profile")
    @Operation(summary = "获取用户资料", security = @SecurityRequirement(name = "bearerAuth"))
    public String profile() {
        return "这是受保护的用户资料";
    }



}
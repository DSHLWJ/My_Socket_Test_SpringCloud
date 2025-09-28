package org.example.demo.dao;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * *@ClassName User
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/28 19:24
 * *Version 1.0
 **/
public class User {
    @Schema(description = "用户ID", example = "1")
    private Long id;

    @Schema(description = "用户名", example = "zhangsan")
    private String name;

    @Schema(description = "邮箱", example = "test@example.com")
    private String email;

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

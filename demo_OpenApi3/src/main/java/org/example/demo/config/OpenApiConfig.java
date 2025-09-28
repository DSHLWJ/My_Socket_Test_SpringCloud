package org.example.demo.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * *@ClassName OpenApiConfig
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/28 19:50
 * *Version 1.0
 **/

/**
 * @SecurityScheme 这是 springdoc-openapi / OpenAPI 3 的注解，用于在生成的 API 文档（Swagger UI / OpenAPI JSON） 中定义一个安全认证方案。
 *
 * name	认证方案名称，后面接口里引用	"bearerAuth"
 * type	认证类型	SecuritySchemeType.HTTP → HTTP 基本认证/Token
 * scheme	认证方案	"bearer" → Bearer token
 * bearerFormat	token 格式说明（可选）	"JWT" → JWT Token
 * **/

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {}



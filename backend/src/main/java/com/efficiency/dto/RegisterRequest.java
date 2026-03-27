package com.efficiency.dto;

import javax.validation.constraints.NotBlank;

/**
 * RegisterRequest 这个类是用于封装前端用户注册时提交的数据。
 * 常见于用户注册功能的接口参数，比如“注册账号”。
 * 
 * 字段说明：
 * - username：账号，不能为空
 * - password：密码，不能为空
 * - realName：真实姓名，不能为空
 * 
 * 加了 @NotBlank 注解的字段，表示这些字段在注册时是前端必须要传的，不能为空（否则返回相应的错误信息）。
 * 
 * 实际用法通常是在 Controller 层用 RegisterRequest 作为注册接口的参数，
 * 框架会自动绑定用户填写的数据到这个类的字段上，并自动校验必填项。
 */
public class RegisterRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "姓名不能为空")
    private String realName;
    private String phone;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}

package com.efficiency.security;

/**
 * UserContext用于存储和获取当前线程的登录用户信息，实现的是“线程隔离”，即每个请求各自保存自己的用户信息，互不干扰。
 * 一般在拦截器（比如JwtAuthInterceptor）中解析完JWT后，将用户信息存入UserContext。
 * 在后续的Controller或Service层，可以通过UserContext.get()获取当前登录用户的信息，无需多次解析Token。
 * 使用ThreadLocal实现，每个线程独立存储，避免并发请求数据混淆。
 * 这是业务开发常用的通用工具类，需理解其用途，并非由框架自动生成。
 */
public class UserContext {
    // 基于ThreadLocal存放用户信息，实现线程安全的用户会话管理
    private static final ThreadLocal<LoginUser> HOLDER = new ThreadLocal<>();

    // 设置当前线程的用户信息
    public static void set(LoginUser loginUser) { HOLDER.set(loginUser); }
    // 获取当前线程的用户信息
    public static LoginUser get() { return HOLDER.get(); }
    // 清除当前线程的用户信息，避免内存泄漏
    public static void clear() { HOLDER.remove(); }
}

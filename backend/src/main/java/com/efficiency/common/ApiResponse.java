package com.efficiency.common;

public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public ApiResponse() {
    }

    //200 = 成功。
    //
    //401 = 没登录/Token过期。
    //
    //500 = 后端代码出 Bug 了。
    public ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(200, "success", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<T>(200, message, data);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<T>(500, message, null);
    }

    public static <T> ApiResponse<T> unauthorized(String message) {
        return new ApiResponse<T>(401, message, null);
    }

    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}

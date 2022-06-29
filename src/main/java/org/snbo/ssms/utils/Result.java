package org.snbo.ssms.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunbo
 * @date 2022-06-08-12:38
 */
public class Result {
    private Boolean success;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private Result() {
    }

    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("成功");
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage("失败");
        return result;
    }

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Result setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Result setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

package com.itheima.entity;

import com.itheima.pojo.Brand;
import lombok.Data;

/**
 * @Description:
 * @Version: V1.0
 */
@Data
public class Result {

    private boolean success = true;

    private String message;

    private Integer code = 10000;

    public Result(boolean success, String message, Integer code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public Result(Brand brand) {
        this.success = true;
        this.message = "执行成功";
        this.code = 10000;
    }


}

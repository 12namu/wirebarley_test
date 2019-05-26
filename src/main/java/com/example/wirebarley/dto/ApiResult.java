package com.example.wirebarley.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult<T> {

    final public static int RESULT_CODE_SUCC = 200;
    final public static int RESULT_CODE_NOT_FOUND = 404;
    final public static int RESULT_CODE_ERROR = 500;

    private int code;
    private T data;

    public ApiResult(int code) {
        this.code = code;
    }

}

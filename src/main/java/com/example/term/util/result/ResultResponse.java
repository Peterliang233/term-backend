package com.example.term.util.result;

import com.example.term.util.code.StatusCode;
import com.example.term.util.response.Response;


public class ResultResponse {

    private static final  String DEFAULT = "Success";


    // 返回成功的状态
    public static Response getSuccessResult(Object data) {
        return new Response()
                .setCode(StatusCode.SUCCESS)
                .setMessage(StatusCode.SUCCESS)
                .setData(data);
    }

    // 返回失败的状态
    public static Response getFailResult(Object data) {
        return new Response()
                .setCode(StatusCode.FAIL)
                .setMessage(StatusCode.FAIL)
                .setData(data);
    }
}

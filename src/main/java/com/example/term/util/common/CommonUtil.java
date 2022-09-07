package com.example.term.util.common;


import com.example.term.config.common.ConstCode;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseType;

public class CommonUtil {
    public static Integer objToInteger(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        }

        throw new ResponseException(ResponseType.ERR_TOKEN);
    }

    public static String objToString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }

        throw new ResponseException(ResponseType.ERR_TOKEN);
    }
}

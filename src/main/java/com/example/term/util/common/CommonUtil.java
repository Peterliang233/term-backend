package com.example.term.util.common;


import com.example.term.config.common.ConstCode;

public class CommonUtil {
    public static Integer objToInteger(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        }

        return Integer.valueOf("1");
    }
}

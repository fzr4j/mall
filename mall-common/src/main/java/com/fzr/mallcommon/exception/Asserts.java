package com.fzr.mallcommon.exception;

import com.fzr.mallcommon.api.IErrorCode;

/**
 * 断言处理类，用于处理各种API异常
 */
public class Asserts {
    public static void fail(String message){
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode){
        throw new ApiException(errorCode);
    }
}

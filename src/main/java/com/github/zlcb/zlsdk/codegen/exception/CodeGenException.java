package com.github.zlcb.zlsdk.codegen.exception;

/**
 * @author Zhongl
 * @date 2020/05/22 14:57
 */
public class CodeGenException extends RuntimeException {

    public CodeGenException(String msg) {
        super(msg);
    }

    public CodeGenException(String message, Throwable cause) {
        super(message, cause);
    }
}

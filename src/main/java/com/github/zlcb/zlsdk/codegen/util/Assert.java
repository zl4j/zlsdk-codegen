package com.github.zlcb.zlsdk.codegen.util;

import com.github.zlcb.zlsdk.codegen.exception.CodeGenException;

/**
 * @author Zhongl
 * @date 2020/05/22 14:59
 */
public final class Assert {

    public static void isNotNull(Object obj, String msg) throws CodeGenException {
        if (obj == null) {
            throw new CodeGenException(msg);
        }
    }

    public static void isNotEmpty(String str, String msg) throws CodeGenException {
        if (str == null || str.trim().length() == 0) {
            throw new CodeGenException(msg);
        }
    }

}

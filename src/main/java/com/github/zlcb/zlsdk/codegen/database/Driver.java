package com.github.zlcb.zlsdk.codegen.database;

/**
 * @author Zhongl
 * @date 2020/05/21 16:22
 */
public enum Driver {
    MySql("com.mysql.cj.jdbc.Driver");

    public final String code;

    private Driver(String code) {
        this.code = code;
    }
}

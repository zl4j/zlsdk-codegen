package com.github.zlcb.zlsdk.codegen.database;

/**
 * @author Zhongl
 * @date 2020/06/02 00:15
 */
public interface DataType {

    String getJdbcType();

    String getJavaType();

    String getName();

}

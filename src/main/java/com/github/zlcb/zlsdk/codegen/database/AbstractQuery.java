package com.github.zlcb.zlsdk.codegen.database;

import com.github.zlcb.zlsdk.codegen.config.DataSourceConfig;
import com.github.zlcb.zlsdk.codegen.exception.CodeGenException;
import com.github.zlcb.zlsdk.codegen.util.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Zhongl
 * @date 2020/05/22 18:44
 */
public abstract class AbstractQuery implements Query {

    protected Connection getConnection(DataSourceConfig ds) throws CodeGenException,
            SQLException,
            ClassNotFoundException {
        Assert.isNotEmpty(ds.getUrl(), "dataSource.url 必须指定！且不能为空字符串！");
        Assert.isNotEmpty(ds.getUsername(), "dataSource.username 必须指定！且不能为空字符串！");

        Class.forName(ds.getDriverName());
        return DriverManager.getConnection(ds.getUrl(), ds.getUsername(), (ds.getPassword() == null ? "" : ds.getPassword()));
    }

}

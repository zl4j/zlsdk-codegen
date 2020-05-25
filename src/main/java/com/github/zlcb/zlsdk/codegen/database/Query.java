package com.github.zlcb.zlsdk.codegen.database;

import com.github.zlcb.zlsdk.codegen.config.DataSourceConfig;
import com.github.zlcb.zlsdk.codegen.exception.CodeGenException;
import com.github.zlcb.zlsdk.codegen.model.Column;
import com.github.zlcb.zlsdk.codegen.model.Table;

import java.util.List;

/**
 * @author Zhongl
 * @date 2020/05/22 18:40
 */
public interface Query {

    List<Table> queryTables(DataSourceConfig ds) throws CodeGenException;

    List<Column> queryColumns(DataSourceConfig ds, String tableName) throws CodeGenException;

    Driver getSupportDriver();
}

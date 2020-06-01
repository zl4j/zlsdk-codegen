package com.github.zlcb.zlsdk.codegen.database;

import com.github.zlcb.zlsdk.codegen.config.DataSourceConfig;
import com.github.zlcb.zlsdk.codegen.exception.CodeGenException;
import com.github.zlcb.zlsdk.codegen.model.Column;
import com.github.zlcb.zlsdk.codegen.model.Table;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhongl
 * @date 2020/05/22 18:42
 */
public class MySqlQuery extends AbstractQuery {

    @Override
    public List<Table> queryTables(DataSourceConfig ds) throws CodeGenException {
        List<Table> tableList;
        String sql = "select table_name name, table_comment comment from information_schema.tables where table_schema" +
                " = (select database())";
        try (Connection conn = this.getConnection(ds);) {
            QueryRunner runner = new QueryRunner();
            tableList = runner.query(conn, sql, new BeanListHandler<Table>(Table.class));
        } catch (Exception e) {
            throw new CodeGenException("获取数据库表信息异常", e);
        }
        return tableList;
    }

    @Override
    public List<Column> queryColumns(DataSourceConfig ds, String tableName) throws CodeGenException {
        List<Column> columnList;
        String sql = "select column_name name, data_type dataType, column_comment comment, column_key " +
                "primaryKey, extra autoIncrement from information_schema.columns where table_name=? and table_schema " +
                "= (select database()) order by ordinal_position ";
        try (Connection conn = this.getConnection(ds);) {
            QueryRunner runner = new QueryRunner();
            columnList = runner.query(conn, sql, new ResultSetHandler<List<Column>>() {
                @Override
                public List<Column> handle(ResultSet rs) throws SQLException {
                    List<Column> list = new ArrayList<Column>();
                    Column col;
                    while (rs.next()) {
                        col = new Column();
                        col.setName(rs.getString("name"));
                        col.setComment(rs.getString("comment"));
                        col.setDataType(MySqlDataType.forName(rs.getString("dataType")));
                        col.setPrimaryKey("PRI".equalsIgnoreCase(rs.getString("primaryKey")));
                        col.setAutoIncrement("auto_increment".equalsIgnoreCase(rs.getString("autoIncrement")));
                        list.add(col);
                    }
                    return list;
                }
            }, tableName);
        } catch (Exception e) {
            throw new CodeGenException("获取数据库列信息异常", e);
        }
        return columnList;
    }

    @Override
    public Driver getSupportDriver() {
        return Driver.MySql;
    }
}

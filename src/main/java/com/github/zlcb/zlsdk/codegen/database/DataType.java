package com.github.zlcb.zlsdk.codegen.database;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Zhongl
 * @date 2020/05/23 01:14
 */
public enum DataType {
    BIT(JdbcType.BIT, Boolean.class.getName()),
    TINYINT(JdbcType.TINYINT, Integer.class.getName()),
    SMALLINT(JdbcType.SMALLINT, Integer.class.getName()),
    INT(JdbcType.INTEGER, Integer.class.getName()),
    INTEGER(JdbcType.INTEGER, Integer.class.getName()),
    BIGINT(JdbcType.BIGINT, Long.class.getName()),
    FLOAT(JdbcType.FLOAT, Float.class.getName()),
    DOUBLE(JdbcType.DOUBLE, Double.class.getName()),
    DECIMAL(JdbcType.DECIMAL, BigDecimal.class.getName()),
    DATE(JdbcType.DATE, Date.class.getName()),
    DATETIME(JdbcType.TIMESTAMP, Date.class.getName()),
    TIMESTAMP(JdbcType.TIMESTAMP, Date.class.getName()),
    TIME(JdbcType.TIME, Date.class.getName()),
    CHAR(JdbcType.CHAR, String.class.getName()),
    VARCHAR(JdbcType.VARCHAR, String.class.getName()),
    BINARY(JdbcType.BINARY, "byte[]"),
    VARBINARY(JdbcType.VARBINARY, "byte[]"),
    BLOB(JdbcType.BLOB, "byte[]"),
    OTHER(JdbcType.OTHER, Object.class.getName());

    private String javaType;
    private String jdbcType;

    DataType(JdbcType jdbcType, String javaType) {
        this.javaType = javaType;
        this.jdbcType = jdbcType.name();
    }

    public String getJavaType() {
        return javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public static DataType get(String name) {
        for (DataType dataType : DataType.values()) {
            if (dataType.name().equals(name.toUpperCase())) {
                return dataType;
            }
        }
        return OTHER;
    }
}

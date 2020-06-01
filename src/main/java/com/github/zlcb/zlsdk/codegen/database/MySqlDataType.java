package com.github.zlcb.zlsdk.codegen.database;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Zhongl
 * @date 2020/06/01 22:38
 */
public enum MySqlDataType implements DataType {
    BIT(JdbcType.BIT, Boolean.class),
    TINYINT(JdbcType.TINYINT, Byte.class),
    SMALLINT(JdbcType.SMALLINT, Short.class),
    MEDIUMINT(JdbcType.INTEGER, Integer.class),
    INT(JdbcType.INTEGER, Integer.class),
    INTEGER(JdbcType.INTEGER, Integer.class),
    BIGINT(JdbcType.BIGINT, Long.class),
    FLOAT(JdbcType.FLOAT, Float.class),
    DOUBLE(JdbcType.DOUBLE, Double.class),
    DECIMAL(JdbcType.DECIMAL, BigDecimal.class),

    DATE(JdbcType.DATE, LocalDate.class),
    DATETIME(JdbcType.TIMESTAMP, LocalDateTime.class),
    TIMESTAMP(JdbcType.TIMESTAMP, LocalTime.class),
    TIME(JdbcType.TIME, Time.class),
    YEAR(JdbcType.DATE, Date.class),

    CHAR(JdbcType.CHAR, String.class),
    VARCHAR(JdbcType.VARCHAR, String.class),

    BINARY(JdbcType.BINARY, "byte[]"),
    VARBINARY(JdbcType.VARBINARY, "byte[]"),

    TINYTEXT(JdbcType.VARCHAR, String.class),
    TEXT(JdbcType.LONGVARCHAR, String.class),
    MEDIUMTEXT(JdbcType.LONGVARCHAR, String.class),
    LONGTEXT(JdbcType.LONGVARCHAR, String.class),

    TINYBLOB(JdbcType.VARBINARY, "byte[]"),
    BLOB(JdbcType.BLOB, "byte[]"),
    MEDIUMBLOB(JdbcType.LONGVARBINARY, "byte[]"),
    LONGBLOB(JdbcType.LONGVARBINARY, "byte[]"),

    OTHER(JdbcType.OTHER, Object.class);

    private String javaType;
    private String jdbcType;

    MySqlDataType(JdbcType jdbcType, Class javaType) {
        this(jdbcType, javaType.getName());
    }

    MySqlDataType(JdbcType jdbcType, String javaType) {
        this.javaType = javaType;
        this.jdbcType = jdbcType.name();
    }

    public String getJavaType() {
        return javaType;
    }

    @Override
    public String getName() {
        return this.name();
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public static MySqlDataType forName(String name) {
        for (MySqlDataType dataType : MySqlDataType.values()) {
            if (dataType.name()
                    .equals(name.toUpperCase())) {
                return dataType;
            }
        }
        return OTHER;
    }
}

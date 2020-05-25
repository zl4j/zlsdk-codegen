package com.github.zlcb.zlsdk.codegen.model;

/**
 * @author Zhongl
 * @date 2020/05/22 22:18
 */
public class Column {

    public static final String NAME = "NAME";
    public static final String COMMENT = "COMMENT";
    public static final String DATA_TYPE = "DATA_TYPE";
    public static final String PRIMARY_KEY = "PRIMARY_KEY";
    public static final String AUTO_INCREMENT = "AUTO_INCREMENT";

    private String name;
    private String comment;
    private String dataType;
    private boolean primaryKey;
    private boolean autoIncrement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }
}

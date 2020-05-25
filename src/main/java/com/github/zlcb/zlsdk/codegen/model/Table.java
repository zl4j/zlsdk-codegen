package com.github.zlcb.zlsdk.codegen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhongl
 * @date 2020/05/22 22:12
 */
public class Table {

    public static final String NAME = "NAME";
    public static final String COMMENT = "COMMENT";

    private String name;
    private String comment;

    private String entityName;
    private String referenceName;

    private List<Column> columns = new ArrayList<>();

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

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}

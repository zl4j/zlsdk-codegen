package com.github.zlcb.zlsdk.codegen.util;

import com.github.zlcb.zlsdk.codegen.model.Column;
import com.github.zlcb.zlsdk.codegen.model.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * @author Zhongl
 * @date 2020/05/23 00:10
 */
public class ConsolePrinter {

    private static final List<String> LOG_BUFFER = new ArrayList<String>();

    public static void info(String msg, String...args) {
        System.out.println(String.format(msg, args));
    }

    public static void printToBuffer(String msg, String...args) {
        LOG_BUFFER.add(String.format(msg, args));
    }

    public static void flushPrint() {
        LOG_BUFFER.stream().forEach(msg -> {
            System.out.println(msg);
        });
        LOG_BUFFER.clear();
    }


    public static void printTables(List<Table> tables) {
        int nameMaxLen = 0, commentMaxLen = 0;
        for (Table t : tables) {
            nameMaxLen = Math.max(t.getName()
                    .length(), nameMaxLen);
            commentMaxLen = Math.max(t.getComment()
                    .length() * 2, commentMaxLen);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        int width = IntStream.of(nameMaxLen, commentMaxLen, 1).sum();
        sb.append(StringUtils.rightPad("", width, "="));
        sb.append("\n");
        //header
        sb.append(StringUtils.rightPad(Table.NAME, nameMaxLen));
        sb.append(' ');
        sb.append(StringUtils.rightPad(Table.COMMENT, commentMaxLen));
        sb.append("\n");
        //header分割线
        sb.append(StringUtils.rightPad("", nameMaxLen, "="));
        sb.append(' ');
        sb.append(StringUtils.rightPad("", commentMaxLen, "="));
        sb.append("\n");
        //body
        for (Table t : tables) {
            sb.append(StringUtils.rightPad(t.getName(), nameMaxLen));
            sb.append(' ');
            sb.append(StringUtils.rightPad(t.getComment(), commentMaxLen));
            sb.append("\n");
        }
        //footer
        sb.append(StringUtils.rightPad("", width, "="));
        sb.append(String.format("\nRows:%s", tables.size()));

        System.out.println(sb.toString());
    }

    public static void printColumns(List<Column> columns) {
        int nameMaxLen = 0, commentMaxLen = 0;
        for (Column c : columns) {
            nameMaxLen = Math.max(c.getName()
                    .length(), nameMaxLen);
            commentMaxLen = Math.max(c.getComment()
                    .length() * 2, commentMaxLen);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        int width = IntStream.of(nameMaxLen, Column.DATA_TYPE.length(), Column.PRIMARY_KEY.length(),
                Column.AUTO_INCREMENT.length(), commentMaxLen, 4).sum();
        sb.append(StringUtils.rightPad("", width, "="));
        sb.append("\n");
        //header
        sb.append(StringUtils.rightPad(Column.NAME, nameMaxLen));
        sb.append(' ');
        sb.append(Column.DATA_TYPE);
        sb.append(' ');
        sb.append(Column.PRIMARY_KEY);
        sb.append(' ');
        sb.append(Column.AUTO_INCREMENT);
        sb.append(' ');
        sb.append(StringUtils.rightPad(Column.COMMENT, commentMaxLen));
        sb.append("\n");
        //header分割线
        sb.append(StringUtils.rightPad("", nameMaxLen, "="));
        sb.append(' ');
        sb.append(StringUtils.rightPad("", Column.DATA_TYPE.length(), "="));
        sb.append(' ');
        sb.append(StringUtils.rightPad("", Column.PRIMARY_KEY.length(), "="));
        sb.append(' ');
        sb.append(StringUtils.rightPad("", Column.AUTO_INCREMENT.length(), "="));
        sb.append(' ');
        sb.append(StringUtils.rightPad("", commentMaxLen, "="));
        sb.append("\n");
        //body
        for (Column c : columns) {
            sb.append(StringUtils.rightPad(c.getName(), nameMaxLen));
            sb.append(' ');
            sb.append(StringUtils.rightPad(c.getDataType(), Column.DATA_TYPE.length()));
            sb.append(' ');
            sb.append(StringUtils.rightPad((c.isPrimaryKey() ? "Yes" : "No"), Column.PRIMARY_KEY.length()));
            sb.append(' ');
            sb.append(StringUtils.rightPad((c.isAutoIncrement() ? "Yes" : "No"), Column.AUTO_INCREMENT.length()));
            sb.append(' ');
            sb.append(StringUtils.rightPad(c.getComment(), commentMaxLen));
            sb.append("\n");
        }
        //footer
        sb.append(StringUtils.rightPad("", width, "-"));
        sb.append(String.format("\nRows:%s", columns.size()));

        System.out.println(sb.toString());
    }

}

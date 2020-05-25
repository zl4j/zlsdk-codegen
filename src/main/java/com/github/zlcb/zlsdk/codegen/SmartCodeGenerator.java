package com.github.zlcb.zlsdk.codegen;

import com.github.zlcb.zlsdk.codegen.config.DataSourceConfig;
import com.github.zlcb.zlsdk.codegen.config.GlobalConfig;
import com.github.zlcb.zlsdk.codegen.database.Query;
import com.github.zlcb.zlsdk.codegen.engine.TemplateEngine;
import com.github.zlcb.zlsdk.codegen.engine.VelocityTemplateEngine;
import com.github.zlcb.zlsdk.codegen.exception.CodeGenException;
import com.github.zlcb.zlsdk.codegen.model.Column;
import com.github.zlcb.zlsdk.codegen.model.Table;
import com.github.zlcb.zlsdk.codegen.util.ConsolePrinter;

import java.util.List;

/**
 * @author Zhongl
 * @date 2020/05/21 15:26
 */
public class SmartCodeGenerator implements Generator {

    private final TemplateEngine engine;

    private final GlobalConfig config;

    private SmartCodeGenerator(GlobalConfig config, TemplateEngine engine) {
        this.config = config;
        this.engine = engine;
    }

    public static void run(GlobalConfig config) {
        run(config, new VelocityTemplateEngine());
    }

    public static void run(GlobalConfig config, TemplateEngine engine) {
        new SmartCodeGenerator(config, engine).run();
    }

    public static void peek(DataSourceConfig dsc) {
        Query query = DataSourceConfig.queryMap(dsc.getDriver());
        List<Table> tables;
        try {
            tables = query.queryTables(dsc);
        } catch (CodeGenException e) {
            e.printStackTrace();
            return;
        }
        //打印到控制台
        ConsolePrinter.printTables(tables);
    }

    public static void peek(DataSourceConfig dsc, String tableName) {
        Query query = DataSourceConfig.queryMap(dsc.getDriver());
        List<Column> columns;
        try {
            columns = query.queryColumns(dsc, tableName);
        } catch (CodeGenException e) {
            e.printStackTrace();
            return;
        }
        //打印到控制台
        ConsolePrinter.printColumns(columns);
    }

    private void run() {
        long startupDate = System.currentTimeMillis();
        try {
            //初始化模板引擎
            engine.init(config);
            //模板引擎开始执行
            engine.run();

            ConsolePrinter.printToBuffer("已完成！共耗时：%sms", String.valueOf(System.currentTimeMillis() - startupDate));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConsolePrinter.flushPrint();
        }
    }

}

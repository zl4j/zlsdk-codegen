package com.github.zlcb.zlsdk.codegen.engine;

import com.github.zlcb.zlsdk.codegen.config.DataSourceConfig;
import com.github.zlcb.zlsdk.codegen.config.GlobalConfig;
import com.github.zlcb.zlsdk.codegen.database.Query;
import com.github.zlcb.zlsdk.codegen.model.Column;
import com.github.zlcb.zlsdk.codegen.template.*;
import com.github.zlcb.zlsdk.codegen.util.ConsolePrinter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Zhongl
 * @date 2020/05/23 01:48
 */
public abstract class AbstractTemplateEngine implements TemplateEngine {

    protected GlobalConfig config;

    protected abstract void write(String templatePath, String outputPath, Map<String, Object> dataMap);

    protected final List<Template> templateList = new ArrayList<Template>();

    @Override
    public void run() {
        //装载模板
        this.loadTemplates();
        if (templateList.isEmpty()) {
            ConsolePrinter.info("生成策略禁用了所有的模板");
            return;
        }
        //判断是否需要生成基类
        if (config.getStrategy()
                .isGenerateBase()) {
            new GenericTemplate(this).execute(null);
        }

        Query query = DataSourceConfig.queryMap(config.getDataSource()
                .getDriver());
        templateList.stream()
                .forEach(template -> {
                    query.queryTables(config.getDataSource())
                            .stream()
                            .filter(t -> { //过滤指定表生成策略
                                if (config.getStrategy()
                                        .getFixedTableNames()
                                        .size() > 0) { //指定了生成表集合
                                    if (!config.getStrategy()
                                            .getFixedTableNames()
                                            .contains(t.getName())) {
                                        return false;
                                    }
                                } else { //指定了忽略表集合
                                    if (config.getStrategy()
                                            .getIgnoreTableNames()
                                            .contains(t.getName())) { //在忽略列表中，则不生成
                                        return false;
                                    }
                                }
                                return true;
                            })
                            .filter(t -> { //检查指定的表名前缀
                                String tableName = t.getName();
                                if (StringUtils.isNotBlank(config.getStrategy()
                                        .getTablePrefix()) && StringUtils.contains(tableName,
                                        config.getStrategy()
                                                .getTablePrefix())) {
                                    tableName = StringUtils.removeFirst(tableName,
                                            config.getStrategy()
                                                    .getTablePrefix());
                                    if (StringUtils.isBlank(tableName)) {
                                        return false;
                                    }
                                }
                                //生成实体名
                                String entityName = CaseUtils.toCamelCase(tableName, true, new char[]{'_'});
                                t.setEntityName(entityName);
                                //生成实体引用名
                                t.setReferenceName(StringUtils.uncapitalize(entityName));
                                return true;
                            })
                            .map(t -> { //查询表的列信息，并注入到表信息中
                                List<Column> columns = query.queryColumns(config.getDataSource(), t.getName());
                                if (columns != null && columns.size() > 0) {
                                    t.setColumns(columns);
                                }
                                return t;
                            })
                            .forEach(t -> {
                                template.execute(t);
                            });
                });
    }

    protected void loadTemplates() {
        if (config.getStrategy()
                .isGenerateEntity()) {
            templateList.add(new EntityTemplate(this));
        }
        if (config.getStrategy()
                .isGenerateMapper()) {
            templateList.add(new MapperTemplate(this));
        }
        if (config.getStrategy()
                .isGenerateService()) {
            templateList.add(new ServiceTemplate(this));
        }
        if (config.getStrategy()
                .isGenerateController()) {
            templateList.add(new ControllerTemplate(this));
        }
        if (config.getStrategy()
                .isGenerateMapping()) {
            templateList.add(new MappingTemplate(this));
        }
    }

    public void outputFile(String templatePath, String outputPath, String outputFileName, String extFileName,
                           Map<String, Object> dataMap) {
        //检查文件生成目录是否存在
        //不存在，则生成目录
        File outputDir = new File(outputPath);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        String fileName = String.format("%s/%s.%s", outputPath, outputFileName, extFileName);
        if (existsFile(fileName)) { //文件是否存在
            //文件存在，且允许覆盖。则直接覆盖
            if (config.getStrategy()
                    .isAllowOverrideFile()) {
                write(templatePath, fileName, dataMap);
            } else {
                //文件存在，且不允许覆盖
                //判断是否以时间戳为后缀生成重复名称的文件
                if (config.getStrategy()
                        .isAllowRepeatFile()) {
                    fileName = String.format("%s.%s", fileName,
                            LocalDateTime.now()
                                    .format(DateTimeFormatter.ofPattern("HHmmss")));
                    //生成文件
                    write(templatePath, fileName, dataMap);
                }
            }
        } else { //文件不存在，直接生成
            write(templatePath, fileName, dataMap);
        }
    }

    protected boolean existsFile(String fileName) {
        return new File(fileName).exists();
    }

    public GlobalConfig getConfig() {
        return config;
    }
}

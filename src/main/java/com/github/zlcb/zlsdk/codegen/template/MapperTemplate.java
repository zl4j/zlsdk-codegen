package com.github.zlcb.zlsdk.codegen.template;

import com.github.zlcb.zlsdk.codegen.engine.AbstractTemplateEngine;
import com.github.zlcb.zlsdk.codegen.model.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhongl
 * @date 2020/05/23 15:38
 */
public class MapperTemplate extends AbstractTemplate {

    public MapperTemplate(AbstractTemplateEngine engine) {
        super(engine);
        this.templatePath = getConfig().getTemplate()
                .getMapper();
    }

    @Override
    public void execute(Table t) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("package", getConfig().getPackages()
                .getMapper());
        dataMap.put("comment", t.getComment());
        dataMap.put("author", getConfig().getAuthor());
        dataMap.put("date", getConfig().getCurrentDate());

        String className = String.format("%sMapper", t.getEntityName());

        dataMap.put("className", className);
        dataMap.put("entityName", t.getEntityName());
        dataMap.put("entityPackage", getConfig().getPackages()
                .getEntity());
        dataMap.put("imapperPackage", getConfig().getPackages()
                .getiMapper());

        //生成Mapper文件
        engine.outputFile(templatePath, getConfig().getOutputPath()
                .getMapper(), className, "java", dataMap);
    }
}

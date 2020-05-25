package com.github.zlcb.zlsdk.codegen.template;

import com.github.zlcb.zlsdk.codegen.engine.AbstractTemplateEngine;
import com.github.zlcb.zlsdk.codegen.model.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhongl
 * @date 2020/05/23 15:39
 */
public class ServiceTemplate extends AbstractTemplate {

    private String implTemplatePath;

    public ServiceTemplate(AbstractTemplateEngine engine) {
        super(engine);
        this.templatePath = getConfig().getTemplate().getService();
        this.implTemplatePath = getConfig().getTemplate().getServiceImpl();
    }

    @Override
    public void execute(Table t) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("package", getConfig().getPackages()
                .getService());
        dataMap.put("comment", t.getComment());
        dataMap.put("author", getConfig().getAuthor());
        dataMap.put("date", getConfig().getCurrentDate());

        String className = String.format("%sService", t.getEntityName());

        dataMap.put("className", className);
        dataMap.put("entityName", t.getEntityName());
        dataMap.put("entityPackage", getConfig().getPackages()
                .getEntity());
        dataMap.put("imapperPackage", getConfig().getPackages()
                .getiMapper());
        dataMap.put("iservicePackage", getConfig().getPackages()
                .getiService());
        dataMap.put("baseServicePackage", getConfig().getPackages()
                .getBaseService());
        dataMap.put("mapperPackage", getConfig().getPackages()
                .getMapper());
        dataMap.put("mapperName", String.format("%sMapper", t.getEntityName()));
        dataMap.put("mapperRefName", String.format("%sMapper", t.getReferenceName()));

        //生成Service文件
        engine.outputFile(templatePath, getConfig().getOutputPath()
                .getService(), className, "java", dataMap);

        //生成ServiceImpl文件
        engine.outputFile(implTemplatePath, getConfig().getOutputPath()
                        .getService()
                        .concat("/impl"), className.concat(
                "Impl"),
                "java", dataMap);
    }
}

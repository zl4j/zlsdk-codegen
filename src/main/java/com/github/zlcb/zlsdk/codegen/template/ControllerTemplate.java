package com.github.zlcb.zlsdk.codegen.template;

import com.github.zlcb.zlsdk.codegen.engine.AbstractTemplateEngine;
import com.github.zlcb.zlsdk.codegen.model.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhongl
 * @date 2020/05/23 15:41
 */
public class ControllerTemplate extends AbstractTemplate {

    public ControllerTemplate(AbstractTemplateEngine engine) {
        super(engine);
        this.templatePath = getConfig()
                .getTemplate()
                .getController();
    }

    @Override
    public void execute(Table t) {
        String className, serviceName, pathName;
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("package", getConfig().getPackages()
                .getController());
        dataMap.put("comment", t.getComment());
        dataMap.put("author", getConfig().getAuthor());
        dataMap.put("date", getConfig().getCurrentDate());

        className = String.format("%sController", t.getEntityName());

        dataMap.put("rPackage", getConfig().getPackages()
                .getR());
        dataMap.put("className", className);
        dataMap.put("entityPackage", getConfig().getPackages()
                .getEntity());
        dataMap.put("entityName", t.getEntityName());
        dataMap.put("entityRefName", StringUtils.uncapitalize(t.getEntityName()));

        serviceName = String.format("%sService", t.getEntityName());
        dataMap.put("servicePackage", getConfig().getPackages()
                .getService());
        dataMap.put("serviceName", serviceName);
        dataMap.put("serviceRefName", StringUtils.uncapitalize(serviceName));

        dataMap.put("pathName", getControllerPathName(getConfig(), t));

        //生成Mapper文件
        engine.outputFile(templatePath, getConfig().getOutputPath()
                .getController(), className, "java", dataMap);
    }
}

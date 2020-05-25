package com.github.zlcb.zlsdk.codegen.template;

import com.github.zlcb.zlsdk.codegen.database.DataType;
import com.github.zlcb.zlsdk.codegen.engine.AbstractTemplateEngine;
import com.github.zlcb.zlsdk.codegen.model.Column;
import com.github.zlcb.zlsdk.codegen.model.Table;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;

import java.util.*;

/**
 * @author Zhongl
 * @date 2020/05/23 12:42
 */
public class EntityTemplate extends AbstractTemplate {

    public EntityTemplate(AbstractTemplateEngine engine) {
        super(engine);
        this.templatePath = getConfig().getTemplate()
                .getEntity();
    }

    @Override
    public void execute(Table t) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("package", getConfig().getPackages()
                .getEntity());
        dataMap.put("comment", t.getComment());
        dataMap.put("author", getConfig().getAuthor());
        dataMap.put("date", getConfig().getCurrentDate());

        dataMap.put("className", t.getEntityName());

        Set<String> importSet = new HashSet<String>();
        List<Map<String, Object>> constList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> fieldList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> keyList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> methodList = new ArrayList<Map<String, Object>>();

        String constName, fieldName, methodName, typeName;
        Map<String, Object> constMap, fieldMap, keyMap, methodMap;
        for (Column col : t.getColumns()) {
            typeName = DataType.get(col.getDataType())
                    .getJavaType();
            if (StringUtils.contains(typeName, ".")) {
                importSet.add(typeName);
                typeName = StringUtils.substringAfterLast(typeName, ".");
            }

            //常量
            constName = StringUtils.upperCase(col.getName());
            constMap = new HashMap<String, Object>(4);
            constMap.put("name", constName);
            constMap.put("value", col.getName());
            constList.add(constMap);

            //变量
            fieldName = CaseUtils.toCamelCase(col.getName(), false, new char[]{'_'});
            fieldMap = new HashMap<String, Object>(4);
            fieldMap.put("name", fieldName);
            fieldMap.put("type", typeName);
            fieldMap.put("comment", col.getComment());
            fieldList.add(fieldMap);
            //主键
            if (col.isPrimaryKey()) {
                keyMap = new HashMap<>(2);
                keyMap.put("name", fieldName);
                keyMap.put("type", typeName);
                keyList.add(keyMap);
            }

            //getter/setter方法
            methodName = CaseUtils.toCamelCase(col.getName(), true, new char[]{'_'});
            methodMap = new HashMap<String, Object>(4);
            methodMap.put("name", methodName);
            methodMap.put("field", fieldName);
            methodMap.put("type", typeName);
            methodList.add(methodMap);

        }
        dataMap.put("imports", importSet);
        dataMap.put("consts", constList);
        dataMap.put("fields", fieldList);
        dataMap.put("keys", keyList);
        dataMap.put("methods", methodList);

        //生成Entity文件
        engine.outputFile(templatePath, getConfig().getOutputPath()
                .getEntity(), t.getEntityName(), "java", dataMap);
    }
}

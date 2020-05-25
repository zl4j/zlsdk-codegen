package com.github.zlcb.zlsdk.codegen.template;

import com.github.zlcb.zlsdk.codegen.engine.AbstractTemplateEngine;
import com.github.zlcb.zlsdk.codegen.model.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhongl
 * @date 2020/05/25 20:46
 */
public class GenericTemplate extends AbstractTemplate {

    public GenericTemplate(AbstractTemplateEngine engine) {
        super(engine);
    }

    @Override
    public void execute(Table t) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("rPackage", getConfig().getPackages()
                .getR());
        dataMap.put("imapperPackage", getConfig().getPackages()
                .getiMapper());
        dataMap.put("iservicePackage", getConfig().getPackages()
                .getiService());
        dataMap.put("baseServicePackage", getConfig().getPackages()
                .getBaseService());

        //R 文件生成
        engine.outputFile(getConfig().getTemplate()
                .getR(), getConfig().getOutputPath()
                .getR(), "R", "java", dataMap);
        //IMapper 文件生成
        engine.outputFile(getConfig().getTemplate()
                .getiMapper(), getConfig().getOutputPath()
                .getiMapper(), "IMapper", "java", dataMap);
        //IService 文件生成
        engine.outputFile(getConfig().getTemplate()
                .getiService(), getConfig().getOutputPath()
                .getiService(), "IService", "java", dataMap);
        //BaseService 文件生成
        engine.outputFile(getConfig().getTemplate()
                .getBaseService(), getConfig().getOutputPath()
                .getBaseService(), "BaseService", "java", dataMap);
    }

}

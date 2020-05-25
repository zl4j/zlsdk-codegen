package com.github.zlcb.zlsdk.codegen.engine;

import com.github.zlcb.zlsdk.codegen.config.GlobalConfig;
import com.github.zlcb.zlsdk.codegen.exception.CodeGenException;
import com.github.zlcb.zlsdk.codegen.util.ConsolePrinter;
import com.github.zlcb.zlsdk.codegen.util.Const;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * @author Zhongl
 * @date 2020/05/23 01:47
 */
public class VelocityTemplateEngine extends AbstractTemplateEngine {

    private VelocityEngine engine;

    @Override
    public void init(GlobalConfig config) {
        this.engine = new VelocityEngine();
        this.engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        this.engine.setProperty("classpath.resource.loader.class",
                ClasspathResourceLoader.class.getName());
        this.engine.setProperty(Velocity.ENCODING_DEFAULT, Velocity.ENCODING_DEFAULT);
        this.engine.setProperty(Velocity.INPUT_ENCODING, Velocity.ENCODING_DEFAULT);
        // 初始化
        this.engine.init();

        this.config = config;
    }

    @Override
    protected void write(String templatePath, String outputPath, Map<String, Object> dataMap) {
        outputPath = outputPath.replaceAll("\\\\", "/");
        Template template = this.engine.getTemplate(templatePath, Const.ENCODING);
        try (FileOutputStream fos = new FileOutputStream(outputPath);
             OutputStreamWriter ow = new OutputStreamWriter(fos, Const.ENCODING);
             BufferedWriter writer = new BufferedWriter(ow)) {
            template.merge(new VelocityContext(dataMap), writer);
        } catch (Exception e) {
            throw new CodeGenException("文件生成异常：" + outputPath, e);
        }
        ConsolePrinter.printToBuffer("已生成：[%s]", outputPath);
    }

}

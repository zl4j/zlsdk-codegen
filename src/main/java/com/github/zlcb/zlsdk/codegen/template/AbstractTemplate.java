package com.github.zlcb.zlsdk.codegen.template;

import com.github.zlcb.zlsdk.codegen.config.GlobalConfig;
import com.github.zlcb.zlsdk.codegen.engine.AbstractTemplateEngine;
import com.github.zlcb.zlsdk.codegen.model.Table;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

/**
 * @author Zhongl
 * @date 2020/05/23 14:07
 */
public abstract class AbstractTemplate implements Template {

    protected AbstractTemplateEngine engine;

    protected String templatePath;

    public AbstractTemplate(AbstractTemplateEngine engine) {
        this.engine = engine;
    }

    protected GlobalConfig getConfig() {
        return engine.getConfig();
    }

    protected String getControllerPathName(GlobalConfig config, Table table) {
        String tableName = table.getName();
        if (StringUtils.isNotBlank(config.getStrategy()
                .getTablePrefix()) && StringUtils.contains(tableName, config.getStrategy().getTablePrefix())) {
            tableName = StringUtils.removeFirst(tableName, config.getStrategy().getTablePrefix());
        }
        int count = StringUtils.countMatches(tableName, '_');
        if (count > 0) {
            return WordUtils.initials(tableName, '_').toLowerCase();
        }
        return tableName.toLowerCase();
    }
}

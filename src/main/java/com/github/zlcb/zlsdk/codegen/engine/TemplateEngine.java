package com.github.zlcb.zlsdk.codegen.engine;

import com.github.zlcb.zlsdk.codegen.config.GlobalConfig;

/**
 * @author Zhongl
 * @date 2020/05/23 01:38
 */
public interface TemplateEngine {

    void init(GlobalConfig config);

    void run();

}

package com.github.zlcb.zlsdk.codegen.util;

/**
 * @author Zhongl
 * @date 2020/05/23 12:48
 */
public interface Const {

    //工程所在目录
    String PROJECT_DIR = System.getProperty("user.dir");
    //Java文件输出目录
    String SOURCE_FOLDERS = PROJECT_DIR + "/src/main/java";
    //资源文件输出目录
    String RESOURCES_FOLDERS = PROJECT_DIR + "/src/main/resources";

    //默认代码作者
    String DEFAULT_AUTHOR = "GenCode";
    //默认基础包
    String DEFAULT_BASE_PACKAGE = "com.github.zlcb";

    String ENCODING = "UTF-8";

    interface Package {
        String R = "common";
        String IMAPPER = "common.mapper";
        String ISERVICE = "common.service";
        String BASE_SERVICE = "common.service";

        String MAPPING = "mapping";
        String ENTITY = "entity";
        String MAPPER = "mapper";
        String SERVICE = "service";
        String CONTROLLER = "controller";
    }

}

package com.github.zlcb.zlsdk.codegen.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成策略配置
 * @author Zhongl
 * @date 2020/05/21 19:17
 */
public class StrategyConfig {

    /**
     * 生成策略信息配置
     **/
    //是否生成基类
    private boolean generateBase;
    //是否生成实体类
    private boolean generateEntity;
    //是否生成数据映射文件
    private boolean generateMapping;
    //是否生成数据访问类
    private boolean generateMapper;
    //是否生成服务接口
    private boolean generateService;
    //是否生成控制类
    private boolean generateController;
    //文件存在时，是否以替换的方式保存（新文件替换旧文件）
    private boolean allowOverrideFile;
    //文件存在且不允许覆盖时，是否以重命名的方式保存（格式：文件名_当前时间戳）
    private boolean allowRepeatFile;
    //需要去除的表名前缀（如：s_user，前缀为：s_）
    private String tablePrefix;

    /**
     * 指定需要生成的表名列表</br>
     * 注：表名同时存在于忽略列表中，仍然生成，不受影响
     */
    private List<String> fixedTableNames = new ArrayList<>();

    /**
     * 指定需要忽略的表名列表
     */
    private List<String> ignoreTableNames = new ArrayList<>();

    private StrategyConfig() {
    }

    public static StrategyConfigurer configurer() {
        return new StrategyConfigurer();
    }

    public static class StrategyConfigurer implements Configurer<StrategyConfig> {

        //是否生成基类
        private boolean generateBase = false;
        //是否生成实体类
        private boolean generateEntity = true;
        //是否生成数据映射文件
        private boolean generateMapping = true;
        //是否生成数据访问类
        private boolean generateMapper = true;
        //是否生成服务接口
        private boolean generateService = true;
        //是否生成控制类
        private boolean generateController = true;

        //文件存在时，是否以替换的方式保存（新文件替换旧文件）
        private boolean allowOverrideFile = false;
        //文件存在且不允许覆盖时，是否以重命名的方式保存（格式：文件名_当前时间戳）
        private boolean allowRepeatFile = true;
        //需要去除的表名前缀（如：s_user，前缀为：s_）
        private String tablePrefix;

        /**
         * 指定需要生成的表名列表</br>
         * 注：表名同时存在于忽略列表中，仍然生成，不受影响
         */
        private List<String> fixedTableNames = new ArrayList<>();

        /**
         * 指定需要忽略的表名列表
         */
        private List<String> ignoreTableNames = new ArrayList<>();

        @Override
        public StrategyConfig config() {
            StrategyConfig config = new StrategyConfig();
            config.generateBase = generateBase;
            config.generateEntity = generateEntity;
            config.generateMapper = generateMapper;
            config.generateService = generateService;
            config.generateController = generateController;
            config.generateMapping = generateMapping;

            config.allowOverrideFile = allowOverrideFile;
            config.allowRepeatFile = allowRepeatFile;
            config.tablePrefix = tablePrefix;

            config.fixedTableNames = fixedTableNames;
            config.ignoreTableNames = ignoreTableNames;
            return config;
        }

        public StrategyConfigurer setGenerateBase(boolean generateBase) {
            this.generateBase = generateBase;
            return this;
        }

        public StrategyConfigurer setGenerateEntity(boolean generateEntity) {
            this.generateEntity = generateEntity;
            return this;
        }

        public StrategyConfigurer setGenerateMapping(boolean generateMapping) {
            this.generateMapping = generateMapping;
            return this;
        }

        public StrategyConfigurer setGenerateMapper(boolean generateMapper) {
            this.generateMapper = generateMapper;
            return this;
        }

        public StrategyConfigurer setGenerateService(boolean generateService) {
            this.generateService = generateService;
            return this;
        }

        public StrategyConfigurer setGenerateController(boolean generateController) {
            this.generateController = generateController;
            return this;
        }

        public StrategyConfigurer setAllowOverrideFile(boolean allowOverrideFile) {
            this.allowOverrideFile = allowOverrideFile;
            return this;
        }

        public StrategyConfigurer setAllowRepeatFile(boolean allowRepeatFile) {
            this.allowRepeatFile = allowRepeatFile;
            return this;
        }

        public StrategyConfigurer setTablePrefix(String tablePrefix) {
            this.tablePrefix = tablePrefix;
            return this;
        }

        public StrategyConfigurer addFixedTableNames(String... fixedTableNames) {
            if (fixedTableNames != null) {
                for (String name : fixedTableNames) {
                    this.fixedTableNames.add(name);
                }
            }
            return this;
        }

        public StrategyConfigurer addIgnoreTableNames(String... ignoreTableNames) {
            if (ignoreTableNames != null) {
                for (String name : ignoreTableNames) {
                    this.ignoreTableNames.add(name);
                }
            }
            return this;
        }
    }

    public boolean isGenerateBase() {
        return generateBase;
    }

    public boolean isGenerateEntity() {
        return generateEntity;
    }

    public boolean isGenerateMapping() {
        return generateMapping;
    }

    public boolean isGenerateMapper() {
        return generateMapper;
    }

    public boolean isGenerateService() {
        return generateService;
    }

    public boolean isGenerateController() {
        return generateController;
    }

    public boolean isAllowOverrideFile() {
        return allowOverrideFile;
    }

    public boolean isAllowRepeatFile() {
        return allowRepeatFile;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public List<String> getFixedTableNames() {
        return fixedTableNames;
    }

    public List<String> getIgnoreTableNames() {
        return ignoreTableNames;
    }
}

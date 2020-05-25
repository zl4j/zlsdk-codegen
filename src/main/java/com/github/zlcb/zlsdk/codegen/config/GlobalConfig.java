package com.github.zlcb.zlsdk.codegen.config;

import com.github.zlcb.zlsdk.codegen.util.Const;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Zhongl
 * @date 2020/05/25 00:29
 */
public class GlobalConfig {

    //基本包
    private String basePackage;
    //程序作者
    private String author;
    //当前时间
    private String currentDate;

    //包名信息
    private PackageConfig packages;
    //输出文件路径名
    private OutputPathConfig outputPath;

    //数据源配置
    private DataSourceConfig dataSource;
    //生成策略配置
    private StrategyConfig strategy;
    //模板配置
    private TemplateConfig template;
    //自定义参数
    private MetaDataCustomizer customizer;

    private GlobalConfig() {
    }

    public static GlobalConfigurer configurer() {
        return new GlobalConfigurer();
    }

    public static class GlobalConfigurer implements Configurer<GlobalConfig> {
        //基本包
        private String basePackage;
        //映射文件包
        private String mappingPackage;
        //程序作者
        private String author;
        //数据源配置
        private DataSourceConfig dataSource;
        //生成策略配置
        private StrategyConfig strategy;
        //模板配置
        private TemplateConfig template;
        //自定义参数
        private MetaDataCustomizer customizer;

        public GlobalConfigurer basePackage(String basePackage) {
            this.basePackage = basePackage;
            return this;
        }

        public GlobalConfigurer mappingPackage(String mappingPackage) {
            this.mappingPackage = mappingPackage;
            return this;
        }

        public GlobalConfigurer author(String author) {
            this.author = author;
            return this;
        }

        public GlobalConfigurer dataSource(DataSourceConfig dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public GlobalConfigurer strategy(StrategyConfig strategy) {
            this.strategy = strategy;
            return this;
        }

        public GlobalConfigurer template(TemplateConfig template) {
            this.template = template;
            return this;
        }

        public GlobalConfigurer metadata(MetaDataCustomizer customizer) {
            customizer.customize();
            this.customizer = customizer;
            return this;
        }

        @Override
        public GlobalConfig config() {
            GlobalConfig config = new GlobalConfig();
            config.basePackage = StringUtils.defaultString(basePackage, Const.DEFAULT_BASE_PACKAGE);
            config.author = StringUtils.defaultString(author, Const.DEFAULT_AUTHOR);
            config.currentDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
            config.dataSource = dataSource;
            config.strategy = strategy == null ? StrategyConfig.configurer()
                    .config() : strategy;
            config.template = template == null ? TemplateConfig.configurer()
                    .config() : template;
            config.customizer = customizer;

            //固定包名生成
            config.packages = new PackageConfig(config);
            //文件路径生成
            config.outputPath = new OutputPathConfig(config);
            mappingPackage = StringUtils.defaultString(mappingPackage, Const.Package.MAPPING);
            config.outputPath.setMapping(String.format("%s/%s", Const.RESOURCES_FOLDERS, mappingPackage.replaceAll(
                    "\\.", "/")));

            return config;
        }
    }

    public String getBasePackage() {
        return basePackage;
    }

    public String getAuthor() {
        return author;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public DataSourceConfig getDataSource() {
        return dataSource;
    }

    public StrategyConfig getStrategy() {
        return strategy;
    }

    public TemplateConfig getTemplate() {
        return template;
    }

    public MetaDataCustomizer getCustomizer() {
        return customizer;
    }

    public PackageConfig getPackages() {
        return packages;
    }

    public OutputPathConfig getOutputPath() {
        return outputPath;
    }
}

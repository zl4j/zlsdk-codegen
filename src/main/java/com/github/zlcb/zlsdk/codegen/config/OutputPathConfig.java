package com.github.zlcb.zlsdk.codegen.config;

import com.github.zlcb.zlsdk.codegen.util.Const;

/**
 * 文件输出路径配置
 * @author Zhongl
 * @date 2020/05/25 17:43
 */
public class OutputPathConfig {

    private String r;
    private String iMapper;
    private String iService;
    private String baseService;

    private String mapping;
    private String entity;
    private String mapper;
    private String service;
    private String controller;

    protected OutputPathConfig(GlobalConfig config) {
        this.r = convertToPath(config.getPackages().getR());
        this.iMapper = convertToPath(config.getPackages().getiMapper());
        this.iService = convertToPath(config.getPackages().getiService());
        this.baseService = convertToPath(config.getPackages().getBaseService());
        this.entity = convertToPath(config.getPackages().getEntity());
        this.mapper = convertToPath(config.getPackages().getMapper());
        this.service = convertToPath(config.getPackages().getService());
        this.controller = convertToPath(config.getPackages().getController());
    }

    private String convertToPath(String packageName) {
        return String.format("%s/%s", Const.SOURCE_FOLDERS, packageName.replaceAll("\\.", "/"));
    }

    public String getR() {
        return r;
    }

    public String getiMapper() {
        return iMapper;
    }

    public String getiService() {
        return iService;
    }

    public String getBaseService() {
        return baseService;
    }

    public String getEntity() {
        return entity;
    }

    public String getMapper() {
        return mapper;
    }

    public String getService() {
        return service;
    }

    public String getController() {
        return controller;
    }

    public String getMapping() {
        return mapping;
    }

    protected void setMapping(String mapping) {
        this.mapping = mapping;
    }
}

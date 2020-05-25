package com.github.zlcb.zlsdk.codegen.config;

import com.github.zlcb.zlsdk.codegen.util.Const;

/**
 * @author Zhongl
 * @date 2020/05/25 17:43
 */
public class PackageConfig {

    private String r;
    private String iMapper;
    private String iService;
    private String baseService;
    private String entity;
    private String mapper;
    private String service;
    private String controller;

    protected PackageConfig(GlobalConfig config) {
        this.r = String.format("%s.%s", config.getBasePackage(), Const.Package.R);
        this.iMapper = String.format("%s.%s", config.getBasePackage(), Const.Package.IMAPPER);
        this.iService = String.format("%s.%s", config.getBasePackage(), Const.Package.ISERVICE);
        this.baseService = String.format("%s.%s", config.getBasePackage(), Const.Package.BASE_SERVICE);
        this.entity = String.format("%s.%s", config.getBasePackage(), Const.Package.ENTITY);
        this.mapper = String.format("%s.%s", config.getBasePackage(), Const.Package.MAPPER);
        this.service = String.format("%s.%s", config.getBasePackage(), Const.Package.SERVICE);
        this.controller = String.format("%s.%s", config.getBasePackage(), Const.Package.CONTROLLER);
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
}

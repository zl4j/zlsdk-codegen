package com.github.zlcb.zlsdk.codegen.config;

import org.apache.commons.lang3.StringUtils;

/**
 * 模板信息配置
 * @author Zhongl
 * @date 2020/05/25 01:54
 */
public class TemplateConfig {

    //模板文件目录
    private static final String TEMPLATE_PATH = "/codegen";

    //IMapper模板文件名
    private final String iMapper = TEMPLATE_PATH + "/imapper.java.vm";
    //IService模板文件
    private final String iService = TEMPLATE_PATH + "/iservice.java.vm";
    //BaseService模板文件名
    private final String baseService = TEMPLATE_PATH + "/baseService.java.vm";
    //R模板文件名
    private final String r = TEMPLATE_PATH + "/r.java.vm";

    //映射文件模板文件名
    private String mapping = TEMPLATE_PATH + "/mapping.xml.vm";
    //实体类模板文件名
    private String entity = TEMPLATE_PATH + "/entity.java.vm";
    //数据访问类模板文件名
    private String mapper = TEMPLATE_PATH + "/mapper.java.vm";
    //服务接口模板文件名
    private String service = TEMPLATE_PATH + "/service.java.vm";
    //服务实现类模板文件名
    private String serviceImpl = TEMPLATE_PATH + "/serviceImpl.java.vm";
    //控制类模板文件名
    private String controller = TEMPLATE_PATH + "/controller.java.vm";

    private TemplateConfig() {
    }

    public static TemplateConfigurer configurer() {
        return new TemplateConfigurer();
    }

    public static class TemplateConfigurer implements Configurer<TemplateConfig> {

        //映射文件模板文件名
        private String mapping;
        //实体类模板文件名
        private String entity;
        //数据访问类模板文件名
        private String mapper;
        //服务接口模板文件名
        private String service;
        //服务实现类模板文件名
        private String serviceImpl;
        //控制类模板文件名
        private String controller;

        @Override
        public TemplateConfig config() {
            TemplateConfig config = new TemplateConfig();
            if (StringUtils.isNotBlank(mapping)) {
                config.mapping = mapping;
            }
            if (StringUtils.isNotBlank(entity)) {
                config.entity = entity;
            }
            if (StringUtils.isNotBlank(mapper)) {
                config.mapper = mapper;
            }
            if (StringUtils.isNotBlank(service)) {
                config.service = service;
            }
            if (StringUtils.isNotBlank(serviceImpl)) {
                config.serviceImpl = serviceImpl;
            }
            if (StringUtils.isNotBlank(controller)) {
                config.controller = controller;
            }
            return config;
        }

        public TemplateConfigurer setMapping(String mapping) {
            this.mapping = mapping;
            return this;
        }

        public TemplateConfigurer setEntity(String entity) {
            this.entity = entity;
            return this;
        }

        public TemplateConfigurer setMapper(String mapper) {
            this.mapper = mapper;
            return this;
        }

        public TemplateConfigurer setService(String service) {
            this.service = service;
            return this;
        }

        public TemplateConfigurer setServiceImpl(String serviceImpl) {
            this.serviceImpl = serviceImpl;
            return this;
        }

        public TemplateConfigurer setController(String controller) {
            this.controller = controller;
            return this;
        }
    }

    public String getiMapper() {
        return iMapper;
    }

    public String getiService() {
        return iService;
    }

    public String getR() {
        return r;
    }

    public String getBaseService() {
        return baseService;
    }

    public String getMapping() {
        return mapping;
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

    public String getServiceImpl() {
        return serviceImpl;
    }

    public String getController() {
        return controller;
    }
}

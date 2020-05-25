package com.github.zlcb.zlsdk.codegen.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhongl
 * @date 2020/05/21 23:10
 */
public abstract class MetaDataCustomizer {

    private final Map<String,Object> metaDataMap = new HashMap<>();

    protected abstract void customize();

    public MetaDataCustomizer push(String key, Object value) {
        this.metaDataMap.put(key, value);
        return this;
    }

    public Map<String,Object> metadata() {
        return metaDataMap;
    }
}

package com.github.zlcb.zlsdk.codegen.config;

import com.github.zlcb.zlsdk.codegen.database.Driver;
import com.github.zlcb.zlsdk.codegen.database.MySqlQuery;
import com.github.zlcb.zlsdk.codegen.database.Query;
import com.github.zlcb.zlsdk.codegen.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 * @author Zhongl
 * @date 2020/05/21 23:07
 */
public class DataSourceConfig {

    private static final Map<Driver, Query> QUERY_MAP = new HashMap<Driver, Query>();

    static {
        QUERY_MAP.put(Driver.MySql, new MySqlQuery());
    }

    //数据库驱动
    private Driver driver;
    //数据库驱动名
    private String driverName;
    //数据库URL
    private String url;
    //用户名
    private String username;
    //用户密码
    private String password;

    private DataSourceConfig() {}

    public static Query queryMap(Driver driver) {
        return QUERY_MAP.get(driver);
    }

    public static DataSourceConfigurer configurer() {
        return new DataSourceConfigurer();
    }

    public static class DataSourceConfigurer implements Configurer<DataSourceConfig> {

        private Driver driver = Driver.MySql;
        private String driverName = Driver.MySql.code;
        private String url;
        private String username;
        private String password;

        public DataSourceConfigurer setDriver(Driver driver) {
            this.driver = driver;
            return this;
        }

        public DataSourceConfigurer setDriverName(String driverName) {
            this.driverName = driverName;
            return this;
        }

        public DataSourceConfigurer setUrl(String url) {
            this.url = url;
            return this;
        }

        public DataSourceConfigurer setUsername(String username) {
            this.username = username;
            return this;
        }

        public DataSourceConfigurer setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public DataSourceConfig config() {
            Assert.isNotNull(driver, "dataSource.driver 不能为空");
            Assert.isNotEmpty(driverName, "dataSource.driverName 不能为空");
            Assert.isNotEmpty(url, "dataSource.url 不能为空");
            Assert.isNotEmpty(username, "dataSource.username 不能为空");

            DataSourceConfig config = new DataSourceConfig();
            config.driver = driver;
            config.driverName = driverName;
            config.url = url;
            config.username = username;
            config.password = password;
            return config;
        }
    }

    public Driver getDriver() {
        return driver;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

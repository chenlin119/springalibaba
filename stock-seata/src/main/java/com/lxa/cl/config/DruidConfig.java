package com.lxa.cl.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() throws SQLException {
        DruidDataSource dds = new DruidDataSource();
        dds.setInitialSize(5);                                  /* 初始化时建立物理连接的个数 */
        dds.setMaxActive(150);                                   /* 最大连接池数量 */
        dds.setMaxWait(60000);                                  /* 获取连接时最大等待时间，单位毫秒 */
        dds.setMinIdle(20);                                      /* 最小连接池数量 */
        dds.setTimeBetweenEvictionRunsMillis(60000);            /* Destroy线程会检测连接的间隔时间 */
        dds.setMinEvictableIdleTimeMillis(30000);              /* 连接保持空闲而不被驱逐的最小时间 */
        dds.setValidationQuery("SELECT 1 FROM DUAL");           /* 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。 */
        dds.setTestWhileIdle(true);                             /* 建议配置为true，不影响性能，并且保证安全性 */
        dds.setTestOnBorrow(false);                             /* 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能 */
        dds.setTestOnReturn(false);                             /* 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能 */
        dds.setPoolPreparedStatements(false);                   /* 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭 */
        dds.setFilters("stat,wall");                            /* 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat, 日志用的filter:log4j, 防御sql注入的filter:wall */
        dds.setMaxPoolPreparedStatementPerConnectionSize(50);   /* 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100 */
        dds.setUseGlobalDataSourceStat(true);
        Properties properties = new Properties();
        properties.setProperty("druid.stat.mergeSql", "true");
        properties.setProperty("druid.stat.slowSqlMillis", "100");
        dds.setConnectProperties(properties);
        dds.setDbType("mysql");//mysql必须小写
        return dds;
    }


    @Bean
    public ServletRegistrationBean statViewServlet() {
        //创建servlet注册实体
        // 1.StatViewServlet是druid实现的一个Servlet容器，来显示druid的一些信息
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置ip白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
        servletRegistrationBean.addInitParameter("deny", "192.168.0.19");
        //设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    //设置网站的过滤信息
    @Bean
    public FilterRegistrationBean statFilter() {
        //创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}



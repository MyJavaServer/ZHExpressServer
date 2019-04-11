package com.zhihui.zhexpress.config.db;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.zhihui.zhexpress.mapper", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class DataSourceConfig {

    @Bean(name = "masterDataSource")
    @Qualifier("masterDataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mybatis/*.xml"));
        //配置mybatis resulttypr=map,返回null时不显示字段
        org.apache.ibatis.session.Configuration configutation = new org.apache.ibatis.session.Configuration();
        configutation.setCallSettersOnNulls(true);
        configutation.setMapUnderscoreToCamelCase(true);
        sessionFactoryBean.setConfiguration(configutation);
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "false");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        // 添加插件
//        sessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager bfTransactionManager(@Qualifier("masterDataSource") DataSource prodDataSource) {
        return new DataSourceTransactionManager(prodDataSource);
    }


}
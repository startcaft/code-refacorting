package com.basic.core.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * 从数据库数据源配置类
 */
@Configuration
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterDataSourceConfig.class);

    /**
     * 从数据库Mapper接口的精确包名
     */
    static final String PACKAGE = "com.basic.core.dao.cluster";
    /**
     * 从数据库Mapper映射文件所在位置
     */
    static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

    /*简单的JDBC连接配置*/
    @Value("${cluster.datasource.url}")
    private String url;
    @Value("${cluster.datasource.username}")
    private String user;
    @Value("${cluster.datasource.password}")
    private String password;
    @Value("${cluster.datasource.driverClassName}")
    private String driverClass;

    /*数据源的具体配置*/
    @Value("${cluster.datasource.initialSize}")
    private Integer initialSize;
    @Value("${cluster.datasource.minIdle}")
    private Integer minIdle;
    @Value("${cluster.datasource.maxActive}")
    private Integer maxActive;
    @Value("${cluster.datasource.maxWait}")
    private Integer maxWait;
    @Value("${cluster.datasource.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${cluster.datasource.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${cluster.datasource.validationQuery}")
    private String validationQuery;
    @Value("${cluster.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${cluster.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${cluster.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${cluster.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${cluster.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${cluster.datasource.filters}")
    private String filters;
    @Value("${cluster.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean(name = "clusterDataSource")
    public DataSource clusterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            dataSource.setFilters(filters);
        }
        catch (SQLException e) {
            LOGGER.error("cluster druid dataSource configuration initialization filter", e);
        }
        dataSource.setConnectionProperties(connectionProperties);

        return dataSource;
    }

    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setConfigLocation(new ClassPathResource(MasterDataSourceConfig.MYBATIS_CONFIG_FILE));
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
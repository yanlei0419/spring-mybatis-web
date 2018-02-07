//package com.annotation.config;
//
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
///**
// * Description: <类功能描述>. <br>
// * <p>
// * <使用说明>
// * </p>
// * Makedate:2014年9月3日 下午5:24:02
// * 
// * @author Administrator
// * @version V1.0
// */
//
//@Configuration
//// 加载资源文件
//@PropertySource({ "classpath:/jdbc.properties" })
//@MapperScan("com.seeker.mapper")
//public class DataSourceConfig {
//
//	@Value("${jdbc.driver}")
//	private String driverClassName;
//	@Value("${jdbc.url}")
//	private String url;
//	@Value("${jdbc.username}")
//	private String username;
//	@Value("${jdbc.password}")
//	private String password;
//	@Value("${jdbc.initialSize}")
//	private int initialSize;
//
//	// <!-- 配置初始化大小、最小、最大 -->
//	// <!-- 通常来说，只需要修改initialSize、minIdle、maxActive -->
//	@Value("${jdbc.minIdle}")
//	private int minIdle;
//	@Value("${jdbc.maxActive}")
//	private int maxActive;
//	@Value("${jdbc.testWhileIdle}")
//	private boolean testWhileIdle;
//	// <!-- 配置获取连接等待超时的时间 -->
//	@Value("${jdbc.maxWait}")
//	private long maxWait;
//
//	// <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
//	@Value("${jdbc.minEvictableIdleTimeMillis}")
//	private long minEvictableIdleTimeMillis;
//	// <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
//	@Value("${jdbc.timeBetweenEvictionRunsMillis}")
//	private String timeBetweenEvictionRunsMillis;
//
//	// @Value("${jdbc.filters}")
//	private String filters = "stat,log4j";
//
//	// @Value("${jdbc.connectionProperties}")
//	private String connectionProperties = "config.decrypt=true";
//
//	// private List proxyFilters;
//
//	@Bean("dataSource")
//	public DataSource dataSource() throws SQLException {
//		DruidDataSource dataSource = new DruidDataSource();
//
//		dataSource.setDriverClassName(driverClassName);
//		dataSource.setUrl(url);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//		dataSource.setInitialSize(initialSize);
//		dataSource.setMinIdle(minIdle);
//		dataSource.setMaxActive(maxActive);
//		dataSource.setTestWhileIdle(testWhileIdle);
//		dataSource.setMaxWait(maxWait);
//		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//		dataSource.setFilters(filters);
//		dataSource.setConnectionProperties(connectionProperties);
//		// dataSource.setProxyFilters(proxyFilters);
//
//		return dataSource;
//	}
//
//	@Bean(name = "sqlSessionFactory")
//	public SqlSessionFactory sqlSessionFactoryBean() throws SQLException {
//		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setDataSource(dataSource());
//		// 添加XML目录
//		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		try {
//			bean.setMapperLocations(resolver.getResources("classpath:mapper/**/*Mapper.xml"));
//			return bean.getObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Bean
//	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}
//
//	@Bean
//	public DataSourceTransactionManager annotationDrivenTransactionManager() throws SQLException {
//		return new DataSourceTransactionManager(dataSource());
//	}
//
//}

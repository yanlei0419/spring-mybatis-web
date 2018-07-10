package org.springboot.config;
//package com.zx.config;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.test.annotation.Repeat;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.mybatis.spring.SqlSessionFactoryBean;
//
//@Configuration
////启用注解事务管理，使用CGLib代理
//@EnableTransactionManagement(proxyTargetClass = true)
//@Import({DataSourceConfig.class})
//@MapperScan("com.seeker.mapper")
//public class DaoConfig {
//	@Bean(name="transactionManager")
//	@Resource(name="dataSource")
//	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource ) {
//		DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager(dataSource);
//		return dataSourceTransactionManager;
//	}
//	
//	@Bean(name="sqlSessionFactory")
//	public SqlSessionFactoryBean SqlSessionFactoryBean(DataSource dataSource ) {
//		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
//		bean.setDataSource(dataSource);
//		bean.setConfigLocation("classpath:application/mybatis-config.xml");
//		return bean;
//	}
//	
//	
//	<!-- spring和MyBatis完美整合，不需要mybatis的配置映射文件 -->
//	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
//		<property name="dataSource" ref="dataSource" />
//		<property name="configLocation" value="classpath:application/mybatis-config.xml" />
//	</bean>
//	
//	
//	<!-- DAO接口所在包名，Spring会自动查找其下的类 -->
//	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
//		<property name="basePackage" value="com.seeker.mapper" />
//		<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
//	</bean>
//	
//}
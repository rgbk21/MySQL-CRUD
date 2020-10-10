package com.example.demo.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.example.demo.dao"})
@PropertySource({"classpath:persistence-${persistenceTarget}.properties"})
public class PersistenceJPAConfig {



}

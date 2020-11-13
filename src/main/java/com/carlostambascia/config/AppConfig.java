package com.carlostambascia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {@ComponentScan("com.carlostambascia.dao"), @ComponentScan("com.carlostambascia.service")})
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties props = new Properties();
        // Setting JDBC properties
        props.put(DRIVER, env.getProperty("spring.datasource.driver"));
        props.put(URL, env.getProperty("spring.datasource.url"));
        props.put(USER, env.getProperty("spring.datasource.username"));
        props.put(PASS, env.getProperty("spring.datasource.password"));

        // Setting Hibernate properties
        props.put(SHOW_SQL, env.getProperty("spring.jpa.show-sql"));
        props.put(HBM2DDL_AUTO, env.getProperty("spring.jpa.hibernate.ddl-auto"));

        props.put(DIALECT, env.getProperty("spring.jpa.properties.hibernate.dialect"));
        props.put(CURRENT_SESSION_CONTEXT_CLASS, env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        props.put(FORMAT_SQL, env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        props.put(NON_CONTEXTUAL_LOB_CREATION, env.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setPackagesToScan("com.carlostambascia.model");

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}


package com.centime.l1.dbconnectionservice.db.config;

import jakarta.persistence.EntityManagerFactory;
import java.util.Objects;
import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Custom JPA configuration to establish connection to Database
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.centime.l1.dbconnectionservice")
public class NameToColorMappingJpaDbConfig {

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSource() {
        return this.dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.centime.l1.dbconnectionservice");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateAdditionalProperties());
        em.setEntityManagerFactoryInterface(EntityManagerFactory.class);
        return em;
    }

    private Properties hibernateAdditionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.ddl-auto", this.env.getProperty("spring.jpa.hibernate.ddl-auto","update"));
        properties.setProperty("hibernate.hbm2ddl.auto", this.env.getProperty("spring.jpa.hibernate.ddl-auto","update"));
        properties.setProperty("hibernate.show_sql", this.env.getProperty("spring.jpa.show-sql","true"));
        properties.setProperty("hibernate.format_sql", this.env.getProperty("spring.jpa.properties.hibernate.format_sql","true"));
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}

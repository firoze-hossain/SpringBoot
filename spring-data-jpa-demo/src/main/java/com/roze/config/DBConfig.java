package com.roze.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DBConfig {
    @Bean
    DriverManagerDataSource driverManagerDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/studentdb");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("firoze28");
        return driverManagerDataSource;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(driverManagerDataSource());
        entityManagerFactoryBean.setPackagesToScan("com.roze.entity");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return entityManagerFactoryBean;
    }

    @Bean
    EntityManager entityManager(@Autowired EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;

    }
}

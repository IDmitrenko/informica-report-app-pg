package ru.avers.informica.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"ru.avers.informica"})
@PropertySource("classpath:application.yml")
@EnableTransactionManagement
public class AppConfig {
    @Autowired
    Environment environment;
}

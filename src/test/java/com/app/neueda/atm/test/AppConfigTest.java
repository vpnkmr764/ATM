package com.app.neueda.atm.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan(basePackages = {"com.app.neueda.atm"})
public class AppConfigTest {

}

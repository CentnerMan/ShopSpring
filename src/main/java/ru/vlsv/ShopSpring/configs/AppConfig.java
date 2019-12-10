package ru.vlsv.ShopSpring.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 29.10.2019
 * @link https://github.com/Centnerman
 */

@Configuration
@PropertySource("classpath:private.properties")
@ComponentScan("ru.vlsv.ShopSpring")
public class AppConfig {
}

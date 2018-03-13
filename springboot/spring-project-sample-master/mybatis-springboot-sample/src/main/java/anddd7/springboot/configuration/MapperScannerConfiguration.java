package anddd7.springboot.configuration;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扫描mybatis的接口
 */
@Configuration
// 因为这个对象的扫描，需要在MyBatisConfig的后面注入，所以加上下面的注解
@AutoConfigureAfter(MyBatisConfiguration.class)
public class MapperScannerConfiguration {
    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        //创建内置的config
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //获取之前注入的beanName为sqlSessionFactory的对象
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //会触发 No MyBatis mapper was found in '[anddd7.springboot.controller, anddd7.springboot]' package.  警告
        mapperScannerConfigurer.setBasePackage("anddd7.springboot.dao");
        return mapperScannerConfigurer;
    }
}

package Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhouchao on 2019/2/14.
 */
@ImportResource({"classpath:application-dubbo.xml"})

@ComponentScan(basePackages = "Controller,Service,Service.impl")
@EnableAutoConfiguration
@SpringBootApplication
//@ImportResource(locations = {"classpath:application-dubbo.xml"})
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);

//        MqService bean = run.getBean(MqService.class);
//        bean.receiveMessage();
    }
}

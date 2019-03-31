package Controller;

import Service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.DubboService;


/**
 * Created by zhouchao on 2019/2/14.
 */
@RestController
public class SpringBootController {


    @Autowired
    private UserService userService;

    @Reference
    private DubboService dubboService;


    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("访问了后台数据");

        return "hello zc";
    }


    @RequestMapping("/addUser")
    public String insertUser(String userName,int age) {
        return String.valueOf(userService.addUser(userName,age));

    }


    @RequestMapping("/dubbo")
    public String dubboHello(){

        System.out.println("1111");
        dubboService.sayHello();

        return "";
    }
}

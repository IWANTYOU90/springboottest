package Service.impl;

import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by zhouchao on 2019/2/17.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addUser(String userName, int age) {

        int result = jdbcTemplate.update("insert into users(`name`, `age`) VALUES(?,?) ",userName,age);
        return result;
    }
}

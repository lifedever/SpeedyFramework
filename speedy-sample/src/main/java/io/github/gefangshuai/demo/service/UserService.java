package io.github.gefangshuai.demo.service;

import io.github.gefangshuai.demo.dao.UserDao;
import io.github.gefangshuai.demo.model.User;
import io.github.gefangshuai.ext.persistence.CoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by gefangshuai on 2016/1/6.
 */
@Service
public class UserService extends CoreService<User, Long>{

    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        super.coreDao = userDao;
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}

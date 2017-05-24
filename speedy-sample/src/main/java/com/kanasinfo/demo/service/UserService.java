package com.kanasinfo.demo.service;

import com.kanasinfo.demo.dao.UserRepository;
import com.kanasinfo.demo.model.User;
import com.kanasinfo.ext.persistence.SupportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by gefangshuai on 2016/1/6.
 */
@Service
public class UserService extends SupportService<User, String> {

    private UserRepository userRepository;

    @Resource
    public void setUserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
        super.supportRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

package io.github.gefangshuai.demo.service;

import io.github.gefangshuai.demo.dao.UserRepository;
import io.github.gefangshuai.demo.model.User;
import io.github.gefangshuai.ext.persistence.SupportService;
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

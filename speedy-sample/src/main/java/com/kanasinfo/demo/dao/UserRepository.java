package com.kanasinfo.demo.dao;

import com.kanasinfo.demo.model.User;
import com.kanasinfo.ext.persistence.SupportRepository;

/**
 * Created by gefangshuai on 2016/1/6.
 */
public interface UserRepository extends SupportRepository<User, String> {
    User findByUsername(String username);
}

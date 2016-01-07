package io.github.gefangshuai.demo.dao;

import io.github.gefangshuai.demo.model.User;
import io.github.gefangshuai.ext.persistence.CoreDao;

/**
 * Created by gefangshuai on 2016/1/6.
 */
public interface UserDao extends CoreDao<User, Long> {
    User findByUsername(String username);
}

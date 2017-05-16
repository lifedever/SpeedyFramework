package io.github.gefangshuai.demo.dao;

import io.github.gefangshuai.demo.model.User;
import io.github.gefangshuai.ext.persistence.SupportRepository;

/**
 * Created by gefangshuai on 2016/1/6.
 */
public interface UserRepository extends SupportRepository<User, String> {
    User findByUsername(String username);
}

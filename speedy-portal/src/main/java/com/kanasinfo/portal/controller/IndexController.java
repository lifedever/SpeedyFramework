package com.kanasinfo.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gefangshuai on 2017/4/9.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public String index(){
        return "index";
    }

    @RequestMapping("/users")
    public String users(){
        return "users";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

}

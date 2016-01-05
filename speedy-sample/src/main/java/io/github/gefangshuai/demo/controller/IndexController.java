package io.github.gefangshuai.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gefan on 2016/1/4.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/hello")
    public String hello() throws RuntimeException {
        throw new RuntimeException("server error!");
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }
}

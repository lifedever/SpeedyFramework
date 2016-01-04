package io.github.gefangshuai.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gefan on 2016/1/4.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        Logger.getLogger(IndexController.class).debug("hello index");
        System.out.println("dddd");
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() throws RuntimeException {
        throw new RuntimeException("server error!");
    }
}

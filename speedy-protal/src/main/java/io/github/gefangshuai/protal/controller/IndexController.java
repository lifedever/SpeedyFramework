package io.github.gefangshuai.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

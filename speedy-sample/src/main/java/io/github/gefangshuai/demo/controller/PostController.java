package io.github.gefangshuai.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gefangshuai on 2016/1/6.
 */
@Controller
@RequestMapping("/p")
public class PostController {
    @RequestMapping
    public String index(){
        return "p/index";
    }
}

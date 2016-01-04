package io.github.gefangshuai.projects.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gefan on 2016/1/4.
 */
@RestController
@RequestMapping("/rest")
public class IndexRestController {
    @RequestMapping
    public String hello(){
        return "Hello World!";
    }
}

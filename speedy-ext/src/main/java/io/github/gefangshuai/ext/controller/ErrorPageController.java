package io.github.gefangshuai.ext.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gefan on 2016/1/4.
 */
@Controller
public class ErrorPageController {

    @RequestMapping("/404")
    public String error404(){
        return "404";
    }
    @RequestMapping("/400")
    public String error400(){
        return "400";
    }
    @RequestMapping("/500")
    public String error500(){
        return "500";
    }

}

package io.github.gefangshuai.demo.controller;

import io.github.gefangshuai.demo.model.User;
import io.github.gefangshuai.ext.utils.FlashMessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    public String saveUser(User user, String confirm_password, RedirectAttributes redirectAttributes){
        if (StringUtils.isNotBlank(confirm_password) && confirm_password.equals(user.getPassword())) {
            user.encryptUser();
            FlashMessageUtils.success(redirectAttributes, "注册成功，请登录！");
            return "redirect:/login";
        }else{
            FlashMessageUtils.error(redirectAttributes, "密码不符合标准!");
            return "redirect:/register";
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }
}

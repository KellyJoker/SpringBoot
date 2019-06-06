package com.dxd.demo01.web;

import com.dxd.demo01.domain.User;
import com.dxd.demo01.service.MyUserDetailsService;
import com.dxd.demo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * 用户登录页面跳转，需要在 WebMvcConfig 中配置视图解析器
     * @return
     */
    @RequestMapping(value="/login")
    public String commonController() {
        return "login";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "good";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@RequestBody User user){
        try {
            userService.save(user);
            return  "success";
        }catch (Exception e){
            e.printStackTrace();
            return  "failed";
        }
    }

    @RequestMapping(value = "/saveAll",method = RequestMethod.POST)
    public String saveAll(@RequestBody List<User> users){
        try {
            userService.saveAll(users);
            return  "success";
        }catch (Exception e){
            e.printStackTrace();
            return  "failed";
        }
    }

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    public User findOne(Integer id){
        try {
            User user = userService.findOne(id);
            return  user;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String delete(Integer id){
        try {
            userService.delete(id);
            return  "success";
        }catch (Exception e){
            e.printStackTrace();
            return  "failed";
        }
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<User> findAll(){
        try {
            return  userService.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}

package com.yrd.farm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.yrd.farm.entities.User;
import com.yrd.farm.repository.NodeRepository;
import com.yrd.farm.repository.TerminalRepository;
import com.yrd.farm.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController {

//    @DeleteMapping
//    @PutMapping
//    @GetMapping

	@Autowired
	private NodeRepository nodeService;
	
	@Autowired
	private UserRepository userService;
	
	@Autowired
	private TerminalRepository terminalService;
	
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,HttpSession session){
    	
    	User user = new User();
    	user.setPassword(password);
    	user.setUsername(username);
		Example<User> example = Example.of(user);
    	Optional<User> one = userService.findOne(example);
    	
        if(userService.exists(example)){
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",username);
            session.setAttribute("userId", one.get().getId());
            return "redirect:/main.html";
        }else{
            //登陆失败

            map.put("msg","用户名密码错误");
            return  "login";
        }

    }
    @GetMapping(value = "mainUI")
    public String main(){
    	System.out.println("主界面！");
    	  return "redirect:/main.html";
    }
}

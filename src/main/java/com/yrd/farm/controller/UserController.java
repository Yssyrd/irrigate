package com.yrd.farm.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.yrd.farm.entities.User;
import com.yrd.farm.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	//查询所有员工返回列表页面
    @GetMapping("/users")
    public String  list(Model model){
        Collection<User> users = userRepository.findAll();

        //放在请求域中
        model.addAttribute("users",users);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "user/list";
    }

    //来到员工添加页面
    @GetMapping("/user")
    public String toAddPage(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        return "user/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/user")
    public String addEmp(User user){
        //来到员工列表页面

        System.out.println("保存的员工信息："+user);
        //保存员工
        userRepository.save(user);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/users";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
    	User user = userRepository.getOne(id);
        model.addAttribute("user",user);

        return "user/add";
    }

    //员工修改；需要提交员工id；
    @PutMapping("/user")
    public String updateEmployee(User user){
        System.out.println("修改的员工数据："+user);
        userRepository.save(user);
        return "redirect:/users";
    }

    //员工删除
    @DeleteMapping("/user/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
    	userRepository.deleteById(id);
        return "redirect:/users";
    }
	
}

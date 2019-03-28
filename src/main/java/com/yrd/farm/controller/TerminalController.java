package com.yrd.farm.controller;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.yrd.farm.entities.Terminal;
import com.yrd.farm.entities.User;
import com.yrd.farm.repository.GetUUID;
import com.yrd.farm.repository.TerminalRepository;
import com.yrd.farm.repository.UserRepository;

@Controller
public class TerminalController {

	@Autowired
	private TerminalRepository service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GetUUID uuid;
	
	@GetMapping("/terminals")
    public String nodes(Model model){
		Sort sort = new Sort(Sort.Direction.DESC,"loginDate");
        Collection<Terminal> terminals = service.findAll(sort);
        System.out.println("获取所有结点信息");
        //放在请求域中
        model.addAttribute("terminals",terminals);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "terminal/terminalList";
    }
	
	@GetMapping("/terminal")
    public String terminals(Model model){
		
		Collection<User> users = userRepository.findAll();
        model.addAttribute("users",users);
		
        return "terminal/terminalAdd";
    }
	
	@PostMapping(value=("/terminal"))
	public String addTerminal(Terminal terminal,Model model){
        System.out.println("保存的结点信息："+terminal);
        terminal.setLoginDate(new Timestamp(new Date().getTime()));
        terminal.setSN(uuid.get16UUID());
        service.save(terminal);
        
        return "redirect:/terminals";
    }
	
	@DeleteMapping("/terminal/{id}")
    public String deleteTer(@PathVariable("id") Integer id,Model model){
        service.deleteById(id);
        return "redirect:/terminals";
    }
    
    @GetMapping("/terminal/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
    	Terminal terminal = service.getOne(id);
        model.addAttribute("terminal",terminal);
        
        Collection<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        
        return "terminal/terminalAdd";
    }
    
    @PutMapping("/terminal")
    public String updateTerminal(Terminal terminal){
    	System.out.println(terminal);
        service.save(terminal);
        return "redirect:/terminals";
    }
}

package com.yrd.farm.controller;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yrd.farm.entities.Node;
import com.yrd.farm.entities.Terminal;
import com.yrd.farm.repository.NodeRepository;
import com.yrd.farm.repository.TerminalRepository;
import com.yrd.farm.service.GetUUID;


@Controller
public class NodeController {

	@Autowired
	private NodeRepository service;
	
	@Autowired
	private TerminalRepository Tservice;
	
	@Autowired
	private GetUUID uuid;
	
	@GetMapping("/nodes")
    public String nodes(Model model){
        Collection<Node> nodes = service.findAll();
        System.out.println("获取所有结点信息");
        //放在请求域中
        model.addAttribute("nodes",nodes);
     
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "nodes/nodesList";
    }
	
	@GetMapping("/node")
    public String nod(Model model){
		
		Collection<Terminal> terminals = Tservice.findAll();
        model.addAttribute("terminals",terminals);
		
        return "nodes/nodesAdd";
    }
	
	@PostMapping(value=("/node"))
	public String addNode(Node node,Model model){
        System.out.println("保存的结点信息："+node);
        node.setLoginDate(new Timestamp(new Date().getTime()));
        node.setSN(uuid.get32UUID());
        service.save(node);
        
        return "redirect:/nodes";
    }
	
    @DeleteMapping("/node/{id}")
    public String deleteNode(@PathVariable("id") Integer id,Model model){
        service.deleteById(id);
        return "redirect:/nodes";
    }
    
    @GetMapping("/node/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
    	Node node = service.getOne(id);
        model.addAttribute("node",node);
        Collection<Terminal> terminals = Tservice.findAll();
        model.addAttribute("terminals",terminals);
        return "nodes/nodesAdd";
    }
    
  //员工修改；需要提交员工id；
    @PutMapping("/node")
    public String updateNode(Node node,Model model){
    	System.out.println(node);
        service.save(node);
        return "redirect:/nodes";
    }
}

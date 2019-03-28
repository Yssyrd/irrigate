package com.yrd.farm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yrd.farm.common.EntityToJson;
import com.yrd.farm.component.WebSocketServer;
import com.yrd.farm.entities.Node;
import com.yrd.farm.entities.Terminal;
import com.yrd.farm.entities.User;
import com.yrd.farm.entities.UserUnion;
import com.yrd.farm.repository.NodeRepository;
import com.yrd.farm.repository.TerminalRepository;
import com.yrd.farm.repository.UserRepository;
import com.yrd.farm.repository.UserUnionRepository;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
public class DashboardController {

	@Autowired
	private TerminalRepository termialRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserUnionRepository unionRepo;
	
	@Autowired
	private NodeRepository nodeRepo;
	
	@RequestMapping("/getBaseInfo")
    @ResponseBody
    public JSONObject getBaseInfo(@RequestParam(value="userId", required=true) Integer id) {
		
		JSONObject result = new JSONObject();
		JSONArray terArr = new JSONArray();
		EntityToJson toJson = new EntityToJson();
		
		Optional<User> user = userRepo.findById(id);
		UserUnion union = new UserUnion();
		union.setUserId(user.get().getId().toString());
		Example<UserUnion> unionEx = Example.of(union);
	 	Optional<UserUnion> userUnion = unionRepo.findOne(unionEx);
	 	if(userUnion.isPresent()){
	 		result.put("user", toJson.objToJson(user.get(),userUnion.get()));
	 	}else{
	 		result.put("user", toJson.objToJson(user.get(),null));
	 	}
	 	
		Terminal ter = new Terminal();
		ter.setUserId(user.get().getId().toString());
		Example<Terminal> example = Example.of(ter);
		List<Terminal> terList = termialRepo.findAll(example);
		for (Terminal terminal : terList) {
			terArr.add(toJson.objToJson(terminal));
		}
		result.put("terminal", terArr);
		
        return result;
    }
	
	@RequestMapping("/getNodesInfo")
    @ResponseBody
    public JSONArray getNodesInfo(@RequestParam(value="terId", required=true) String id) {
		
		JSONArray nodeArr = new JSONArray();
		EntityToJson toJson = new EntityToJson();
		
		Node node = new Node();
		node.setTerminal(id);
		Example<Node> nEx = Example.of(node);
		List<Node> nodeList = nodeRepo.findAll(nEx);
		for (Node nodes : nodeList) {
			nodeArr.add(toJson.objToJson(nodes));
		}
		
		return nodeArr;
	}
	
	//页面请求
	@GetMapping("/socket/{cid}")
	public ModelAndView socket(@PathVariable String cid) {
		ModelAndView mav=new ModelAndView("/socket");
		mav.addObject("cid", cid);
		return mav;
	}
	//推送数据接口
	@RequestMapping("/socket/push")
	@ResponseBody
	public String pushToWeb(@RequestParam("cid") String cid,
			@RequestParam("message") String message) {  
		try {
			WebSocketServer.sendInfo(message,cid);
		} catch (IOException e) {
			e.printStackTrace();
			return (cid+"#"+e.getMessage());
		}  
		return cid+" 成功";
	} 
}


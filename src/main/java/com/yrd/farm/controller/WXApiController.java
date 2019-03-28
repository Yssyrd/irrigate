package com.yrd.farm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.yrd.farm.common.EntityToJson;
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
public class WXApiController {

	@Autowired
	private TerminalRepository termialRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserUnionRepository unionRepo;
	
	@Autowired
	private NodeRepository nodeRepo;
	
	@PostMapping("/wx/dev/allTerminal")
	public JSONObject allTerminal(String id) {
		
		JSONObject res = new JSONObject();
		
		if(id==null||id.trim().equals("")){
			res.put("code", "-1");
			res.put("msg", "标志编号不能为空");
			return res;
		}
		
		JSONArray terArr = new JSONArray();
		EntityToJson toJson = new EntityToJson();
		
		Terminal ter = new Terminal();
		ter.setUserId(id);
		Example<Terminal> example = Example.of(ter);
		List<Terminal> terList = termialRepo.findAll(example);
		if(terList.size()<0){
			res.put("code", "-1");
			res.put("msg", "查询不到数据");
			return res;
		}
		for (Terminal terminal : terList) {
			terArr.add(toJson.objToJson(terminal));
		}
		
		res.put("code", "200");
		res.put("msg", "数据调取成功");
		res.put("data", terArr);
		
		return res;
	}
	
	@PostMapping("/wx/dev/searchDev")
	public JSONObject searchDev(String name, String id) {
		
		JSONObject res = new JSONObject();
		
		return res;
	}
	
}

package com.yrd.farm.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.yrd.farm.common.HttpClientUtil;
import com.yrd.farm.entities.User;
import com.yrd.farm.entities.UserUnion;
import com.yrd.farm.repository.UserRepository;
import com.yrd.farm.repository.UserUnionRepository;

import net.sf.json.JSONObject;

@RestController
public class WXLoginController {
	
	@Autowired
	private UserUnionRepository unionService;
	
	@Autowired
	private UserRepository userService;

	@PostMapping("/wx/member/login")
	public JSONObject wxLogin(String data) {
		JSONObject res = new JSONObject();
		JSONObject obj = JSONObject.fromObject(data); 

		if(obj.getString("code")==null||obj.getString("code").equals("")||obj.getString("code").length()<1){
			res.put("code", "-1");
			res.put("msg", "需要code");
			return res;
		}
		String code = obj.getString("code");
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> param = new HashMap<>();
		param.put("appid", "wx544d45f6ce6ae390");
		param.put("secret", "3f87b3a3ba73d334995e575e0cc13295");
		param.put("js_code", code);
		param.put("grant_type", "authorization_code");
		
		String openId = HttpClientUtil.doGet(url, param);
		
		if(openId==null||openId.equals("")){
			res.put("code", "-1");
			res.put("msg", "调用微信出错");
			return res;
		}
		
		String nickName = obj.getString("nickname");
		String avatar = obj.getString("avatarUrl");
		String city = obj.getString("city");
		String gender = obj.getString("gender");
		
		User user = new User();
		UserUnion union = new UserUnion();
		
		user.setGender(gender);
		user.setNickName(nickName);
		user.setCity(city);
		
		user = userService.saveAndFlush(user);
		
		union.setOpenId(openId);
		union.setLoginDate(new Timestamp(new Date().getTime()));
		union.setUpdateDate(new Timestamp(new Date().getTime()));
		union.setUserId(user.getId().toString());
		union.setAvatar(avatar);
		
		unionService.saveAndFlush(union);
		
		res.put("code", "200");
		res.put("msg", "操作成功");
		res.put("data", user.getId().toString());
		return res;
	}
	
	@PostMapping("/wx/member/check-reg")
	public JSONObject wxCheck(String data) {
		
		System.out.println("bbbbbbbbbbbbbbbbb");
		JSONObject res = new JSONObject();
		
		res.put("code", "200");
		res.put("msg", "操作成功");
		
		return res;
	}
}

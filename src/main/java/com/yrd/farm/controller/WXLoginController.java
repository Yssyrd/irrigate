package com.yrd.farm.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.yrd.farm.common.HttpClientUtil;
import com.yrd.farm.entities.Terminal;
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
		res.put("data", new JSONObject());

		if(obj.getString("code")==null||obj.getString("code").equals("")||obj.getString("code").length()<1){
			res.put("code", "-1");
			res.put("msg", "需要code");
			return res;
		}
		String code = obj.getString("code");
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> param = new HashMap<>();
		param.put("appid", "wxd6cc9c2f1d963190");
		param.put("secret", "b8089dad0ee4fdc2437be73441d012eb");
		param.put("js_code", code);
		param.put("grant_type", "authorization_code");
		
		
		String resStr = HttpClientUtil.doGet(url, param);
		if(resStr==null||resStr.equals("")){
			res.put("code", "-1");
			res.put("msg", "调用微信出错");
			return res;
		}
		JSONObject resObj = JSONObject.fromObject(resStr);
		String openId = resObj.getString("openid");
		
		String nickName = obj.getString("nickName");
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
		
		JSONObject token = new JSONObject();
		token.put("token", user.getId().toString());
		res.put("code", "200");
		res.put("msg", "操作成功");
		res.put("data", token);
		return res;
	}
	
	@PostMapping("/wx/member/check-reg")
	public JSONObject wxCheck(String code) {
		System.out.println(code);
		JSONObject res = new JSONObject();
		res.put("data", new JSONObject());
		JSONObject obj = new JSONObject();
		
		if(code==null||code.equals("")||code.length()<1){
			res.put("code", "-1");
			res.put("msg", "需要code");
			return res;
		}
		
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> param = new HashMap<>();
		param.put("appid", "wxd6cc9c2f1d963190");
		param.put("secret", "b8089dad0ee4fdc2437be73441d012eb");
		param.put("js_code", code);
		param.put("grant_type", "authorization_code");
		
		String resStr = HttpClientUtil.doGet(url, param);
		if(resStr==null||resStr.equals("")){
			res.put("code", "-1");
			res.put("msg", "调用微信出错");
			return res;
		}
		System.out.println(resStr);
		JSONObject resObj = JSONObject.fromObject(resStr);
		String openId = resObj.getString("openid");
		UserUnion uu = new UserUnion();
		uu.setOpenId(openId);
		Example<UserUnion> example = Example.of(uu);
		Optional<UserUnion> one = unionService.findOne(example);
		if(!one.isPresent()){
			res.put("code", "-1");
			res.put("msg", "未绑定");
			return res;
		}
		uu = one.get();
		obj.put("token", uu.getUserId());
		System.out.println(uu);
		System.out.println(uu.getUserId());
		res.put("code", "200");
		res.put("msg", "操作成功");
		res.put("data", obj);
		return res;
	}
}

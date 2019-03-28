package com.yrd.farm.common;

import com.yrd.farm.entities.Node;
import com.yrd.farm.entities.Terminal;
import com.yrd.farm.entities.User;
import com.yrd.farm.entities.UserUnion;

import net.sf.json.JSONObject;

public class EntityToJson {

	public JSONObject objToJson(User entity,UserUnion union){

		JSONObject result = new JSONObject();
		
		if(entity!=null){
			JSONObject obj = new JSONObject();
			obj.put("id", entity.getId());
			obj.put("nick", entity.getNickName());
			obj.put("password", entity.getPassword());
			obj.put("phone", entity.getPhone());
			obj.put("status", entity.getStatus());
			obj.put("username", entity.getUsername());
			obj.put("auth", entity.getAuth());
			obj.put("gender", entity.getGender());
			result.put("base", obj);
		}
		
		if(union!=null){
			JSONObject unionObj = new JSONObject();
			unionObj.put("id", union.getId());
			unionObj.put("avatar", union.getAvatar());
			unionObj.put("openId", union.getOpenId());
			unionObj.put("type", union.getType());
			unionObj.put("updateDate", union.getUpdateDate().toString().substring(0, 19));
			unionObj.put("userId", union.getUserId());
			unionObj.put("loginDate", union.getLoginDate().toString().substring(0, 19));
			unionObj.put("unionId", union.getUnionId());
			result.put("union", unionObj);
		}
		
		return result;
	}
	
	public JSONObject objToJson(Terminal entity){
		JSONObject obj = new JSONObject();
		
		if(entity!=null){
			
			obj.put("id", entity.getId());
			obj.put("loraAddr", entity.getLoraAddr());
			obj.put("loraCh", entity.getLoraChannel());
			obj.put("name", entity.getName());
			obj.put("phyAdde", entity.getPhysicalAddr());
			obj.put("SN", entity.getSN());
			obj.put("version", entity.getVersion());
			obj.put("delFlag", entity.getDelFlag());
			obj.put("loginDate", entity.getLoginDate().toString().substring(0, 19));
			obj.put("proDate", entity.getProDate());
			obj.put("proId", entity.getProId());
			obj.put("userId", entity.getUserId());
			obj.put("status", entity.getStatus());
		}
		
		return obj;
	}
	
	public JSONObject objToJson(Node entity){
		JSONObject obj = new JSONObject();
		if(entity!=null){
			obj.put("ch", entity.getChannel());
			obj.put("delFlag", entity.getDelFlag());
			obj.put("id", entity.getId());
			obj.put("loginDate", entity.getLoginDate().toString().substring(0, 19));
			obj.put("addr", entity.getModuleAddr());
			obj.put("name", entity.getName());
			obj.put("nodeStatus", entity.getNodeStatus());
			obj.put("openStatus", entity.getOpenStatus());
			obj.put("proDate", entity.getProDate());
			obj.put("proId", entity.getProId());
			obj.put("site", entity.getSite());
			obj.put("SN", entity.getSN());
			obj.put("terminal", entity.getTerminal());
			obj.put("version", entity.getVersion());
			obj.put("lastUpdate", entity.getLastUpdate());
		}
		
		return obj;
	}
}

package com.yrd.farm.service.imp;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.yrd.farm.component.WebSocketServer;
import com.yrd.farm.config.Constant;
import com.yrd.farm.entities.Node;
import com.yrd.farm.entities.Terminal;
import com.yrd.farm.repository.NodeRepository;
import com.yrd.farm.repository.TerminalRepository;
import com.yrd.farm.service.MqttService;

import net.sf.json.JSONObject;

@Service
public class MqttServiceImp implements MqttService {

	@Autowired
	private Constant constant;
	@Autowired
	private TerminalRepository terminalRp;
	@Autowired
	private NodeRepository nodeRp;
	
	@Autowired
	private MqttService mqttService;
	
	@Override
	public void getCode(String topic, String msg) {
		
		if(topic==null||topic.equals("")){
			return;
		}
		
		String[] arr = topic.split("/");
		String user = "";
		String dev = "";
		if(arr.length>=4){
			
			if(arr[0].equals("user")){
				user = arr[1];
			}
			if(arr[0].equals("dev")){
				dev = arr[1];
			}
			if(arr[2].equals("user")){
				user = arr[3];
			}
			if(arr[2].equals("dev")){
				dev = arr[3];
			}
		}
		String res = analysisCode(msg,dev,user);
		
		if(res.equals("")){
			return;
		}
	}

	@Override
	public String analysisCode(String code,String dev, String user) {
		
		JSONObject obj = new JSONObject();
		String res = "";
		
		JSONObject skt = new JSONObject();
		skt.put("dev", dev);
		Node node = new Node();
		Terminal ter = new Terminal();
		String update = String.valueOf(new Timestamp(new Date().getTime()));
		if(dev.length()==32){
			node.setSN(dev);
			Example<Node> example = Example.of(node);
			Optional<Node> one = nodeRp.findOne(example);
			node = one.get();
		}
		if(dev.length()==16){
			ter.setSN(dev);
			Example<Terminal> terEx = Example.of(ter);
			Optional<Terminal> terOne = terminalRp.findOne(terEx);
			ter = terOne.get();
		}
		
		int str = 0;
		try {
			obj = JSONObject.fromObject(code);
			str = obj.getInt("code");
			skt.put("code", code);
		} catch (Exception e) {
			return res;
		}
		
		switch (str) {
			case 0xA0:
				
				break;
			case 0xA1:
				
				break;
			case 0xA2:
	
				break;
			case 0xA3:
				res = "NODE_OPEN_TAP_OK";
				
				node.setOpenStatus(res);
				node.setLastUpdate(update);
				nodeRp.save(node);
				skt.put("update", update);
				sendSocket(skt.toString(), user);
				break;
			case 0xA4:
				res = "NODE_CLOSE_TAP_OK";
				node.setOpenStatus(res);
				node.setLastUpdate(update);
				nodeRp.save(node);
				skt.put("update", update);
				sendSocket(skt.toString(), user);
				
				break;
			case 0xA5:
				res = "NODE_OPEN_TAP_ERR";
				node.setOpenStatus(res);
				node.setLastUpdate(update);
				nodeRp.save(node);
				skt.put("update", update);
				
				sendSocket(skt.toString(), user);
				break;
			case 0xA6:
				res = "NODE_CLOSE_TAP_ERR";
				node.setOpenStatus(res);
				node.setLastUpdate(update);
				nodeRp.save(node);
				skt.put("update", update);
				sendSocket(skt.toString(), user);
				break;
			case 0xA7:
				res = "NODE_OPENING_TAP";
				node.setOpenStatus(res);
				node.setLastUpdate(update);
				nodeRp.save(node);
				skt.put("update", update);
				sendSocket(skt.toString(), user);
				break;
			case 0xA8:
				res = "NODE_CLOSING_TAP";
				node.setOpenStatus(res);
				node.setLastUpdate(update);
				nodeRp.save(node);
				skt.put("update", update);
				sendSocket(skt.toString(), user);
				break;
			case 0xA9:
				
				break;
			case 0xC0:
//				res = "NODE_CONNECT";
				break;
			case 0xC1:
	
				break;
			case 0xC2:
				res = "NODE_ONLINE";
				if(dev.length()==32){
					node.setNodeStatus(res);
					node.setLastUpdate(update);
					nodeRp.save(node);
					skt.put("update", update);
					skt.put("type", "0");
					sendSocket(skt.toString(), user);
				}
				if(dev.length()==16){
					ter.setStatus(res);
					terminalRp.save(ter);
					skt.put("update", update);
					skt.put("type", "1");
					sendSocket(skt.toString(), user);
				}
				
				break;
			case 0xC3:
				res = "NODE_OFFLINE";
				if(dev.length()==32){
					node.setNodeStatus(res);
					node.setLastUpdate(update);
					nodeRp.save(node);
					skt.put("update", update);
					skt.put("type", "0");
					sendSocket(skt.toString(), user);
				}
				if(dev.length()==16){
					ter.setStatus(res);
					terminalRp.save(ter);
					skt.put("update", update);
					skt.put("type", "1");
					sendSocket(skt.toString(), user);
				}
				
				break;
			case 0xC4:
				
				break;
			case 0xC5:
	
				break;
			case 0xD0:
				res = "BIND_USER";
				break;
			case 0xD1:
//				res = "BIND_USER_OK";
				break;
			case 0xD2:
				
				break;
			case 0xD3:
	
				break;
			default:
				break;
		}
		
		return res;
	}

	@Override
	@SuppressWarnings("unchecked") 
	public Method getGetMethod(Class objectClass, String fieldName) {
		
		  StringBuffer sb = new StringBuffer();  
	      sb.append("get");  
	      sb.append(fieldName.substring(0, 1).toUpperCase());  
	      sb.append(fieldName.substring(1));  
	      try {  
	          return objectClass.getMethod(sb.toString());  
	      } catch (Exception e) {  
	      }  
	      return null;  
	}

	@Override
	public Object invokeGet(Object o, String fieldName) {
		Method method = getGetMethod(o.getClass(), fieldName);  
        try {  
            return method.invoke(o, new Object[0]);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
	}

	@Override
	public void sendSocket(String info, String user) {
		try {
			WebSocketServer.sendInfo(info,user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.dnf.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.dnf.model.User;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class UserController extends Controller{

	public void index(){
		//解决 post 跨域问题
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		
		//若不存在 session 则创建一个新的session
		
		System.out.println("request_2"+getRequest().getRequestURI());
		System.out.println(getRequest().getRemoteUser());
		
		HttpSession session = getSession();
		String sessionId = session.getId();
		if(session.isNew()){
			System.out.println("session 创建成功 , sessionId 为 " + sessionId);
		}else{
			System.out.println("服务器已经存在session , sessionId 为 " + sessionId);
		}
		
		
		Map<String, Object> map = getSessionAttr("user");
		
		List<Record> record = Db.find("SELECT * FROM goods WHERE type='"+ getPara("type") +"' and daqv='"+ getPara("daqv") +"' and server='"+ getPara("server") +"'");
		
		setAttr("map", map.get("name"));
		
		setAttr("records", record);
		
		renderJson();
	}
	
	
	public void registe(){
		//解决 post 跨域问题
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		
		
		User user = getModel(User.class);
		user.save();
		
		renderText("注册成功");
	}
	
	//登录
	public void login(){
		
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		
		System.out.println("request_1"+getRequest().getRequestURI());
		
		System.out.println(getRequest().getRemoteUser());
		
		HttpSession session = getSession();
		System.out.println("sessionId => " + session.getId());
		

		
		User user = getModel(User.class);
		
		System.out.println(user.getName());
		
		Record user_db = null;
		
		if(user.getName() != null && !user.getName().equals("")){
			try{
				user_db = Db.find("SELECT * FROM User user WHERE user.name = '" + user.getName().trim() + "'").get(0);
				
				Map<String, Object> map = user_db.getColumns();
				
				if(user_db != null && user.getPassword().equals(map.get("password"))){
					System.out.println("set session => " + map.get("name"));
					setSessionAttr("user", map);

					renderText(map.get("admin").toString());						
					
				}
				
			}catch(Exception e){
				renderText("登陆失败");	
			}
			
		}
		
	}
	
	public void test(){
		List<Record> list = Db.find("SELECT * FROM user");
		
		//setAttr("users", list);
		
		renderJson(list);
	}
}

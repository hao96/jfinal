package com.dnf.controller;


import javax.jws.soap.InitParam;

import com.dnf.model.Goods;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

public class GoodController extends Controller{

	public void input(){
		
		Goods good = getModel(Goods.class);
		
		good.save();
		renderText("录入成功");
	}
	
	public void find(){
				
		setAttr("records", Db.find("SELECT * FROM goods WHERE type='"+ getPara("type") +"' and daqv='"+ getPara("daqv") +"' and server='"+ getPara("server") +"'"));
		
		renderJson();
	}
	
	public void trade(){
		
		Db.update("UPDATE goods SET inventory=? WHERE id=?",Integer.parseInt(getPara("inventory"))-1,Integer.parseInt(getPara("id")));
		
		renderText("购买成功");
		
	}
}

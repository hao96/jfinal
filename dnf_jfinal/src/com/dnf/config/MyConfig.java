package com.dnf.config;

import com.dnf.controller.GoodController;
import com.dnf.controller.UserController;
import com.dnf.model.Goods;
import com.dnf.model.User;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

public class MyConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		me.setDevMode(true);
	}

	@Override
	public void configEngine(Engine arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins me) {
		//配置数据库 连接池
		DruidPlugin dp = new DruidPlugin("jdbc:mysql://localhost/dnf", "root", "root");
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		
		arp.addMapping("user", User.class);
		arp.addMapping("goods", Goods.class);
		
		me.add(dp);
		me.add(arp);
		
		
	}

	@Override
	public void configRoute(Routes me) {
		// 映射  ORM
		me.add("/user", UserController.class);
		me.add("/good", GoodController.class);
	}

}

package com.cubekrowd.net;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.cubekrowd.net.events.FishDrag;

public class Main extends JavaPlugin{
	public void onEnable(){
		reg_ev();
	}
	
	public void reg_ev(){
		PluginManager pl = getServer().getPluginManager();
		pl.registerEvents(new FishDrag(), this);
	}
}

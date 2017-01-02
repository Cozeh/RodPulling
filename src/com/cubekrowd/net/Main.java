package com.cubekrowd.net;


import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.cubekrowd.net.events.FishDrag;

public class Main extends JavaPlugin{
	
	
	public void onEnable(){
		reg_cmds();
		reg_ev();
		reg_cfg();
	}
	
	private void reg_cmds(){
		getCommand("fdrag").setExecutor(new CommandManager(this));
		getCommand("fdrag").setTabCompleter(new TabComplete());
		
	}
	
	private void reg_ev(){
		PluginManager pl = getServer().getPluginManager();
		pl.registerEvents(new FishDrag(this), this);
	}
	
	private void reg_cfg(){
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
}


package com.cubekrowd.net.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.cubekrowd.net.Main;

public class DragOnOff implements CommandExecutor {

	int i = 0, x = 0;

	private Main plugin;

	public DragOnOff(Main pl) {

		plugin = pl;

	}

	public boolean onCommand(CommandSender sender, Command cmd, String cmdstring, String[] args) {
		
		List<String> svadmins = plugin.getConfig().getStringList("Server Admins");
		x = plugin.getConfig().getInt("isOn");
		i = 0;
		
		if(sender instanceof ConsoleCommandSender){
			i = 1;
		}
		else{
			for(String admin : svadmins){
				if (sender.getName().equals(admin)){
					i = 1;
				}
			}
		}
		if(i == 1){ // I could have used swich here but I like this more
			if(args.length == 0){
				sender.sendMessage(ChatColor.RED + "The correct syntax is : /fdrag <value>");
				return true;
			}
			if(args[0].equalsIgnoreCase("on")){
				if(x == 1){
					sender.sendMessage(ChatColor.AQUA + "The plugin was already ON");
				}
				else{
					x = 1;
					plugin.getConfig().set("isOn", x);
					plugin.saveConfig();
					sender.sendMessage(ChatColor.AQUA + "The plugin is now ON");
				}
			}
			if(args[0].equalsIgnoreCase("off")){
				if(x == 0){
					sender.sendMessage(ChatColor.AQUA + "The plugin was already OFF");
				}
				else{
					x = 0;
					plugin.getConfig().set("isOn", x);
					plugin.saveConfig();
					sender.sendMessage(ChatColor.AQUA + "The plugin is now OFF");
				}
			}
			if(args[0].equalsIgnoreCase("add")){
				if(!(args.length == 1)){
					List<String> lst = plugin.getConfig().getStringList("Server Admins");
					lst.add(args[1]);
					plugin.getConfig().set("Server Admins", lst);
					plugin.saveConfig();
					sender.sendMessage(ChatColor.GREEN + "The player " + args[1] + " has been added to the admins list!");
				}
				else{
					sender.sendMessage(ChatColor.RED + "The correct syntax is : /fdrag add <name>");
				}
				
			}
			if(args[0].equalsIgnoreCase("remove")){
				if(!(args.length == 1)){
					List<String> lst = plugin.getConfig().getStringList("Server Admins");
					lst.remove(args[1]);
					plugin.getConfig().set("Server Admins", lst);
					plugin.saveConfig();
					sender.sendMessage(ChatColor.GREEN + "The player " + args[1] + " has been removed from the admins list!");
				}
				else{
					sender.sendMessage(ChatColor.RED + "The correct syntax is : /fdrag remove <name>");
				}
				
			}
		if(args[0].equalsIgnoreCase("mult")){
			if(isItInt(args[1])){
				plugin.getConfig().set("mult", args[1]);
				plugin.saveConfig();
				sender.sendMessage(ChatColor.GREEN + "The velocity has been multiplied by " + args[1] + "!");
			}
			else {
				sender.sendMessage(ChatColor.RED + "You did not enter a number!");
			}
		}
		}
		else{
			sender.sendMessage(ChatColor.DARK_RED + "You are not allowed to do that command!");
		}
		i = 0;
		
		return true;
	}
	
	public static boolean isItInt(String str) {
	    try {
	        Integer.parseInt(str);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

}

package com.cubekrowd.net;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.cubekrowd.net.commands.DragCommand;
import com.cubekrowd.net.commands.DragMult;
import com.cubekrowd.net.commands.DragOff;
import com.cubekrowd.net.commands.DragOn;
import com.cubekrowd.net.commands.DragType;

import net.md_5.bungee.api.ChatColor;

public class CommandManager implements CommandExecutor {

	private Main plugin;

	public CommandManager(Main pl) {
		plugin = pl;
		commands.add(new DragOn());
		commands.add(new DragOff());
		commands.add(new DragMult());
		commands.add(new DragType());
	}
	
	public static ArrayList<DragCommand> commands = new ArrayList<DragCommand>();

	public static ArrayList<DragCommand> showCommands(){
		return commands;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CmdStr, String[] args) {
		int isOn, mult;
		if (sender.hasPermission("fdrag.fdrag") || sender.isOp()) {
			if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(ChatColor.DARK_AQUA + "Available Commands:");
				for(DragCommand cm : commands) {
					sender.sendMessage(ChatColor.RED + "/fdrag " + cm.getName() + " " + cm.getArgs() + ChatColor.YELLOW
							+ " - " + ChatColor.AQUA + cm.getDescription());
				}
				return true;
			}
			isOn = plugin.getConfig().getInt("isOn");
			if (isOn > 1 || isOn < 0) isOn = 1;
			if (args[0].equalsIgnoreCase("on")) {
				if (args.length == 1) {
					if (isOn == 0) {
						plugin.getConfig().set("isOn", 1);
						plugin.saveConfig();
						sender.sendMessage(ChatColor.AQUA + "The plugin is now " + ChatColor.GOLD + "ON!");
					} else {
						sender.sendMessage(ChatColor.AQUA + "The plugin was already " + ChatColor.GOLD + "ON!");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "The correct syntax is: /fdrag on");
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("off")) {
				if (args.length == 1) {
					if (isOn == 1) {
						plugin.getConfig().set("isOn", 0);
						plugin.saveConfig();
						sender.sendMessage(ChatColor.AQUA + "The plugin is now " + ChatColor.GOLD + "OFF!");
					} else {
						sender.sendMessage(ChatColor.AQUA + "The plugin was already " + ChatColor.GOLD + "OFF!");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "The correct syntax is: /fdrag off");
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("mult")) {
				if (args.length == 1 || args.length > 2) {
					sender.sendMessage(ChatColor.RED + "The correct syntax is: /fdrag mult <number 1-5>");
					return true;
				} else if (args.length == 2) {
					if (isItInt(args[1])) {
						mult = Integer.parseInt(args[1]);
						int a = 1;
						if (mult > 5) {
							sender.sendMessage(ChatColor.RED + "The maximum value of the velocity you can set is 5!");
							sender.sendMessage(ChatColor.GREEN + "Setting the velocity to " + ChatColor.GOLD + "5.");
							mult = 5;
							a = 0;
						} else if (mult < 0) {
							sender.sendMessage(ChatColor.RED + "The minimum value of the velocity you can set is 0!");
							sender.sendMessage(ChatColor.GREEN + "Setting the velocity to " + ChatColor.GOLD + "0.");
							mult = 0;
							a = 0;
						}
						if (a == 1) {
							sender.sendMessage(
									ChatColor.GREEN + "The velocity is now set to: " + ChatColor.GOLD + args[1]);
						}
						plugin.getConfig().set("mult", mult);
						plugin.saveConfig();
						return true;
					}
					else{
						sender.sendMessage(ChatColor.RED + "The correct syntax is: /fdrag mult <number 1-5>");
						return true;
					}
				}

			}
			if(args[0].equalsIgnoreCase("type")){
				if(args.length > 2 || args.length == 1){
					sender.sendMessage(ChatColor.RED + "The correct syntax is: /fdrag type <normal|reversed>");
					return true;
				}
				int type = plugin.getConfig().getInt("type");
				if(!(type == 0) && !(type == 1)) type = 1;
				else if(args.length == 2){
					if(args[1].equalsIgnoreCase("normal")){
						if(type == 1){
							sender.sendMessage(ChatColor.AQUA + "The type was already set to:" + ChatColor.GOLD + " Normal.");
							return true;
						}
						else if(type == 0){
							plugin.getConfig().set("type", 1);
							plugin.saveConfig();
							sender.sendMessage(ChatColor.AQUA + "The type is now set to:" + ChatColor.GOLD + " Normal.");
							return true;
						}
					}
					else if(args[1].equalsIgnoreCase("reversed")){
						if(type == 0){
							sender.sendMessage(ChatColor.AQUA + "The type was already set to:" + ChatColor.GOLD + " Reversed.");
							return true;
						}
						else if(type == 1){
							plugin.getConfig().set("type", 0);
							plugin.saveConfig();
							sender.sendMessage(ChatColor.AQUA + "The type is now set to:" + ChatColor.GOLD + " Reversed.");
							return true;
						}
					}
					else if(!(args[1].equalsIgnoreCase("normal")) && !(args[1].equalsIgnoreCase("reversed"))){
						sender.sendMessage(ChatColor.RED + "The correct syntax is: /fdrag type <normal|reversed>");
						return true;
					}
				}
			}
			if(args.length >= 1){
				sender.sendMessage(ChatColor.RED + "Unknown command. Use /fdrag help.");
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.DARK_RED + "You do not have enough permissions!");
		}
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

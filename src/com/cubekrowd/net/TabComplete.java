package com.cubekrowd.net;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.cubekrowd.net.commands.DragCommand;

public class TabComplete implements TabCompleter{

	ArrayList<DragCommand> commands = CommandManager.showCommands();
	public ArrayList<String> tabs = new ArrayList<String>();
	public TabComplete(){
		tabs.add("normal");
		tabs.add("reversed");
	}
	
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String cmdStr, String[] args) {
		if(!(cmd.getName().equalsIgnoreCase("fdrag"))) return null;
		if(args.length == 1){
			ArrayList<String> cmds = new ArrayList<String>();
			for(DragCommand comms : commands){
				if(args[0] == ""){
					cmds.add(comms.getName());
				}
				if(comms.getName().toLowerCase().startsWith(args[0].toLowerCase())){
					cmds.add(comms.getName());
				}
			}
			return cmds;
		}
		if(args.length == 2){
			ArrayList<String> tabbing = new ArrayList<String>();
				for(String tab : tabs){
					if(args[1].equals("")) {tabbing.add(tab); continue;}
					if(tab.toLowerCase().startsWith(args[1].toLowerCase())) tabbing.add(tab);
				}
			return tabbing;
			}
			

		if(args.length >= 2){
			return null;
		}
		
		return null;
	}

}

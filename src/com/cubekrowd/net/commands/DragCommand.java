package com.cubekrowd.net.commands;

public abstract class DragCommand {

	private String name, desc, args;
	
	public DragCommand(String name, String desc, String args){
		this.name = name;
		this.desc = desc;
		this.args = args;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return desc;
	}
	
	public String getArgs() {
		return args;
	}
}
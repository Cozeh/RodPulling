package com.cubekrowd.net.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

import com.cubekrowd.net.Main;

public class FishDrag implements Listener {

	private Main plugin;
	
	public FishDrag(Main pl){	
		plugin = pl;
	}

	int isOn, mult, type;
	
	@EventHandler
	public void onFishDrag(PlayerFishEvent event) {
		isOn = plugin.getConfig().getInt("isOn");
		if((!(isOn == 0)) && (!(isOn == 1))){
			isOn = 1;
			plugin.getConfig().set("isOn", isOn);
			plugin.saveConfig();
		}
		
		mult = plugin.getConfig().getInt("mult");
		type = plugin.getConfig().getInt("type");
		
		
		if(isOn == 1){
				
		Player player = event.getPlayer();
		if (event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
			Entity entity = event.getCaught();
			if (entity instanceof LivingEntity) {
				if(type == 1){
					Vector dir = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
					dir.multiply(mult);
					dir.add(new Vector(0,1,0));
					player.setVelocity(dir);
				}
				else if(type == 0){
					Vector dir = player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize();
					dir.multiply(mult);
					dir.add(new Vector(0,1,0));
					entity.setVelocity(dir);
				}
			}
		}
	}
	}
}

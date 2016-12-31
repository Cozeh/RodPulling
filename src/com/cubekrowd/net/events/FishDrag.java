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

	int x = 0;
	
	@EventHandler
	public void onFishDrag(PlayerFishEvent event) {
		x = plugin.getConfig().getInt("isOn");
		if((!(x == 0)) && (!(x == 1))){
			x = 1;
			plugin.getConfig().set("isOn", x);
			plugin.saveConfig();
		}
		
		String mult = plugin.getConfig().getString("mult");
		int res = Integer.parseInt(mult);
		
		if(res > 5 || res < 1){
			res = 2;
		}
		
		plugin.getConfig().set("mult", res);
		
		if(x == 1){
				
		Player player = event.getPlayer();
		if (event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
			Entity entity = event.getCaught();
			if (entity instanceof LivingEntity) {
				Vector dir = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
				dir.multiply(res);
				dir.add(new Vector(0,1,0));
				player.setVelocity(dir);
			}
		}
	}
	}
}

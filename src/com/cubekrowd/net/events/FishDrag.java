package com.cubekrowd.net.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class FishDrag implements Listener {

	@EventHandler
	public void onFishDrag(PlayerFishEvent event) {
		Player player = event.getPlayer();
		if (event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
			Entity entity = event.getCaught();
			if (entity instanceof LivingEntity) {
				Vector dir = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
				dir.multiply(2);
				dir.add(new Vector(0,1,0));
				player.setVelocity(dir);
			}
		}
	}

}

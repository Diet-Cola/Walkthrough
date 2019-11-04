package com.github.diet.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.diet.Walkthrough;
import com.github.diet.utils.AdvancementUtils;

public class PlayerListener implements Listener {
    
	@EventHandler
	public void onPlayerChat (AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		Bukkit.getScheduler().runTask(Walkthrough.getInstance(), () -> {
			AdvancementUtils.grantAdvancement("main/localChat", p);
		});
	}
	@EventHandler
	public void onItemPickup (EntityPickupItemEvent e) {
		if (EntityType.PLAYER.equals(e.getEntityType())) {
		    Player p = (Player) e.getEntity();
		    //Get Wood Advancement
		    if (e.getItem().getItemStack().getType() == Material.OAK_LOG && AdvancementUtils.hasAdvancement("main/localChat", p)) {
		    	AdvancementUtils.grantAdvancement("main/getWood", p);
		    }
		}
	}
}

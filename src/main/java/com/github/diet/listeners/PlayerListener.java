package com.github.diet.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
}

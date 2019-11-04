package com.github.diet.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.devotedmc.hiddenore.events.HiddenOreGenerateEvent;
import com.github.diet.utils.AdvancementUtils;

public class HiddenOreListener implements Listener {

	@EventHandler
	public void onOreGenerate (HiddenOreGenerateEvent e) {
		Player p = e.getPlayer();
		if (AdvancementUtils.hasAdvancement("main/localChat", p)) {
			AdvancementUtils.grantAdvancement("main/goMining", p);
		}
	}	
}

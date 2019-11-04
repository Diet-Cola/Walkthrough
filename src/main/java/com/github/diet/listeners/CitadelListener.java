package com.github.diet.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.github.diet.utils.AdvancementUtils;

import vg.civcraft.mc.citadel.events.ReinforcementCreationEvent;


public class CitadelListener implements Listener {
	
	public void onReinforcement (ReinforcementCreationEvent e) {
		Player p = e.getPlayer();
		if (AdvancementUtils.hasAdvancement("main/localChat", p)); {
			AdvancementUtils.grantAdvancement("main/reinforce", p);
		}
	}
}

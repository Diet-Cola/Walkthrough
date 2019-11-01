package com.github.diet.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.diet.Walkthrough;

public class AdvancementUtils {
	public static void grantAdvancement(String key, Player player){
		NamespacedKey nsk = new NamespacedKey(Walkthrough.getInstance(), key);
		Advancement adv = Walkthrough.getInstance().getServer().getAdvancement(nsk);
    
		if(adv != null){
			AdvancementProgress progress = player.getAdvancementProgress(adv);
			if (!progress.isDone()) {
				for(String criteria : progress.getRemainingCriteria()){
					progress.awardCriteria(criteria);
				}
			}
		}
	}
	public static Advancement getAdvancement(JavaPlugin plugin, String key){
	    NamespacedKey nsk = new NamespacedKey(plugin, key);
	    Advancement adv = plugin.getServer().getAdvancement(nsk);
	    return adv;
	}

	public static boolean advancementExists(JavaPlugin plugin, String key){
	    return getAdvancement(plugin, key) != null;
	}
	
	public static boolean hasAdvancement(String key, Player p) {
		NamespacedKey nsk = new NamespacedKey(Walkthrough.getInstance(), key);
		Advancement adv = Walkthrough.getInstance().getServer().getAdvancement(nsk);
		if (p.getAdvancementProgress(adv).isDone()) {
			return true;
		} else {
			return false;			
		}

	}
};
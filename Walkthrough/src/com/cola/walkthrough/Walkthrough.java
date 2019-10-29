package com.cola.walkthrough;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import hu.trigary.advancementcreator.Advancement;
import hu.trigary.advancementcreator.AdvancementFactory;
import listeners.PlayerListener;
import utils.WalkthroughSettingsManager;
import vg.civcraft.mc.civmodcore.ACivMod;

public class Walkthrough extends ACivMod {
	private static Walkthrough instance;
	
	private WalkthroughSettingsManager settingsManager;
	
	protected String getPluginName() {
		return "Walkthrough";
	}
	public static Walkthrough getInstance() {
		return instance;
	}
	public WalkthroughSettingsManager getSettingsManager() {
		return settingsManager;
	}
	public void onEnable() {
		super.onEnable();
		instance = this;
		if (!Bukkit.getPluginManager().isPluginEnabled("CivModCore")) {
			getLogger().info("CivModCore not enabled, shutting down");
			Bukkit.shutdown();
		}
		getLogger().info("Walkthrough enabled");
		registerListener(new PlayerListener());
		AdvancementFactory factory = new AdvancementFactory(Walkthrough.getInstance(), true, false);
		
		Advancement root = factory.getRoot("main/root", "Welcome to CivClassic!", "You've joined for the first time", Material.GRASS, "blocks/dirt");
		@SuppressWarnings("unused")
		Advancement localChat = factory.getImpossible("main/localChat", root, "Only few can hear you...", "Chat range is limited to 1,000 blocks here.", Material.BOOK);
		Bukkit.reloadData();
	}
	
	public void onDisable() {
		Bukkit.shutdown();
	}
}

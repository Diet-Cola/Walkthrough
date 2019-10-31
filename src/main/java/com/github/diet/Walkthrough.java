package com.github.diet;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import com.github.diet.listeners.PlayerListener;
import com.github.diet.utils.WalkthroughSettingsManager;

import hu.trigary.advancementcreator.Advancement;
import hu.trigary.advancementcreator.AdvancementFactory;
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
	@Override
	public void onEnable() {
		super.onEnable();
		instance = this;
		if (!Bukkit.getPluginManager().isPluginEnabled("CivModCore")) {
			getLogger().info("CivModCore not enabled, shutting down");
			Bukkit.shutdown();
		}
		//Registering all advancements
		AdvancementFactory factory = new AdvancementFactory(Walkthrough.getInstance(), true, false);
		Advancement mainRoot = factory.getRoot("main/root", "Welcome to CivClassic!", "You've joined for the first time", Material.GRASS, "gui/advancements/backgrounds/stone");
		Advancement localChat = factory.getImpossible("main/localChat", mainRoot, "Only few can hear you...", "Chat range is limited to 1,000 blocks here.", Material.BOOK);
		Advancement getWood = factory.getImpossible("main/getWood", localChat, "Things grow differently here", "Trees and crops grow differently by biome. Try right clicking the ground with a sapling.", Material.OAK_LOG);
		Advancement goMining = factory.getImpossible("main/goMining", localChat, "You found an ore!", "Ore generates as you mine stone!", Material.DIAMOND_ORE);
		registerListener(new PlayerListener());
		settingsManager = new WalkthroughSettingsManager();
		getLogger().info("Walkthrough enabled");
		//Required to stop advancements overlapping each other.
		Bukkit.reloadData();
	}
	@Override
	public void onDisable() {
		Bukkit.shutdown();
	}
}

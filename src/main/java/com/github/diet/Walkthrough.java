package com.github.diet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import com.github.diet.listeners.PlayerListener;
import com.github.diet.utils.WalkthroughSettingsManager;

import hu.trigary.advancementcreator.Advancement;
import hu.trigary.advancementcreator.AdvancementFactory;
import hu.trigary.advancementcreator.shared.ItemObject;
import hu.trigary.advancementcreator.trigger.ImpossibleTrigger;
import net.md_5.bungee.api.chat.TextComponent;
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
		getLogger().info("Walkthrough enabled");
		//Registering all advancements
		AdvancementFactory factory = new AdvancementFactory(this, true, false);
		Advancement root = factory.getRoot("main/root", "Welcome to CivClassic!", "You've joined for the first time", Material.GRASS, "gui/advancements/backgrounds/stone");
		new Advancement(new NamespacedKey(this, "main/localChat"), new ItemObject().setItem(Material.BOOK),
                new TextComponent("Only few can hear you..."), new TextComponent("Chat range is limited to 1,000 blocks here."))
                .addTrigger("impossible", new ImpossibleTrigger())
                .makeChild(root.getId())
                .setFrame(Advancement.Frame.GOAL)
                .activate(false);
		registerListener(new PlayerListener());
		settingsManager = new WalkthroughSettingsManager();
		//Required to stop advancements overlapping each other.
		Bukkit.reloadData();
	}
	@Override
	public void onDisable() {
		Bukkit.shutdown();
	}
}

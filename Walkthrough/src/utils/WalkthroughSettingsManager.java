package utils;

import java.util.UUID;

import com.cola.walkthrough.Walkthrough;

import vg.civcraft.mc.civmodcore.playersettings.PlayerSettingAPI;
import vg.civcraft.mc.civmodcore.playersettings.gui.MenuSection;
import vg.civcraft.mc.civmodcore.playersettings.impl.BooleanSetting;

public class WalkthroughSettingsManager {

	private BooleanSetting tutorialState;
	
	public WalkthroughSettingsManager() {
		initSettings();
	}
	
	private void initSettings() {
		MenuSection menu = PlayerSettingAPI.getMainMenu().createMenuSection("Walkthrough", "Tutorial Settings");
		tutorialState = new BooleanSetting(Walkthrough.getInstance(), false, "Tutorial Toggle", "advancement grant everything", "Enable/Disable Tutorial");
		PlayerSettingAPI.registerSetting(tutorialState, menu);
	}
	
	public boolean tutorialState(UUID uuid) {
		return tutorialState.getValue(uuid);
	}
	
}

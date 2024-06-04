package net.trollblox.superrecipes;

import net.fabricmc.api.ModInitializer;

import net.trollblox.superrecipes.util.SuperLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuperRecipes implements ModInitializer {
	public static final String MOD_ID = "superrecipes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loading Super Recipes...");

		SuperLootTableModifiers.modifyLootTables();
	}
}
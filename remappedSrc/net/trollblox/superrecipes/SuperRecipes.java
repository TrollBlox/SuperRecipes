package net.trollblox.superrecipes;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.stat.Stats;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.VillagerData;
import net.trollblox.superrecipes.config.SuperConfigs;
import net.trollblox.superrecipes.listeners.HopperSpeedToggleListener;
import net.trollblox.superrecipes.util.SuperLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.trollblox.superrecipes.Constants.MOD_ID;

public class SuperRecipes implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loading Super Recipes...");

		SuperConfigs.registerConfigs();

		SuperLootTableModifiers.modifyLootTables();

		HopperSpeedToggleListener.init();
	}
}
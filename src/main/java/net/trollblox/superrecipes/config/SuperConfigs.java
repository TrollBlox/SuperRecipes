package net.trollblox.superrecipes.config;

import com.mojang.datafixers.util.Pair;
import net.trollblox.superrecipes.SuperRecipes;

public class SuperConfigs {
    public static SimpleConfig CONFIG;
    private static SuperConfigProvider configs;

    public static int HOPPER_TICK_DELAY;
    public static float ANCIENT_DEBRIS_DROP_RATE;

    public static void registerConfigs() {
        configs = new SuperConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(SuperRecipes.MOD_ID + "-config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("hopper-tick-delay", 0), "Delay in tick for hopper transfer speed");
        configs.addKeyValuePair(new Pair<>("ancient-debris-droprate", 0.005f), "Drop chance for ancient debris from Zombified Piglins");
    }

    private static void assignConfigs() {
        HOPPER_TICK_DELAY = CONFIG.getOrDefault("hopper-tick-delay", 0);
        SuperRecipes.LOGGER.info("Assigned HOPPER_TICK_DELAY to " + HOPPER_TICK_DELAY);
        ANCIENT_DEBRIS_DROP_RATE = (float) CONFIG.getOrDefault("ancient-debris-droprate", 0.005f);
        SuperRecipes.LOGGER.info("Assigned ANCIENT_DEBRIS_DROP_RATE to " + ANCIENT_DEBRIS_DROP_RATE);
    }
}

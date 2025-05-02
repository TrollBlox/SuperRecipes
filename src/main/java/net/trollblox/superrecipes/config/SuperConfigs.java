package net.trollblox.superrecipes.config;

import com.mojang.datafixers.util.Pair;
import net.trollblox.superrecipes.Constants;
import net.trollblox.superrecipes.SuperRecipes;

public class SuperConfigs {
    public static SimpleConfig CONFIG;
    private static SuperConfigProvider configs;

    public static int HOPPER_TICK_DELAY;
    public static float ANCIENT_DEBRIS_DROP_RATE;
    public static String HOPPER_TOGGLE_ITEM;
    public static boolean CHANNELLING_AFFECTS_WEATHER;

    public static void registerConfigs() {
        configs = new SuperConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Constants.MOD_ID).provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("hopper-tick-delay", 0), "Delay in tick for hopper transfer speed");
        configs.addKeyValuePair(new Pair<>("ancient-debris-droprate", 0.005f), "Drop chance for ancient debris from Zombified Piglins");
        configs.addKeyValuePair(new Pair<>("hopper-toggle-item", "wheat_seeds"), "Item held when opening a hopper to toggle speed");
        configs.addKeyValuePair(new Pair<>("channelling-affects-weather", true), "Channelling Tridents start thunderstorms");
    }

    private static void assignConfigs() {
        HOPPER_TICK_DELAY = CONFIG.getOrDefault("hopper-tick-delay", 0);
        SuperRecipes.LOGGER.info("Assigned HOPPER_TICK_DELAY to {}", HOPPER_TICK_DELAY);
        ANCIENT_DEBRIS_DROP_RATE = (float) CONFIG.getOrDefault("ancient-debris-droprate", 0.005f);
        SuperRecipes.LOGGER.info("Assigned ANCIENT_DEBRIS_DROP_RATE to {}", ANCIENT_DEBRIS_DROP_RATE);
        HOPPER_TOGGLE_ITEM = CONFIG.getOrDefault("hopper-toggle-item", "wheat_seeds");
        SuperRecipes.LOGGER.info("Assigned HOPPER_TOGGLE_ITEM to {}", HOPPER_TOGGLE_ITEM);
        CHANNELLING_AFFECTS_WEATHER = CONFIG.getOrDefault("channelling-affects-weather", true);
        SuperRecipes.LOGGER.info("Assigned CHANNELLING_AFFECTS_WEATHER to {}", CHANNELLING_AFFECTS_WEATHER);
    }
}

package net.trollblox.superrecipes.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.trollblox.superrecipes.config.SuperConfigs;

public class SuperLootTableModifiers {
    public static final Identifier ZOMBIFIED_PIGLIN_ID = Identifier.of("entities/zombified_piglin");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && key.getValue().equals(ZOMBIFIED_PIGLIN_ID)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .conditionally(RandomChanceLootCondition.builder(SuperConfigs.ANCIENT_DEBRIS_DROP_RATE))
                        .with(ItemEntry.builder(Items.ANCIENT_DEBRIS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}

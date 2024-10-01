package net.trollblox.superrecipes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.property.Properties;
import java.util.HashMap;
import java.util.Map;

public class DispenserCauldronHelper {
    public static HashMap<Item, HashMap<String, Object>> BucketToCauldron = new HashMap<>() {
        {
            put(Items.LAVA_BUCKET, new HashMap<>() {
                {
                    put("block", Blocks.LAVA_CAULDRON);
                    put("block_state", Blocks.LAVA_CAULDRON.getDefaultState());
                    put("has_levels", false);
                }
            });

            put(Items.WATER_BUCKET, new HashMap<>() {
                {
                    put("block", Blocks.WATER_CAULDRON);
                    put("block_state", Blocks.WATER_CAULDRON.getDefaultState().with(Properties.LEVEL_3, 3));
                    put("has_levels", true);
                }
            });

            put(Items.POWDER_SNOW_BUCKET, new HashMap<>() {
                {
                    put("block", Blocks.POWDER_SNOW_CAULDRON);
                    put("block_state", Blocks.POWDER_SNOW_CAULDRON.getDefaultState().with(Properties.LEVEL_3, 3));
                    put("has_levels", true);
                }
            });
        }
    };

    public static HashMap<Block, Item> CauldronToBucket = new HashMap<>(){
        {
            for (Map.Entry<Item, HashMap<String, Object>> entry : BucketToCauldron.entrySet()) {
                put((Block)entry.getValue().get("block"), entry.getKey());
            }
        }
    };

    public enum CauldronLevel {
        EMPTY(0), LOW(1), MEDIUM(2), FULL(3);

        private final int value;
        CauldronLevel(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static CauldronLevel getCauldronLevel(BlockState block_state) {
        Block block = block_state.getBlock();
        if (!CauldronToBucket.containsKey(block)) { return CauldronLevel.EMPTY; }
        if (block == Blocks.CAULDRON) { return CauldronLevel.EMPTY; }

        HashMap<String, Object> cauldron = BucketToCauldron.get(CauldronToBucket.get(block));
        if (!((Boolean) cauldron.get("has_levels"))) { return CauldronLevel.FULL; }

        return switch (block_state.get(Properties.LEVEL_3)) {
            case 3 -> CauldronLevel.FULL;
            case 2 -> CauldronLevel.MEDIUM;
            case 1 -> CauldronLevel.LOW;
            default -> CauldronLevel.EMPTY;
        };
    }
}

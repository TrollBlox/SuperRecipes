package net.trollblox.superrecipes.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trollblox.superrecipes.SlowHopper;
import net.trollblox.superrecipes.SuperRecipes;
import net.trollblox.superrecipes.config.SuperConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlock.class)
public class HopperSpeedToggleMixin {
    @Inject(at = @At("TAIL"), method = "onUse")
    private void overrideOnUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> info) {
        if (player.getStackInHand(player.preferredHand).getItem().equals(Registries.ITEM.get(Identifier.of(SuperConfigs.HOPPER_TOGGLE_ITEM)))) {
            SlowHopper blockEntity = (SlowHopper) world.getBlockEntity(pos);
            blockEntity.super_recipes_1_21$setSlowHopper(!blockEntity.super_recipes_1_21$getSlowHopper());
            String text = "Set hopper at (X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ() + ") to " + (blockEntity.super_recipes_1_21$getSlowHopper() ? "slow" : "fast") + ".";
            player.sendMessage(Text.of(text));
            SuperRecipes.LOGGER.info("{} {}", player.getName().toString(), text.replace('S', 's'));
        }
    }

}

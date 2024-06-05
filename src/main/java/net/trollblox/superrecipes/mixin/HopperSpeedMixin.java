package net.trollblox.superrecipes.mixin;

import net.minecraft.block.entity.HopperBlockEntity;
import net.trollblox.superrecipes.SuperRecipes;
import net.trollblox.superrecipes.config.SuperConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperBlockEntity.class)
public class HopperSpeedMixin {

    @Shadow
    private int transferCooldown;

    @Inject(at = @At("TAIL"), method = "setTransferCooldown")
    private void overrideNeedsCooldown(int transferCooldown, CallbackInfo info) {
        int cooldown = transferCooldown - (8 - SuperConfigs.HOPPER_TICK_DELAY);
        this.transferCooldown = cooldown;
    }
}

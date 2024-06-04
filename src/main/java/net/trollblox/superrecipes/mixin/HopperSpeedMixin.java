package net.trollblox.superrecipes.mixin;

import net.minecraft.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public class HopperSpeedMixin {

    @Inject(at = @At("HEAD"), method = "needsCooldown", cancellable = true)
    private void overrideNeedsCooldown(CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(false);
    }
}

package net.trollblox.superrecipes.mixin;

import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.trollblox.superrecipes.SlowHopper;
import net.trollblox.superrecipes.config.SuperConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperBlockEntity.class)
public class HopperSpeedMixin implements SlowHopper {
    @Unique
    private boolean slowHopper;

    @Shadow
    private int transferCooldown;

    @Unique
    @Override
    public void super_recipes_1_21$setSlowHopper(boolean slowHopper) {
        this.slowHopper = slowHopper;
    }

    @Unique
    @Override
    public boolean super_recipes_1_21$getSlowHopper() {
        return slowHopper;
    }

    @Inject(at = @At("TAIL"), method = "setTransferCooldown")
    private void overrideNeedsCooldown(int transferCooldown, CallbackInfo info) {
        if (slowHopper) {
            return;
        }
        this.transferCooldown = transferCooldown - (8 - SuperConfigs.HOPPER_TICK_DELAY);
    }

    @Inject(at = @At("TAIL"), method = "writeNbt")
    private void overrideWriteNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup, CallbackInfo info) {
        nbt.putBoolean("SlowHopper", slowHopper);
    }

    @Inject(at = @At("TAIL"), method = "readNbt")
    private void overrideReadNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup, CallbackInfo info) {
        slowHopper = nbt.contains("SlowHopper") && nbt.getBoolean("SlowHopper");
    }

}

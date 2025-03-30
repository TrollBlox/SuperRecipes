package net.trollblox.superrecipes.mixin;

import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.trollblox.superrecipes.Constants;
import net.trollblox.superrecipes.HopperSpeedData;
import net.trollblox.superrecipes.config.SuperConfigs;
import net.trollblox.superrecipes.enums.HopperSpeed;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperBlockEntity.class)
public class HopperSpeedMixin implements HopperSpeedData {
    @Unique
    private HopperSpeed hopperSpeed;

    @Shadow
    private int transferCooldown;

    @Unique
    public void super_recipes_1_21$setHopperSpeed(HopperSpeed slowHopper) {
        this.hopperSpeed = slowHopper;
    }

    @Unique
    public HopperSpeed super_recipes_1_21$getHopperSpeed() {
        return (hopperSpeed == null ? HopperSpeed.MODDED : hopperSpeed);
    }

    @Inject(at = @At("TAIL"), method = "setTransferCooldown")
    private void overrideNeedsCooldown(int transferCooldown, CallbackInfo info) {
        if (hopperSpeed == HopperSpeed.VANILLA) {
            return;
        }
        this.transferCooldown = transferCooldown - (8 - SuperConfigs.HOPPER_TICK_DELAY);
    }

    @Inject(at = @At("TAIL"), method = "writeNbt")
    private void overrideWriteNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup, CallbackInfo info) {
        nbt.putBoolean(Constants.HOPPER_SPEED_NBT_ID, hopperSpeed == HopperSpeed.MODDED);
    }

    @Inject(at = @At("TAIL"), method = "readNbt")
    private void overrideReadNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup, CallbackInfo info) {
        // Check old key value in nbt and remove it
        if (nbt.contains(Constants.HOPPER_SPEED_NBT_ID_OLD)) {
            hopperSpeed = HopperSpeed.getHopperSpeedFromValue(nbt.getBoolean(Constants.HOPPER_SPEED_NBT_ID_OLD));
            nbt.remove(Constants.HOPPER_SPEED_NBT_ID_OLD);
            return;
        }
        if (nbt.contains(Constants.HOPPER_SPEED_NBT_ID)) {
            hopperSpeed = HopperSpeed.getHopperSpeedFromValue(nbt.getBoolean(Constants.HOPPER_SPEED_NBT_ID));
            return;
        }

        hopperSpeed = HopperSpeed.MODDED;

    }

}

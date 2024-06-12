package net.trollblox.superrecipes.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mixin(BeaconBlockEntity.class)
public class BeaconEffectsMixin {
    @Mutable
    @Shadow
    public static List<List<RegistryEntry<StatusEffect>>> EFFECTS_BY_LEVEL;

    @Mutable
    @Shadow
    private static Set<RegistryEntry<StatusEffect>> EFFECTS;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void overrideBeaconEffects(CallbackInfo info) {
        EFFECTS_BY_LEVEL = List.of(List.of(StatusEffects.SPEED, StatusEffects.HASTE),
                List.of(StatusEffects.RESISTANCE, StatusEffects.JUMP_BOOST),
                List.of(StatusEffects.STRENGTH, StatusEffects.HEALTH_BOOST),
                List.of(StatusEffects.REGENERATION, StatusEffects.NIGHT_VISION, StatusEffects.SATURATION));
        EFFECTS = EFFECTS_BY_LEVEL.stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }

}

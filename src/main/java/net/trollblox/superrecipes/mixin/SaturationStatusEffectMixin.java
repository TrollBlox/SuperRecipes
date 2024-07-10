package net.trollblox.superrecipes.mixin;

import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net.minecraft.entity.effect.SaturationStatusEffect")
public class SaturationStatusEffectMixin extends InstantStatusEffect {
    public SaturationStatusEffectMixin(StatusEffectCategory statusEffectCategory, int i) {
        super(statusEffectCategory, i);
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 100 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        }
        return true;
    }
}

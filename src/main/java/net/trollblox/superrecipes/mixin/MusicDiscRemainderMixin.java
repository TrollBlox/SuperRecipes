package net.trollblox.superrecipes.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(Item.class)
public class MusicDiscRemainderMixin {

    @Mutable
    @Shadow
    private Item recipeRemainder;
    @Inject(at = @At("TAIL"), method = "<init>")
    private void overrideSetRecipeRemainder(CallbackInfo info) {
        if ((Object) this instanceof MusicDiscItem) {
            recipeRemainder = ((MusicDiscItem) (Object) this);
        }
    }

}
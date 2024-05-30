package net.trollblox.superrecipes.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class MusicDiscRemainderMixin {
    @Inject(at = @At("HEAD"), method = "getRecipeRemainder", cancellable = true)
    protected void overrideGetRecipeRemainder(CallbackInfoReturnable<Item> info) {
        if ((Object) this instanceof MusicDiscItem) {
            info.setReturnValue((MusicDiscItem) (Object)this);
        }
    }
    @Inject(at = @At("HEAD"), method = "hasRecipeRemainder", cancellable = true)
    protected void overrideHasRecipeRemainder(CallbackInfoReturnable<Boolean> info) {
        if ((Object) this instanceof MusicDiscItem) {
            info.setReturnValue(true);
        }
    }
}
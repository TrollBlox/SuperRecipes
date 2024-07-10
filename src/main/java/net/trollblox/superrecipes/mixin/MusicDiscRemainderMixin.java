package net.trollblox.superrecipes.mixin;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(Item.class)
public abstract class MusicDiscRemainderMixin {

    @Final
    @Mutable
    @Shadow
    private Item recipeRemainder;

    @Shadow @Final private ComponentMap components;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void overrideSetRecipeRemainder(CallbackInfo info) {
        if (this.components.getOrDefault(DataComponentTypes.JUKEBOX_PLAYABLE, null) != null) {
            recipeRemainder = ((Item) (Object) this);
        }
    }

}
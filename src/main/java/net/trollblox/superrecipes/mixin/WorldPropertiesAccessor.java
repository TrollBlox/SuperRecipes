package net.trollblox.superrecipes.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.level.ServerWorldProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerWorld.class)
public interface WorldPropertiesAccessor {
    @Accessor
    ServerWorldProperties getWorldProperties();
}

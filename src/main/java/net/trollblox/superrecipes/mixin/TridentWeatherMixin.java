package net.trollblox.superrecipes.mixin;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.level.ServerWorldProperties;
import net.trollblox.superrecipes.SuperRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TridentEntity.class)
public abstract class TridentWeatherMixin extends Entity {
    @Shadow public abstract ItemStack getWeaponStack();
    @Shadow private boolean dealtDamage;

    @Unique
    private ItemStack itemStack;

    @Unique
    private void changeWeather() {
        if (itemStack == null) return;
        if (!itemStack.getEnchantments().getEnchantments().contains(this.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getOptional(Enchantments.CHANNELING).orElse(null))) return;
        if (!this.getWorld().isClient) {
            ServerWorld overworld = this.getServer().getOverworld();
            ServerWorldProperties properties = ((WorldPropertiesAccessor) overworld).getWorldProperties();
            Entity bolt = new LightningEntity(EntityType.LIGHTNING_BOLT, overworld);
            bolt.setPosition(this.getPos());
            overworld.spawnEntity(bolt);
            if (!properties.isThundering()) {
                overworld.setWeather(0, ServerWorld.THUNDER_WEATHER_DURATION_PROVIDER.get(overworld.getRandom()), true, true);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onEntityHit")
    private void overrideEntityHit(EntityHitResult entityHitResult, CallbackInfo info) {
        changeWeather();
    }

    @Inject(at = @At("HEAD"), method = "onBlockHitEnchantmentEffects")
    private void overrideBlockHit(ServerWorld world, BlockHitResult blockHitResult, ItemStack weaponStack, CallbackInfo info) {
        if (!dealtDamage) {
            changeWeather();
        }
    }

    public TridentWeatherMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("TAIL"), method = "<init>*")
    private void overrideConstructor(World world, LivingEntity owner, ItemStack stack, CallbackInfo info) {
        itemStack = stack;
    }
}

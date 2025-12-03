package net.tuna.experiment_three.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record HemorrhageEnchantmentEffect(LevelBasedValue enchantLevel) implements EnchantmentEntityEffect {
    public static final MapCodec<HemorrhageEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    LevelBasedValue.CODEC.fieldOf("enchantLevel").forGetter(HemorrhageEnchantmentEffect::enchantLevel)
            ).apply(instance, HemorrhageEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerLevel world, int enchantLevel, EnchantedItemInUse enchantedItemInUse, Entity target, Vec3 vec3) {
        if(target instanceof LivingEntity victim) {
            if(enchantedItemInUse.owner() != null && enchantedItemInUse.owner() instanceof Player player) {
                //TODO: Hemorrhage logic
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}

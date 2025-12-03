package net.tuna.experiment_three.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.tuna.experiment_three.ExperimentThree;

public class ModEnchantmentEffects {
    public static final ResourceKey<Enchantment> THUNDERING = of("thundering");
    public static final MapCodec<LightningEnchantmentEffect> LIGHTNING_EFFECT = register("lightning_effect", LightningEnchantmentEffect.CODEC);

    public static final ResourceKey<Enchantment> HEMORRHAGE = of("hemorrhage");
    public static final MapCodec<HemorrhageEnchantmentEffect> HEMORRHAGE_EFFECT = register("hemorrhage_effect", HemorrhageEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(
                BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
                ResourceLocation.fromNamespaceAndPath(ExperimentThree.MOD_ID, id),
                codec
        );
    }

    private static ResourceKey<Enchantment> of(String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ExperimentThree.MOD_ID, path);
        return ResourceKey.create(Registries.ENCHANTMENT, id);
    }

    public static void registerModEnchantmentEffects() {
        ExperimentThree.LOGGER.info("Registering EnchantmentEffects for" + ExperimentThree.MOD_ID);
    }
}

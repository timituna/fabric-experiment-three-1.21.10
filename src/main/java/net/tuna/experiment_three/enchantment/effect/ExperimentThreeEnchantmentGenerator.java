package net.tuna.experiment_three.enchantment.effect;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;

import java.util.concurrent.CompletableFuture;

public class ExperimentThreeEnchantmentGenerator extends FabricDynamicRegistryProvider {
    public ExperimentThreeEnchantmentGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
        System.out.println("REGISTERING ENCHANTMENTS");
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        register(entries, ModEnchantmentEffects.THUNDERING, Enchantment.enchantment(
                Enchantment.definition(
                        registries.lookupOrThrow(Registries.ITEM).getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        10,
                        3,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(1, 15),
                        5,
                        EquipmentSlotGroup.HAND
                )
            ).withEffect(
                EnchantmentEffectComponents.POST_ATTACK,
                EnchantmentTarget.ATTACKER,
                EnchantmentTarget.VICTIM,
                new LightningEnchantmentEffect(LevelBasedValue.perLevel(0.4f, 0.2f))
            )
        );
        register(entries, ModEnchantmentEffects.HEMORRHAGE, Enchantment.enchantment(
                Enchantment.definition(
                        registries.lookupOrThrow(Registries.ITEM).getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        25,
                        1,
                        Enchantment.dynamicCost(1, 0),
                        Enchantment.dynamicCost(1, 0),
                        3,
                        EquipmentSlotGroup.HAND
                )
            ).withEffect(
                EnchantmentEffectComponents.POST_ATTACK,
                EnchantmentTarget.ATTACKER,
                EnchantmentTarget.VICTIM,
                new HemorrhageEnchantmentEffect(LevelBasedValue.perLevel(0.4f, 0.2f))
            )
        );
    }

    private void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.location()), resourceConditions);
    }

    @Override
    public String getName() {
        return "ExperimentThreeEnchantmentGenerator";
    }
}

package net.tuna.experiment_three.items;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.tuna.experiment_three.ExperimentThree;

import java.util.List;
import java.util.function.Function;

public class ModItems {
    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.accept(ModItems.SUSPICIOUS_SUBSTANCE));
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.SUSPICIOUS_SUBSTANCE, 135 * 20);
        });
    };

    private static List<MobEffectInstance> suspicious_substance_effects = List.of(new MobEffectInstance(MobEffects.POISON, 10 * 20, 1),
            new MobEffectInstance(MobEffects.BLINDNESS, 5 * 20));

    public static final Consumable POISON_FOOD_CONSUMABLE_COMPONENT = Consumables.defaultFood()
            // The duration is in ticks, 20 ticks = 1 second
            .onConsume(new ApplyStatusEffectsConsumeEffect(suspicious_substance_effects))
            .build();
    public static final FoodProperties POISON_FOOD_COMPONENT = new FoodProperties.Builder()
            .alwaysEdible()
            .build();
    public static final Item SUSPICIOUS_SUBSTANCE = registerItem("suspicious_substance", Item::new,
            new Item.Properties().food(POISON_FOOD_COMPONENT, POISON_FOOD_CONSUMABLE_COMPONENT));

    public static Item registerItem(
            String name,
            Function<Item.Properties, Item> itemFactory,
            Item.Properties properties
    ) {
        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(ExperimentThree.MOD_ID, name)
        );
        Item item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }
}

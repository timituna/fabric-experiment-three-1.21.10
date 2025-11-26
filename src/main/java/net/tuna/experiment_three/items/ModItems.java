package net.tuna.experiment_three.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;
import net.tuna.experiment_three.ExperimentThree;

import java.util.List;
import java.util.function.Function;

public class ModItems {
    public static void init() {
        /*ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.accept(ModItems.SUSPICIOUS_SUBSTANCE));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> itemGroup.accept(ModItems.GUIDITE_SWORD));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
                        .register((itemGroup) -> itemGroup.accept(ModItems.GUIDITE_HELMET));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup) -> itemGroup.accept(ModItems.GUIDITE_BOOTS));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup) -> itemGroup.accept(ModItems.GUIDITE_LEGGINGS));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup) -> itemGroup.accept(ModItems.GUIDITE_CHESTPLATE));*/
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.SUSPICIOUS_SUBSTANCE, 135 * 20);
            });
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.accept(ModItems.SUSPICIOUS_SUBSTANCE);
            itemGroup.accept(ModItems.GUIDITE_SWORD);
            itemGroup.accept(ModItems.GUIDITE_HELMET);
            itemGroup.accept(ModItems.GUIDITE_BOOTS);
            itemGroup.accept(ModItems.GUIDITE_LEGGINGS);
            itemGroup.accept(ModItems.GUIDITE_CHESTPLATE);
        });
    }

    /*public static final TagKey<Item> REPAIRS_GUIDITE_ARMOR = TagKey.create(
            BuiltInRegistries.ITEM.key(),
            ResourceLocation.fromNamespaceAndPath(ExperimentThree.MOD_ID, "repairs_guidite_armor")
    );*/


    public static final ResourceKey<CreativeModeTab> CUSTOM_ITEM_GROUP_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            ResourceLocation.fromNamespaceAndPath(ExperimentThree.MOD_ID, "item_group")
    );

    public static final CreativeModeTab CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.GUIDITE_SWORD))
            .title(Component.translatable("itemGroup.experiment-three"))
            .build();

    public static final ToolMaterial GUIDITE_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            455,
            5.0f,
            1.5f,
            22,
            GuiditeArmorMaterial.REPAIRS_GUIDITE_ARMOR
    );

    public static final Item GUIDITE_SWORD = registerItem(
            "guidite_sword",
            Item::new,
            new Item.Properties().sword(GUIDITE_TOOL_MATERIAL, 1f,1f)
    );

    private static final List<MobEffectInstance> suspicious_substance_effects = List.of(new MobEffectInstance(MobEffects.POISON, 10 * 20, 1),
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

    public static final Item GUIDITE_HELMET = registerItem(
            "guidite_helmet",
            Item::new,
            new Item.Properties().humanoidArmor(GuiditeArmorMaterial.INSTANCE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(GuiditeArmorMaterial.BASE_DURABILITY))
    );
    public static final Item GUIDITE_CHESTPLATE = registerItem("guidite_chestplate",
            Item::new,
            new Item.Properties().humanoidArmor(GuiditeArmorMaterial.INSTANCE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(GuiditeArmorMaterial.BASE_DURABILITY))
    );

    public static final Item GUIDITE_LEGGINGS = registerItem(
            "guidite_leggings",
            Item::new,
            new Item.Properties().humanoidArmor(GuiditeArmorMaterial.INSTANCE, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(GuiditeArmorMaterial.BASE_DURABILITY))
    );

    public static final Item GUIDITE_BOOTS = registerItem(
            "guidite_boots",
            Item::new,
            new Item.Properties().humanoidArmor(GuiditeArmorMaterial.INSTANCE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(GuiditeArmorMaterial.BASE_DURABILITY))
    );

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

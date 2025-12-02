package net.tuna.experiment_three;

import net.fabricmc.api.ModInitializer;

import net.tuna.experiment_three.items.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.tuna.experiment_three.enchantment.effect.ModEnchantmentEffects.registerModEnchantmentEffects;

public class ExperimentThree implements ModInitializer {
	public static final String MOD_ID = "experiment-three";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.init();
		registerModEnchantmentEffects();
		LOGGER.info("Hello Fabric world!");
	}
}
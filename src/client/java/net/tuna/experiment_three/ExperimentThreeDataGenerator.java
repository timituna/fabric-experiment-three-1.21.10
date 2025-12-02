package net.tuna.experiment_three;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.tuna.experiment_three.enchantment.effect.ExperimentThreeEnchantmentGenerator;

public class ExperimentThreeDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ExperimentThreeEnchantmentGenerator::new);
	}
}

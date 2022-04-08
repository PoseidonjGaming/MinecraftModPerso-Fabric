package fr.perso_fabric.world_gen;

import fr.perso_fabric.init.ModBlock;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;


import java.util.List;

public class OreConfig {
    private static final List<OreFeatureConfig.Target> overworl_vibrnaium_ore=List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlock.vibranium_ore.getDefaultState()));


    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig,?>> vibranium_ore= ConfiguredFeatures.register("vibrnaium_ore", Feature.ORE,new OreFeatureConfig(overworl_vibrnaium_ore,10));
}

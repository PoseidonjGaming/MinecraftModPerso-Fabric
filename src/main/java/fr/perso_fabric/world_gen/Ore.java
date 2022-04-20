package fr.perso_fabric.world_gen;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;

import java.util.List;

public class Ore {
    private static RegistryEntry<ConfiguredFeature<OreFeatureConfig,?>> config(String name, List<OreFeatureConfig.Target> targets, int size){
        return ConfiguredFeatures.register(name, Feature.ORE,new OreFeatureConfig(targets,size));
    }
    public static RegistryEntry<PlacedFeature> placedFeature(String name,List<OreFeatureConfig.Target> targets,int size, int nbVains, int min, int max){
        return PlacedFeatures.register(name+"_placed",config(name,targets,size),OreFeatures.modifiersWithCount(nbVains, HeightRangePlacementModifier.trapezoid(YOffset.fixed(min),YOffset.fixed(max))));
    }
}

package fr.perso_fabric.world_gen;

import net.minecraft.util.registry.RegistryEntry;

import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;

public class OrePlaced {
    public static final RegistryEntry<PlacedFeature> vibranium_ore_placed= PlacedFeatures.register("vibranium_ore_placed",OreConfig.vibranium_ore,OreFeatures.modifiersWithCount(5, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80),YOffset.aboveBottom(80))));
}

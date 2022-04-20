package fr.perso_fabric.world_gen;

import fr.perso_fabric.init.ModBlock;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OreGeneration {

    private static List<List> oreList=new ArrayList<List>(){
        {
            add(Arrays.asList(List.of(
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ((Block) ModBlock.blockList.get(1).get(1)).getDefaultState()),
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.NETHERRACK, ((Block) ModBlock.blockList.get(1).get(1)).getDefaultState())
            ),"vibranium_ore",10,15,80,300));
        }
    };
    private static final List<OreFeatureConfig.Target> vibrnaium_ore =List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ((Block) ModBlock.blockList.get(1).get(1)).getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.NETHERRACK,((Block)ModBlock.blockList.get(1).get(1)).getDefaultState()));
    public static void generateOres(){
        for(int i=0; i<oreList.size();i++){
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Ore.placedFeature(oreList.get(i).get(1).toString(),
                            (List<OreFeatureConfig.Target>) oreList.get(i).get(0),
                            (int)oreList.get(i).get(2),(int)oreList.get(i).get(3),
                            (int) oreList.get(i).get(3),
                            (int) oreList.get(i).get(4)
                    ).getKey().get()
            );
        }

    }
}

package fr.perso_fabric.init;

import fr.perso_fabric.Perso;
import fr.perso_fabric.block.VibraniumChest;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModBlock {
    public static final List<List> blockList=new ArrayList<List>(){
        {
            add(Arrays.asList("vibranium_block",new Block(FabricBlockSettings.of(Material.METAL).requiresTool().strength(5f,6f).sounds(BlockSoundGroup.STONE))));
        }
        {
            add(Arrays.asList("vibranium_ore",new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f,3.0f), UniformIntProvider.create(10,15))));
        }
        {
            add(Arrays.asList("vibranium_gravel",new GravelBlock(AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.STONE_GRAY).strength(0.6F).sounds(BlockSoundGroup.GRAVEL))));
        }
        {
            add(Arrays.asList("vibranium_chest",new VibraniumChest(FabricBlockSettings.of(Material.WOOD).strength(6f))));
        }


    };

    //public static final Block vibranium_chest=new Block(FabricBlockSettings.of(Material.WOOD).strength(6f));


    public static void registerAll(){
        for(int i=0; i<blockList.size();i++){
            registerBlock(blockList.get(i).get(0).toString(),(Block)blockList.get(i).get(1));
        }


    }

    public static void registerBlock(String name,Block block){
        Registry.register(Registry.BLOCK,new Identifier(Perso.ModId,name),block);
        ModItems.registerItem(name,block);
    }


}

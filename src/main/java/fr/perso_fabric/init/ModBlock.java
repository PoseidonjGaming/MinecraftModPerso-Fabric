package fr.perso_fabric.init;

import fr.perso_fabric.Perso;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

public class ModBlock {
    public static final Block vibranium_block=new Block(FabricBlockSettings.of(Material.METAL).requiresTool().strength(5f,6f).sounds(BlockSoundGroup.STONE));
    public static final Block vibranium_chest=new Block(FabricBlockSettings.of(Material.WOOD).strength(6f));
    public static final Block vibranium_ore=new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f,3.0f), UniformIntProvider.create(10,15));
    public static final Block vibranium_gravel = new GravelBlock(AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.STONE_GRAY).strength(0.6F).sounds(BlockSoundGroup.GRAVEL));
    public static void registerAll(){
        registerBlock("vibranium_block",vibranium_block);
        registerBlock("vibranium_ore",vibranium_ore);
        registerBlock("vibranium_gravel",vibranium_gravel);

    }

    public static void registerBlock(String name,Block block){
        Registry.register(Registry.BLOCK,new Identifier(Perso.ModId,name),block);
        ModItems.registerItem(name,block);
    }


}

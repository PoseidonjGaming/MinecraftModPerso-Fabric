package fr.perso_fabric;

import fr.perso_fabric.init.*;
import fr.perso_fabric.world_gen.WorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Perso implements ModInitializer {
    public static final String ModId="perso";

    public static final ItemGroup persoGroup= FabricItemGroupBuilder.build(new Identifier("perso","general"),()->new ItemStack((Item)ModItems.itemList.get(0).get(1)));

    @Override
    public void onInitialize() {
        ModItems.registerAll();
        ModBlock.registerAll();
        ModBlockEntity.registerAll();
        WorldGeneration.generationWorld();
        ModRecipe.registerRecipe();
        ModScreen.RegisterAll();

    }
}

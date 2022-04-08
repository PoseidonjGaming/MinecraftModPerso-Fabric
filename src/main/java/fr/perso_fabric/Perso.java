package fr.perso_fabric;

import fr.perso_fabric.init.ModBlock;
import fr.perso_fabric.init.ModItems;
import fr.perso_fabric.world_gen.WorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Perso implements ModInitializer {
    public static final String ModId="perso";

    public static final ItemGroup persoGroup= FabricItemGroupBuilder.build(new Identifier("perso","general"),()->new ItemStack(ModItems.vibranium_ingot));

    @Override
    public void onInitialize() {
        ModItems.registerAll();
        ModBlock.registerAll();
        WorldGeneration.generationWorld();
    }
}

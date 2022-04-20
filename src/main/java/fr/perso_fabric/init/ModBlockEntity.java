package fr.perso_fabric.init;

import fr.perso_fabric.block.VibraniumChest;
import fr.perso_fabric.block_entity.VibraniumChestBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class ModBlockEntity {

    public static BlockEntityType<VibraniumChestBlockEntity> vibrnanium_chest_blockEntity;



    public static void registerAll(){
        vibrnanium_chest_blockEntity= Registry.register(Registry.BLOCK_ENTITY_TYPE,"vibranium_chest", FabricBlockEntityTypeBuilder.create(VibraniumChestBlockEntity::new,(Block)ModBlock.blockList.get(3).get(1)).build(null));
    }
}

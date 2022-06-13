package fr.perso_fabric.block.block_entity;

import fr.perso_fabric.init.ModBlockEntity;
import fr.perso_fabric.inventory.ImplementedInventory;
import fr.perso_fabric.screen.VibraniumChestScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class VibraniumChestBlockEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory {



    private final DefaultedList<ItemStack> inventory= DefaultedList.ofSize(96,ItemStack.EMPTY);

    public VibraniumChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.vibrnanium_chest_blockEntity, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Test Chest");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new VibraniumChestScreenHandler(syncId,inv,this);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt,inventory);
        super.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,inventory);
    }
}

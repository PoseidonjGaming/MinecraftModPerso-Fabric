package fr.perso_fabric.screen;

import fr.perso_fabric.init.ModScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

public class VibraniumScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final World world;

    public VibraniumScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId,playerInventory,  new SimpleInventory(1));
    }
    public VibraniumScreenHandler(int syncId, PlayerInventory PlayerInventory, Inventory inventory) {
        super(ModScreen.vibranium_chest_screen_handler,syncId);
        this.inventory = inventory;
        this.world=PlayerInventory.player.world;
        checkSize( inventory,1);
        this.addSlot(new Slot( inventory,0,1,1));

        addPlayerInventory(PlayerInventory);
        addPlayerHotbar(PlayerInventory);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack newStack=ItemStack.EMPTY;
        Slot slot=this.slots.get(index);
        if(slot!=null && slot.hasStack()){
            ItemStack originalStack = slot.getStack();
            newStack=originalStack.copy();
            if(index<this.inventory.size()){
                if(!this.insertItem(originalStack,this.inventory.size(),this.slots.size(),true)){
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if(originalStack.isEmpty()){
                slot.setStack(ItemStack.EMPTY);
            }
            else{
                slot.markDirty();
            }
        }
        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, l * 18, i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }

}

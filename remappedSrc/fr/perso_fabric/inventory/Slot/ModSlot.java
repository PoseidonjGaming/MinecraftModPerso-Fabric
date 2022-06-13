package fr.perso_fabric.inventory.Slot;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class ModSlot extends Slot {
    public ModSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return AbstractFurnaceBlockEntity.canUseAsFuel(stack) || ModSlot.isBucket(stack);
    }
    @Override
    public int getMaxItemCount(ItemStack stack) {
        return ModSlot.isBucket(stack) ? 1 : super.getMaxItemCount(stack);
    }
    public static boolean isBucket(ItemStack stack) {
        return stack.isOf(Items.BUCKET);
    }
}

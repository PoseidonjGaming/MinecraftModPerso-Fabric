package fr.perso_fabric.inventory;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

public class ModSlot extends Slot {
    public ModSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxItemCount() {
        return 256;
    }
}

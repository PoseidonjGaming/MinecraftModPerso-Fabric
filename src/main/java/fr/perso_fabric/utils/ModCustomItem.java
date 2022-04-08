package fr.perso_fabric.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModCustomItem extends Item {
    public ModCustomItem(Settings settings) {
        super(settings);
    }
    public boolean hasGlint(ItemStack itemStack){
        return true;
    }
}

package fr.perso_fabric.utils;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class ModCustomHoe extends HoeItem {
    public ModCustomHoe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}

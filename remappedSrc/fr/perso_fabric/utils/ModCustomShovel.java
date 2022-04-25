package fr.perso_fabric.utils;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class ModCustomShovel extends ShovelItem {
    public ModCustomShovel(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}

package fr.perso_fabric.utils;


import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ModCustomSword extends SwordItem {
    public ModCustomSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}

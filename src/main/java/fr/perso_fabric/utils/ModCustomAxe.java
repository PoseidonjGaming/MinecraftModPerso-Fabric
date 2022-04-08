package fr.perso_fabric.utils;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class ModCustomAxe extends AxeItem {
    public ModCustomAxe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}

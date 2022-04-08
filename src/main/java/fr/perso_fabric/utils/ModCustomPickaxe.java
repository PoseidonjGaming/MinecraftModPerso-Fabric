package fr.perso_fabric.utils;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class ModCustomPickaxe extends PickaxeItem {

    public ModCustomPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
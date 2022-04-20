package fr.perso_fabric.lib.enchantment;

import fr.perso_fabric.lib.object.FabricShield;
import fr.perso_fabric.mixin.EnchantmentTargetMixin;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class ShieldTarget extends EnchantmentTargetMixin {

    @Override
    public boolean isAcceptableItem(Item item) {

        if(item.equals(Items.SHIELD)) {
            return true;
        }

        if(item instanceof FabricShield) {
            FabricShield shield = (FabricShield) item;
            return shield.acceptsShieldEnchantments();
        }

        return false;
    }
}
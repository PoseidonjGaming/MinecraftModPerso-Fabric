package fr.perso_fabric.lib.object;

import fr.perso_fabric.lib.enchantment.FabricShieldLibTarget;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

/**
 * Enchantment that works on fabric shields and vanilla shield(Needs events to do anything).
 */
public class FabricShieldEnchantment extends Enchantment {

    private boolean isTreasure;
    private boolean isCurse;

    /**
     * @param weight rarity of enchantment.
     */
    public FabricShieldEnchantment(Rarity weight) {
        super(weight, FabricShieldLibTarget.SHIELD_TARGET, new EquipmentSlot[] { EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND });
        this.isTreasure = false;
        this.isCurse = false;
    }

    /**
     * @param weight rarity of enchantment.
     * @param isTreasure if enchantment is a treasure enchantment.
     */
    public FabricShieldEnchantment(Rarity weight, boolean isTreasure) {
        super(weight, FabricShieldLibTarget.SHIELD_TARGET, new EquipmentSlot[] { EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND });
        this.isTreasure = isTreasure;
        this.isCurse = false;
    }

    /**
     * @param weight rarity of enchantment.
     * @param isTreasure if enchantment is a treasure enchantment.
     */
    public FabricShieldEnchantment(Rarity weight, boolean isTreasure, boolean isCurse) {
        super(weight, FabricShieldLibTarget.SHIELD_TARGET, new EquipmentSlot[] { EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND });
        this.isTreasure = isTreasure;
        this.isCurse = isCurse;
    }

    @Override
    public boolean isTreasure() {
        return this.isTreasure;
    }

    @Override
    public boolean isCursed() {
        return this.isCurse;
    }

    /**
     * @param stack item stack.
     * @return if item has this enchantment.
     */
    public boolean hasEnchantment(ItemStack stack) {
        return EnchantmentHelper.getLevel(this, stack) > 0;
    }
}
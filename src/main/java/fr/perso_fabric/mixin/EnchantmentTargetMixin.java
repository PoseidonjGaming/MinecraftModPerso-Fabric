package fr.perso_fabric.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

@Mixin(EnchantmentTarget.class)
public abstract class EnchantmentTargetMixin {
    
    @Shadow
    public abstract boolean isAcceptableItem(Item item);
}

package fr.perso_fabric.lib.object;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

/**
 * used to identify which items should be treated as shields.
 */
public interface FabricShield {

    /**
     * Vanilla shield: 100 ticks.
     * @return how many ticks shield will be disabled for when it with axe.
     */
    int getCooldownTicks();

    /**
     * If shield supports banners. Used for enabling banner crafting
     * @return Whether a shield supports banners.
     */
    boolean supportsBanner();

    /**
     * If library will allow this shield to have shield enchantments on it.
     */
    default boolean acceptsShieldEnchantments() {
        return true;
    }

    /**
     * Whether or not the shield will have a tooltip showing cooldown
     * when hit by an axe.
     */
    default boolean displayTooltip() {
        return true;
    }

    /**
     * Adds a tooltip immediately after the name & before the tooltip saying shield stats.
     * @param stack shield's item stack
     * @param world world
     * @param tooltip current tooltip
     * @param context context
     */
    default void appendShieldTooltip(ItemStack stack, List<Text> tooltip, TooltipContext context) {};
}
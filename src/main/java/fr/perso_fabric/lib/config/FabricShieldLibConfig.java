package fr.perso_fabric.lib.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "fabricshieldlib")
@Config.Gui.Background("minecraft:textures/block/dirt.png")
public class FabricShieldLibConfig implements ConfigData {
    
    public boolean enable_tooltips = true;
    public boolean allow_vanilla_shield_enchanting = true;
    @Comment("15 = Wood || 5 = Stone || 14 = Iron || 10 = Diamond || 22 = Gold")
    public int vanilla_shield_enchantability = 14;
}

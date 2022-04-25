package fr.perso_fabric.init;

import fr.perso_fabric.Perso;
import fr.perso_fabric.screen.VibraniumScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreen {
    public static final ScreenHandlerType<VibraniumScreenHandler> vibranium_chest_screen_handler= ScreenHandlerRegistry.registerSimple(new Identifier(Perso.ModId,"vibranium_chest_gui"),VibraniumScreenHandler::new);
}

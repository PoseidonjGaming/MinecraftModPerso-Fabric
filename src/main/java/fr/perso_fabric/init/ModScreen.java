package fr.perso_fabric.init;

import fr.perso_fabric.Perso;
import fr.perso_fabric.screen.SmelterScreenHandler;
import fr.perso_fabric.screen.VibraniumChestScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreen {
    public static  ScreenHandlerType<VibraniumChestScreenHandler> vibranium_chest_screen_handler;
    public static  ScreenHandlerType<SmelterScreenHandler> smelter_screen_handler;

    public static void RegisterAll(){
        vibranium_chest_screen_handler=ScreenHandlerRegistry.registerSimple(new Identifier(Perso.ModId,"vibranium_chest_gui"), VibraniumChestScreenHandler::new);
        smelter_screen_handler=ScreenHandlerRegistry.registerSimple(new Identifier(Perso.ModId,"smelter_gui"), SmelterScreenHandler::new);
    }
}

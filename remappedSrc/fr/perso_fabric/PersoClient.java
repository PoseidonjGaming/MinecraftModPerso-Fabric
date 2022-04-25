package fr.perso_fabric;

import fr.perso_fabric.init.ModScreen;
import fr.perso_fabric.screen.VibraniumChestScreen;
import fr.perso_fabric.screen.VibraniumScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class PersoClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        ScreenRegistry.register(ModScreen.vibranium_chest_screen_handler, VibraniumChestScreen::new);
    }
}

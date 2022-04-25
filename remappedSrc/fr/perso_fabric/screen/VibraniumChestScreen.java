package fr.perso_fabric.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.perso_fabric.Perso;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class VibraniumChestScreen extends HandledScreen<VibraniumScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(Perso.ModId, "textures/gui/diamond_container.png");


    public VibraniumChestScreen(VibraniumScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);


        int xMod=(width-237)/2;
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, xMod, 0, 0, 0, 256, 256);
        //drawTexture(matrices, x, y, 0, 0, 256, 256);

        /*if(handler.isLightningStorm()) {
            this.drawTexture(matrices, x + 26, y + 31, 176, 0, 28, 36);
        }*/
    }
    @Override
    protected void init() {
        super.init();
        //Center the title
        titleX = (237- textRenderer.getWidth(title)) / 2;
        titleY=-30;

    }


    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

}
package fr.perso_fabric.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.perso_fabric.Perso;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SmelterScreen extends HandledScreen<SmelterScreenHandler> {
    private static final Identifier texture=new Identifier(Perso.ModId,"textures/gui/smelter_gui.png");
    @Override
    protected void init() {
        super.init();

    }

    public SmelterScreen(SmelterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices,mouseX,mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,texture);
        int x=(width-176)/2;
        int y=(height-168)/2;
        drawTexture(matrices,x,y,0,0,176,168);
        if(handler.isCrafting()) {
            drawTexture(matrices, x + 77, y + 26, 176, 14, 36, handler.getScaledProgress());
        }

        if(handler.hasFuel()) {
            drawTexture(matrices, x + 55, (y + 28) + (14 - handler.getScaledFuelProgress()), 176,14 - handler.getScaledFuelProgress(), 14, handler.getScaledFuelProgress());
        }
    }
}

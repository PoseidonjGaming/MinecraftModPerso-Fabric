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

    private static final Identifier texture= new Identifier(Perso.ModId,"textures/gui/vibranium_chest_gui.png");

    @Override
    protected void init() {
        super.init();
        this.x = (width - 237) / 2;
        this.y = 0;
        this.playerInventoryTitleY=163;
        this.playerInventoryTitleX=35;
        this.backgroundHeight=256;
        this.backgroundWidth=237;


    }

    public VibraniumChestScreen(VibraniumScreenHandler handler, PlayerInventory inventory, Text title) {
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
        int xMod=(width-237)/2;

        drawTexture(matrices,xMod,0,0,0,236,256);
    }
}

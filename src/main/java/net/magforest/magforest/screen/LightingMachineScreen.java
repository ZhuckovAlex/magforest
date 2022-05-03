package net.magforest.magforest.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.magforest.magforest.container.LightningMachineContainer;
import net.magforest.magforest.magforest;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class LightingMachineScreen extends ContainerScreen<LightningMachineContainer> {
    private final ResourceLocation GUI = new ResourceLocation(magforest.MOD_ID,
            "textures/gui/lighting_machine.png");

    public LightingMachineScreen(LightningMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
                super.render(matrixStack, mouseX,mouseY,partialTicks);
        this.renderHoveredTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f,1f,1f,1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0,0, this.xSize, this.ySize);
        if (container.isLightning()){
            this.blit(matrixStack, i + 82, j + 9, 176, 0, 13, 17);
        }
    }
}

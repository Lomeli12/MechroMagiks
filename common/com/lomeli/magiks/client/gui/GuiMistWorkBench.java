package com.lomeli.magiks.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiMistWorkBench extends GuiContainer
{

    public GuiMistWorkBench(Container par1Container)
    {
        super(par1Container);
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
             this.fontRenderer.drawString(StatCollector.translateToLocal("container.crafting"), 28, 6, 4210752);
             this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
             mc.renderEngine.bindTexture("/mods/magiks/textures/gui/kineticgeneratorGUI.png");
             int var5 = (this.width - this.xSize) / 2;
             int var6 = (this.height - this.ySize) / 2;
             this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }


}

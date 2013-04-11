package com.lomeli.magiks.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.lomeli.magiks.inventory.ContainerKineticGenerator;
import com.lomeli.magiks.lib.Strings;
import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiKineticGenerator extends GuiContainer
{
    private TileEntityKineticGenerator tileentitykingen;

    public GuiKineticGenerator(InventoryPlayer par1Container,
            TileEntityKineticGenerator tileentitykingen)
    {
        super(new ContainerKineticGenerator(par1Container, tileentitykingen));
        ySize = 176;
        this.tileentitykingen = tileentitykingen;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = tileentitykingen.isInvNameLocalized() ? tileentitykingen
                .getInvName() : StatCollector.translateToLocal(tileentitykingen
                .getInvName());
        fontRenderer.drawString(Strings.kineticGenName, 6 + xSize / 2
                - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 8,
                ySize - 106 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture("/mods/magiks/textures/gui/kineticgeneratorGUI.png");
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        if(tileentitykingen.hasMist())
        {
            fontRenderer.drawString(StatCollector.translateToLocal("Mist Level:"),xSize + 30, ySize - 123, 4210752);
            fontRenderer.drawString(StatCollector.translateToLocal(tileentitykingen.getMistLevel() + "/" + tileentitykingen.getMaxMistLevel()),xSize + 30, ySize - 113, 4210752);
            fontRenderer.drawString(StatCollector.translateToLocal("Heat Level: " + tileentitykingen.getHeatLevel()),xSize + 30, ySize - 83, 4210752);
            
        }
    }
}

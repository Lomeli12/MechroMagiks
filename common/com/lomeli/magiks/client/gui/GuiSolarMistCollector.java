package com.lomeli.magiks.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.lomeli.magiks.inventory.ContainerSolarMistCollector;
import com.lomeli.magiks.lib.Strings;
import com.lomeli.magiks.tileentity.TileEntitySolarMistCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSolarMistCollector extends GuiContainer
{
    private TileEntitySolarMistCollector tileEntitySolar;

    public GuiSolarMistCollector(InventoryPlayer container,
            TileEntitySolarMistCollector tileEntity)
    {
        super(new ContainerSolarMistCollector(container, tileEntity));
        ySize = 176;
        tileEntitySolar = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = tileEntitySolar.isInvNameLocalized() ? tileEntitySolar
                .getInvName() : StatCollector.translateToLocal(tileEntitySolar
                .getInvName());
        fontRenderer.drawString(Strings.solarGenName, 6 + xSize / 2
                - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 8,
                ySize - 106 + 2, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine
                .bindTexture("/mods/magiks/textures/gui/solarmistcollectorGUI.png");
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        fontRenderer.drawString(StatCollector.translateToLocal("Mist Level:"),
                xSize + 30, ySize - 123, 4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal(tileEntitySolar.getMistLevel()
                        + "/" + tileEntitySolar.getMaxMistLevel()), xSize + 30,
                ySize - 113, 4210752);
        this.drawTexturedModalRect(0, 176, 32, 69, 4,
                tileEntitySolar.getMistLevel());

    }
}

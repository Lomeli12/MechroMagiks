package net.lomeli.magiks.client.gui;

import net.lomeli.magiks.inventory.ContainerKineticGenerator;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.tileentity.TileEntityKineticGenerator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;


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
        fontRenderer.drawString(ModStrings.kineticGenName, 6 + xSize / 2
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
                .func_110577_a(new ResourceLocation(ModStrings.MOD_ID.toLowerCase(),
                		ModStrings.GUI_LOC + "kineticgeneratorGUI.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        fontRenderer.drawString(
        	StatCollector.translateToLocal("Mist Level:"), xStart + 80,
            yStart + 20, 4210752);
        fontRenderer.drawString(
            StatCollector.translateToLocal(tileentitykingen.getMistLevel()
            + "/" + tileentitykingen.getMaxMistLevel()),
            xStart + 80, yStart + 30, 4210752);
        fontRenderer.drawString(
            StatCollector.translateToLocal("Heat Level: "), xStart + 80,
            yStart + 50, 4210752);
        fontRenderer.drawString(
            StatCollector.translateToLocal(tileentitykingen.getHeatLevel() + " C"), xStart + 80,
            yStart + 60, 4210752);
    }
}

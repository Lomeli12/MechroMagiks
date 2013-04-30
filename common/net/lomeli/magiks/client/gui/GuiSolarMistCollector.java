package net.lomeli.magiks.client.gui;

import net.lomeli.magiks.inventory.ContainerSolarMistCollector;
import net.lomeli.magiks.lib.Strings;
import net.lomeli.magiks.tileentity.TileEntitySolarMistCollector;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSolarMistCollector extends GuiContainer
{
    private TileEntitySolarMistCollector tileEntitySolar;
    public ContainerSolarMistCollector containerSolar;

    public GuiSolarMistCollector(InventoryPlayer container,
            TileEntitySolarMistCollector tileEntity)
    {
        super(new ContainerSolarMistCollector(container, tileEntity));
        ySize = 176;
        tileEntitySolar = tileEntity;
        containerSolar = new ContainerSolarMistCollector(container, tileEntity);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	int xStart = xSize / 2;
        int yStart = ySize / 2;
        String containerName = tileEntitySolar.isInvNameLocalized() ? tileEntitySolar
                .getInvName() : StatCollector.translateToLocal(tileEntitySolar
                .getInvName());
        fontRenderer.drawString(Strings.solarGenName , 6 + xStart
                - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 8,
                ySize - 106 + 2, 4210752);
        int e = tileEntitySolar.mistLevel;
        if(e > tileEntitySolar.maxMistLevel)
        	e = tileEntitySolar.maxMistLevel;
        
        fontRenderer.drawString("Mist Level:",
        		xStart + 5, yStart - 68, 4210752);
        fontRenderer.drawString(""+e,  xStart + 5 , yStart - 52, 4210752);
        fontRenderer.drawString("/" + tileEntitySolar.maxMistLevel, 
                xStart + 10, yStart - 42, 4210752);

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
        
        this.drawTexturedModalRect(xStart + 33, yStart + 17, 179, 0, 9,
                (52 * this.tileEntitySolar.getMistPercentage()));

    }
}

package net.lomeli.magiks.client.gui;

import net.lomeli.magiks.inventory.ContainerSolarMistCollector;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.tileentity.TileEntitySolarMistCollector;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSolarMistCollector extends GuiContainer
{
    private TileEntitySolarMistCollector tileEntitySolar;
    
    private ContainerSolarMistCollector containerSolar;

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
        fontRenderer.drawString(ModStrings.solarGenName , 6 + xStart
                - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 8,
                ySize - 106 + 2, 4210752);
        int e = containerSolar.tileEntity.getMistLevel();
        if(e > tileEntitySolar.getMaxMistLevel())
        {
        	e = tileEntitySolar.getMaxMistLevel();
        	this.drawTexturedModalRect(xStart + 33, yStart + 17, 179, 0, 9,
                (52 * (tileEntitySolar.getMistLevel() / tileEntitySolar.getMaxMistLevel())));
        }
        
        fontRenderer.drawString("Mist Level:",
        		xStart + 5, yStart - 68, 4210752);
        fontRenderer.drawString(""+e,  xStart + 5 , yStart - 52, 4210752);
        fontRenderer.drawString("/" + tileEntitySolar.getMaxMistLevel(), 
                xStart + 10, yStart - 42, 4210752);
        
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine
                .func_110577_a(new ResourceLocation(ModStrings.MOD_ID.toLowerCase(),
                		ModStrings.GUI_LOC + "solarmistcollectorGUI.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

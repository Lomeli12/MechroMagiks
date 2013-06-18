package net.lomeli.magiks.client.gui;

import org.lwjgl.opengl.GL11;

import net.lomeli.magiks.inventory.ContainerCoil;
import net.lomeli.magiks.lib.Strings;
import net.lomeli.magiks.tileentity.TileEntityCoil;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCoil extends GuiContainer
{
	public TileEntityCoil tileEntity;
	public ContainerCoil containerCoil;
	
	public GuiCoil(InventoryPlayer container,
            TileEntityCoil tile)
    {
	    super(new ContainerCoil(container, tile));
	    this.tileEntity = tile;
	    this.containerCoil = new ContainerCoil(container, tile);
    }
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	int xStart = xSize / 2;
        int yStart = ySize / 2;
        String containerName = tileEntity.isInvNameLocalized() ? tileEntity
                .getInvName() : StatCollector.translateToLocal(tileEntity
                .getInvName());
        fontRenderer.drawString(Strings.smallCoilName , xStart
                - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 8,
                ySize - 96 + 2, 4210752);
        int e = containerCoil.tileEntity.getMistLevel();
        if(e > tileEntity.getMaxMistLevel())
        {
        	e = tileEntity.getMaxMistLevel();
        	this.drawTexturedModalRect(xStart + 33, yStart + 17, 179, 0, 9,
                (52 * (tileEntity.getMistLevel() / tileEntity.getMaxMistLevel())));
        }
        
        fontRenderer.drawString("Mist Level:",
        		xStart + 5, yStart - 70, 4210752);
        fontRenderer.drawString(""+e,  xStart + 5 , yStart - 60, 4210752);
        fontRenderer.drawString("/" + tileEntity.getMaxMistLevel(), 
                xStart + 10, yStart - 50, 4210752);
        fontRenderer.drawString("Generators: " + tileEntity.connectedGen, 
        		xStart, yStart-35, 4210752);
        fontRenderer.drawString("Machines:    " + tileEntity.connectedMachine, 
        		xStart, yStart-25, 4210752);
    }
	
	@Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine
                .bindTexture("/mods/magiks/textures/gui/solarmistcollectorGUI.png");
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

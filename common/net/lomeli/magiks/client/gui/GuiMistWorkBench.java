package net.lomeli.magiks.client.gui;

import net.lomeli.magiks.inventory.ContainerMistWorkBench;
import net.lomeli.magiks.tileentity.TileEntityMistWorkBench;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiMistWorkBench extends GuiContainer
{
	@SuppressWarnings("unused")
	private TileEntityMistWorkBench tileEntity;
	
    public GuiMistWorkBench(InventoryPlayer par1InventoryPlayer, TileEntityMistWorkBench tile)
    {
        super(new ContainerMistWorkBench(par1InventoryPlayer, tile));
        this.tileEntity = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
    	
        fontRenderer.drawString(
                StatCollector.translateToLocal("Mist Workbench"), 28, 6,
                4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 8,
                ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
            int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine
                .bindTexture("/gui/crafting.png");
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
    }

}

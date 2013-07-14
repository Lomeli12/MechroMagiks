package net.lomeli.magiks.client.gui;

import net.lomeli.magiks.inventory.ContainerOreCrusher;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.tileentity.TileEntityOreCrusher;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiOreCrusher extends GuiContainer
{
    private TileEntityOreCrusher tileEntity;

    public GuiOreCrusher(InventoryPlayer playerInventory,
    		TileEntityOreCrusher tileEntity)
    {
        super(new ContainerOreCrusher(playerInventory, tileEntity));
        ySize = 176;
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
    	fontRenderer.drawString(ModStrings.oreCrusherName, xSize / 2 - 
    		fontRenderer.getStringWidth(ModStrings.oreCrusherName) / 2, 6, 4210752);
    	fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, 
    		ySize - 104, 4210752);
    	
    	int e = this.tileEntity.getProgress();
    	int i = this.tileEntity.getMistLevel();
    	
    	fontRenderer.drawString("Progress: ", xSize - 95, ySize - 118, 4210752);
    	fontRenderer.drawString(e + "%",
    		xSize - (95 - fontRenderer.getStringWidth("Progress: ")), ySize - 118, 4210752);
    	fontRenderer.drawString("Mist:", 8, 6, 4210752);
    	fontRenderer.drawString("" + i, 8, 16, 4210752);
    	fontRenderer.drawString("/" + this.tileEntity.getMaxMistLevel(), 8, 26, 4210752);   
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
            int par3)
    {
        GL11.glColor4f(1f, 1f, 1f, 1f);

        mc.renderEngine
                .func_110577_a(new ResourceLocation(ModStrings.MOD_ID.toLowerCase(),
                		ModStrings.GUI_LOC + "orecrusher.png"));

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        /*int il;
        
        if(tileEntity.processingTime > 0)
        {
        	il = tileEntity.processingTime / 12;
        	drawTexturedModalRect(x + 56, y + 36 + 12 - il, 176, 12 - il, 14, il + 2);
        }*/
    }
}

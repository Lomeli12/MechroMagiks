package net.lomeli.magiks.client.gui;

import net.lomeli.magiks.inventory.ContainerMultiFurnace;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.tileentity.TileEntityMultiFurnaceCore;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;


public class GuiMultiFurnace extends GuiContainer
{
    private TileEntityMultiFurnaceCore tileEntity;

    public GuiMultiFurnace(InventoryPlayer playerInventory,
            TileEntityMultiFurnaceCore tileEntity)
    {
        super(new ContainerMultiFurnace(playerInventory, tileEntity));
        ySize = 176;
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String containerName = tileEntity.isInvNameLocalized() ? tileEntity
                .getInvName() : StatCollector.translateToLocal(tileEntity
                .getInvName());
        fontRenderer.drawString(containerName,
                xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6,
                4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 8,
                ySize - 104, 4210752);
        
        int i = this.tileEntity.getMistLevel();
        
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
                		ModStrings.GUI_LOC + "dupefurnace.png"));

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        int i1;

        if (tileEntity.isBurning())
        {
            i1 = tileEntity.getBurnTimeRemainingScaled(12);
            drawTexturedModalRect(x + 56, y + 36 + 12 - i1, 176, 12 - i1, 14,
                    i1 + 2);
        }

        i1 = tileEntity.getCookProgressScaled(24);
        drawTexturedModalRect(x + 79, y + 34, 176, 14, i1 + 1, 16);
    }
}

package net.lomeli.magiks.client.gui;

import org.lwjgl.opengl.GL11;

import net.lomeli.magiks.inventory.ContainerHollowWood;
import net.lomeli.magiks.lib.Strings;
import net.lomeli.magiks.tileentity.TileEntityHollowWood;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHollowWood extends GuiContainer
{
	private TileEntityHollowWood tileEntity;
	public ContainerHollowWood containerHW;
	
	public GuiHollowWood(InventoryPlayer container, TileEntityHollowWood tileEntityHW) 
	{
		super(new ContainerHollowWood(container, tileEntityHW));
		ySize = 176;
		tileEntity = tileEntityHW;
		containerHW = new ContainerHollowWood(container, tileEntityHW);
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	int xStart = xSize / 2;
        int yStart = ySize / 2;
        String containerName = tileEntity.isInvNameLocalized() ? tileEntity
                .getInvName() : StatCollector.translateToLocal(tileEntity
                .getInvName());
        fontRenderer.drawString(Strings.hollowWoodName , 25 + xStart
                - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 8,
                yStart - 30, 4210752);
    }

	@Override
    protected void drawGuiContainerBackgroundLayer(float f, int i,
            int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine
                .func_110577_a(new ResourceLocation("/mods/magiks/textures/gui/hallowwood.png"));
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
    }
}

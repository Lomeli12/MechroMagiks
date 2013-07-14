package net.lomeli.magiks.client.gui;

import org.lwjgl.opengl.GL11;

import net.lomeli.magiks.inventory.ContainerLinkingChest;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.tileentity.TileEntityLinkingChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLinkingChest extends GuiContainer
{
	private TileEntityLinkingChest linkingTile;
	@SuppressWarnings("unused")
	private ContainerLinkingChest linkingCon;
	
	public GuiLinkingChest(InventoryPlayer container, TileEntityLinkingChest tile) 
	{
		super(new ContainerLinkingChest(container, tile));
		this.ySize = 221;
		this.linkingTile = tile;
		this.linkingCon = new ContainerLinkingChest(container, tile);
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	int xStart = xSize / 2;
        int yStart = ySize / 2;
        String containerName = linkingTile.isInvNameLocalized() ? linkingTile
                .getInvName() : StatCollector.translateToLocal(linkingTile
                .getInvName());
        fontRenderer.drawString(ModStrings.linkingChestName , xStart - 28
        		- fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(
        		StatCollector.translateToLocal("container.inventory"), 8,
        		yStart + 20, 4210752);
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) 
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine
                .func_110577_a(new ResourceLocation("textures/gui/container/generic_54.png"));
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
		
	}

}

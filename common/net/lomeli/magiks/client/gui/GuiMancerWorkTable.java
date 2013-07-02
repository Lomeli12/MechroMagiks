package net.lomeli.magiks.client.gui;

import org.lwjgl.opengl.GL11;

import net.lomeli.magiks.inventory.ContainerMancerWorkTable;
import net.lomeli.magiks.lib.Strings;
import net.lomeli.magiks.tileentity.TileEntityMancerWorkTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMancerWorkTable extends GuiContainer
{
	private TileEntityMancerWorkTable tileEntityMWT;
    public ContainerMancerWorkTable containerMWT;

	public GuiMancerWorkTable(InventoryPlayer container, 
			TileEntityMancerWorkTable tileEntity) 
	{
		super(new ContainerMancerWorkTable(container, tileEntity));
		ySize = 200;
        tileEntityMWT = tileEntity;
        containerMWT = new ContainerMancerWorkTable(container, tileEntity);
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	int xStart = xSize / 2;
        int yStart = ySize / 2;
        String containerName = tileEntityMWT.isInvNameLocalized() ? tileEntityMWT
                .getInvName() : StatCollector.translateToLocal(tileEntityMWT
                .getInvName());
        fontRenderer.drawString(Strings.mancerWTName , 6 + xStart
                - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 8,
                yStart - 7, 4210752);
    }

	@Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
            int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine
                .func_110577_a(new ResourceLocation("/mods/magiks/textures/gui/mancerWT.png"));
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
    }

}

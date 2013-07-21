package net.lomeli.magiks.tileentity.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.lomeli.magiks.client.model.ModelSimplePipe;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.tileentity.TileEntitySimplePipe;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntitySimplePipeRenderer extends TileEntitySpecialRenderer
{
	private ModelSimplePipe model;
	
	public TileEntitySimplePipeRenderer()
	{
		model = new ModelSimplePipe();
	}
	
	@Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, 
			double z, float tick) 
	{
		renderModel((TileEntitySimplePipe)tileentity, x, y, z, tick);
    }
	
	public void renderModel(TileEntitySimplePipe tileentity, double x, double y, double z,
			float tick)
	{
		GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) x, (float) y + 1.0F,
                (float) z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F - 1F, 0.5F);
        func_110628_a(new ResourceLocation(ModStrings.MOD_ID,"textures/entities/simplepipe.png"));
            
        model.render(0.0625F);
            
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

}

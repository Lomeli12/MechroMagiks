package net.lomeli.magiks.tileentity.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.lomeli.magiks.client.model.ModelHollowWood;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.lib.Models;
import net.lomeli.magiks.tileentity.TileEntityHollowWood;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHollowWoodRenderer extends
	TileEntitySpecialRenderer
{
	private ModelHollowWood hallowWood;
	
	public TileEntityHollowWoodRenderer()
	{
		hallowWood = new ModelHollowWood();
	}
	
	@Override
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
            double d2, float f)
    {
		renderModelAt((TileEntityHollowWood) tileentity, d0, d1, d2, f);
    }
	
	public void renderModelAt(TileEntityHollowWood tile, double x,
            double y, double z, float par8)
    {
    	
    	GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) x, (float) y + 1.0F,
                (float) z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F - 1F, 0.5F);
        func_110628_a(new ResourceLocation(ModStrings.MOD_ID, 
        		Models.MODEL_TEXTURES + "HallowWood.png"));
        
        hallowWood.render(0.0625F);

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
    }
}

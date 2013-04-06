package com.lomeli.magiks.tileentity.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;
import com.lomeli.magiks.client.model.ModelKineticGenerator;

public class TileEntityKineticGeneratorRenderer extends TileEntitySpecialRenderer
{
    private ModelKineticGenerator kineticModel;
    
    public TileEntityKineticGeneratorRenderer()
    {
        kineticModel = new ModelKineticGenerator();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
            double d2, float f)
    {
        renderModelAt((TileEntityKineticGenerator) tileentity, d0, d1, d2, f);
    }

    public void renderModelAt(TileEntityKineticGenerator tile, double par2, double par4,
            double par6, float par8)
    {
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) par2, (float) par4 + 1.0F,
                (float) par6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F - 1F, 0.5F);
        bindTextureByName("/mods/magiks/models/kineticgenerator.png");
     
        kineticModel.render(0.0625F);
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
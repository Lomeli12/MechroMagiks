package net.lomeli.magiks.tileentity.renderer;

import net.lomeli.magiks.client.model.ModelKineticGenerator;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.lib.Models;
import net.lomeli.magiks.tileentity.TileEntityKineticGenerator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;


public class TileEntityKineticGeneratorRenderer extends
        TileEntitySpecialRenderer
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

    public void renderModelAt(TileEntityKineticGenerator tile, double par2,
            double par4, double par6, float par8)
    {
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) par2, (float) par4 + 1.0F,
                (float) par6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F - 1F, 0.5F);
        func_110628_a(new ResourceLocation(ModStrings.MOD_ID, 
        		Models.MODEL_TEXTURES + "kineticgenerator.png"));

        kineticModel.render(0.0625F);

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}

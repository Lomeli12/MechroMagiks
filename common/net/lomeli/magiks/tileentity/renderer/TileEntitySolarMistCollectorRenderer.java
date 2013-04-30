package net.lomeli.magiks.tileentity.renderer;

import net.lomeli.magiks.client.model.ModelSolarMistCollector;
import net.lomeli.magiks.tileentity.TileEntitySolarMistCollector;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntitySolarMistCollectorRenderer extends
        TileEntitySpecialRenderer
{
    private ModelSolarMistCollector solarCollector;
    
    public TileEntitySolarMistCollectorRenderer()
    {
        solarCollector = new ModelSolarMistCollector();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
            double d2, float f)
    {
    	renderModelAt((TileEntitySolarMistCollector) tileentity, d0, d1, d2, f);
    }

    public void renderModelAt(TileEntitySolarMistCollector tile, double x,
            double y, double z, float par8)
    {
    	
    	GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) x, (float) y + 1.0F,
                (float) z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F - 1F, 0.5F);
        bindTextureByName("/mods/magiks/models/SolarMistCollector.png");

        solarCollector.render(0.0625F);

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

    }

}

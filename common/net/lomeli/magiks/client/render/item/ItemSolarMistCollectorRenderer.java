package net.lomeli.magiks.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import net.lomeli.magiks.client.model.ModelSolarMistCollector;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemSolarMistCollectorRenderer implements IItemRenderer
{
    private ModelSolarMistCollector solarCollector;

    public ItemSolarMistCollectorRenderer()
    {
        solarCollector = new ModelSolarMistCollector();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
    	if(type == ItemRenderType.EQUIPPED)
			renderSMC(0.25F, 2F, 0.25F, 1.25F);
		else
			renderSMC(0F, 1F, 0F, 1.0F);
    }
    
    public void renderSMC(float x, float y, float z, float scale)
    {
    	GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        // Scale, Translate, Rotate
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(180F, 1F, 0, 0);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/magiks/models/SolarMistCollector.png");
        
        solarCollector.render(0.0625F);
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}

package net.lomeli.magiks.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.lomeli.magiks.client.model.ModelMancerWorkTable;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.lib.Models;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public class ItemMancerWorkTableRenderer implements IItemRenderer
{
	private ModelMancerWorkTable mancerModel;
	
	public ItemMancerWorkTableRenderer()
	{
		mancerModel = new ModelMancerWorkTable();
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
			renderMWT(0.25F, 1.5F, 0.25F, 1.25F);

		else if(type == ItemRenderType.EQUIPPED_FIRST_PERSON)
			renderMWT(0.25F, 1.75F, 0.25F, 1.25F);
		else
			renderMWT(0F, 1F, 0F, 1.0F);
	}
	
	public void renderMWT(float x, float y, float z, float scale)
    {
		GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(180F, 1F, 0, 0);
        FMLClientHandler.instance().getClient().renderEngine
        	.func_110577_a(new ResourceLocation(ModStrings.MOD_ID, 
            		Models.MODEL_TEXTURES + "MancerTable.png"));
        
        mancerModel.render(0.0625F);
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

}

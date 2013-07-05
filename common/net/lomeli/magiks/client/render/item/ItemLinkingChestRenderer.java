package net.lomeli.magiks.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.lib.Models;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemLinkingChestRenderer implements IItemRenderer
{
	private ModelChest chestModel;
	
	public ItemLinkingChestRenderer()
	{
		chestModel = new ModelChest();
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
		switch (type) {
        case ENTITY: {
            renderChest(0.5F, 0.5F, 0.5F);
            break;
        }
        case EQUIPPED: {
            renderChest(1.0F, 1.0F, 1.0F);
            break;
        }
        case INVENTORY: {
            renderChest(0.0F, 0.075F, 0.0F);
            break;
        }
        default:
            break;
    }
	}

	private void renderChest(float x, float y, float z) {

        FMLClientHandler.instance().getClient().renderEngine
        	.func_110577_a(new ResourceLocation(ModStrings.MOD_ID, 
            		Models.MODEL_TEXTURES +"linkingChest.png"));
        GL11.glPushMatrix(); //start
        GL11.glTranslatef(x, y, z); //size
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glRotatef(-90, 0, 1, 0);
        chestModel.renderAll();
        GL11.glPopMatrix(); //end
    }
}

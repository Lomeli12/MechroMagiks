package net.lomeli.magiks.client.render.item;

import org.lwjgl.opengl.GL11;

import net.lomeli.magiks.client.model.ModelSimplePipe;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.IItemRenderer;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemSimplePipeRenderer implements IItemRenderer 
{
	private ModelSimplePipe pipeModel;
	
	public ItemSimplePipeRenderer(){
		pipeModel = new ModelSimplePipe();
	}

	@Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type){
	    return true;
    }

	@Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper){
	    return true;
    }

	@Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
		if(type == ItemRenderType.EQUIPPED)
			renderPipe(0.25F, 1.5F, 0.25F, 1.25F);
		else if(type == ItemRenderType.EQUIPPED_FIRST_PERSON)
			renderPipe(0.25F, 1.75F, 0.25F, 1.25F);
		else
			renderPipe(0F, 1F, 0F, 1.0F);
    }
	
	public void renderPipe(float x, float y, float z, float scale) 
	{
		GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(180F, 1F, 0, 0);
        
        FMLClientHandler.instance().getClient()
        	.renderEngine.func_110577_a(
        		new ResourceLocation(ModStrings.MOD_ID,"textures/entities/simplepipe.png"));
        
        pipeModel.render(0.0625F);
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}

}

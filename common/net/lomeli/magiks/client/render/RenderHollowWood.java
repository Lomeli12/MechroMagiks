package net.lomeli.magiks.client.render;

import net.lomeli.magiks.client.model.ModelHollowWood;
import net.lomeli.magiks.tileentity.TileEntityHollowWood;
import net.lomeli.magiks.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHollowWood implements ISimpleBlockRenderingHandler
{
	@SuppressWarnings("unused")
	private ModelHollowWood modelHW;
	
	public RenderHollowWood()
	{
		modelHW = new ModelHollowWood();
	}
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		TileEntityRenderer entityRenderer = TileEntityRenderer.instance;
        entityRenderer.renderTileEntityAt(new TileEntityHollowWood(), 0D,
            	0D, 0D, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) 
	{
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() 
	{
		return true;
	}

	@Override
	public int getRenderId() 
	{
		return RenderIDs.hollowWoodID;
	}
}

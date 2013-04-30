package net.lomeli.magiks.client.render;

import net.lomeli.magiks.client.model.ModelCoil;
import net.lomeli.magiks.lib.RenderIDs;
import net.lomeli.magiks.tileentity.TileEntityCoil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.world.IBlockAccess;


import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCoil implements ISimpleBlockRenderingHandler
{
	@SuppressWarnings("unused")
	private ModelCoil coilModel;

	public RenderCoil(int id)
	{
		coilModel = new ModelCoil();
		RenderIDs.coilID = id;
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		TileEntityRenderer entityRenderer = TileEntityRenderer.instance;
        entityRenderer.renderTileEntityAt(new TileEntityCoil(), 0.0, 0.0, 0.0,
        		0.5F);
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
        return RenderIDs.coilID;
    }

}

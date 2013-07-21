package net.lomeli.magiks.client.render;

import net.lomeli.magiks.client.model.ModelSimplePipe;
import net.lomeli.magiks.lib.RenderIDs;
import net.lomeli.magiks.tileentity.TileEntitySimplePipe;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSimplePipe implements ISimpleBlockRenderingHandler
{

	private ModelSimplePipe pipeModel;
	
	public RenderSimplePipe(int id){
		pipeModel = new ModelSimplePipe();
		RenderIDs.simplePipeID = id;
	}
	
	@Override
    public void renderInventoryBlock(Block block, int metadata, int modelID,
            RenderBlocks renderer)
    {
		TileEntityRenderer entityRenderer = TileEntityRenderer.instance;
        entityRenderer.renderTileEntityAt(new TileEntitySimplePipe(), 0.0, 0.0, 0.0,
        		0.5F);
    }

	@Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
            Block block, int modelId, RenderBlocks renderer){
	    return true;
    }

	@Override
    public boolean shouldRender3DInInventory(){
	    return true;
    }

	@Override
    public int getRenderId(){
	    return RenderIDs.simplePipeID;
    }

}

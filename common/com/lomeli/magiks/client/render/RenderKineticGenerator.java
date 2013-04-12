package com.lomeli.magiks.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.world.IBlockAccess;

import com.lomeli.magiks.client.model.ModelKineticGenerator;
import com.lomeli.magiks.lib.RenderIDs;
import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKineticGenerator implements ISimpleBlockRenderingHandler
{
    @SuppressWarnings("unused")
    private ModelKineticGenerator kineticModel;

    public RenderKineticGenerator(int id)
    {
        kineticModel = new ModelKineticGenerator();
        RenderIDs.kineticGID = id;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID,
            RenderBlocks renderer)
    {
        TileEntityRenderer entityRenderer = TileEntityRenderer.instance;
        entityRenderer.renderTileEntityAt(new TileEntityKineticGenerator(),
                0.0D, 0.0D, 0.0D, 0.5F);
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
        return RenderIDs.kineticGID;
    }

}

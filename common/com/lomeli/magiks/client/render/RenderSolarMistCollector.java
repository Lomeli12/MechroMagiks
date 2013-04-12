package com.lomeli.magiks.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.world.IBlockAccess;

import com.lomeli.magiks.client.model.ModelSolarMistCollector;
import com.lomeli.magiks.lib.RenderIDs;
import com.lomeli.magiks.tileentity.TileEntitySolarMistCollector;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSolarMistCollector implements ISimpleBlockRenderingHandler
{
    @SuppressWarnings("unused")
    private ModelSolarMistCollector solarCollector;

    public RenderSolarMistCollector(int id)
    {
        solarCollector = new ModelSolarMistCollector();
        RenderIDs.solarGID = id;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID,
            RenderBlocks renderer)
    {
        TileEntityRenderer entityRenderer = TileEntityRenderer.instance;
        entityRenderer.renderTileEntityAt(new TileEntitySolarMistCollector(),
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
        return RenderIDs.solarGID;
    }

}

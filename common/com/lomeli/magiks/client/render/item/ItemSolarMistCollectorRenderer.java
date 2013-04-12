package com.lomeli.magiks.client.render.item;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import com.lomeli.magiks.client.model.ModelSolarMistCollector;
import com.lomeli.magiks.tileentity.TileEntitySolarMistCollector;

public class ItemSolarMistCollectorRenderer implements IItemRenderer
{
    @SuppressWarnings("unused")
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
        TileEntityRenderer.instance.renderTileEntityAt(
                new TileEntitySolarMistCollector(), 0.05D, 0.05D, 0.05D, 0.05F);
    }
}

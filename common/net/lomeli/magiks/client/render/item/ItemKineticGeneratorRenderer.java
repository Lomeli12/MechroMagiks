package net.lomeli.magiks.client.render.item;

import net.lomeli.magiks.client.model.ModelKineticGenerator;
import net.lomeli.magiks.tileentity.TileEntityKineticGenerator;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemKineticGeneratorRenderer implements IItemRenderer
{
    @SuppressWarnings("unused")
    private ModelKineticGenerator kineticGenModel;

    public ItemKineticGeneratorRenderer()
    {
        kineticGenModel = new ModelKineticGenerator();
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
                new TileEntityKineticGenerator(), 0.05D, 0.05D, 0.05D, 0.05F);
    }
}

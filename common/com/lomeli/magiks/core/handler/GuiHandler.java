package com.lomeli.magiks.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;

import com.lomeli.magiks.inventory.ContainerKineticGenerator;
import com.lomeli.magiks.client.gui.GuiKineticGenerator;
import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z)
    {
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        if(tile_entity instanceof TileEntityKineticGenerator)
            return new ContainerKineticGenerator(player.inventory, (TileEntityKineticGenerator)tile_entity);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z)
    {
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        if(tile_entity instanceof TileEntityKineticGenerator)
            return new GuiKineticGenerator(player.inventory, (TileEntityKineticGenerator)tile_entity);
        return null;
    }

}

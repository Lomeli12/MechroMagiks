package net.lomeli.magiks.core.handler;

import net.lomeli.magiks.client.gui.GuiHollowWood;
import net.lomeli.magiks.client.gui.GuiKineticGenerator;
import net.lomeli.magiks.client.gui.GuiLinkingChest;
import net.lomeli.magiks.client.gui.GuiMancerWorkTable;
import net.lomeli.magiks.client.gui.GuiMultiFurnace;
import net.lomeli.magiks.client.gui.GuiSolarMistCollector;
import net.lomeli.magiks.inventory.ContainerHollowWood;
import net.lomeli.magiks.inventory.ContainerKineticGenerator;
import net.lomeli.magiks.inventory.ContainerLinkingChest;
import net.lomeli.magiks.inventory.ContainerMancerWorkTable;
import net.lomeli.magiks.inventory.ContainerMultiFurnace;
import net.lomeli.magiks.inventory.ContainerSolarMistCollector;
import net.lomeli.magiks.tileentity.TileEntityHollowWood;
import net.lomeli.magiks.tileentity.TileEntityKineticGenerator;
import net.lomeli.magiks.tileentity.TileEntityLinkingChest;
import net.lomeli.magiks.tileentity.TileEntityMancerWorkTable;
import net.lomeli.magiks.tileentity.TileEntityMultiFurnaceCore;
import net.lomeli.magiks.tileentity.TileEntitySolarMistCollector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z)
    {
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        if (tile_entity instanceof TileEntityKineticGenerator)
            return new ContainerKineticGenerator(player.inventory,
                    (TileEntityKineticGenerator) tile_entity);
        else if (tile_entity instanceof TileEntitySolarMistCollector)
            return new ContainerSolarMistCollector(player.inventory,
                    (TileEntitySolarMistCollector) tile_entity);
        else if (tile_entity instanceof TileEntityMultiFurnaceCore)
            return new ContainerMultiFurnace(player.inventory,
                    (TileEntityMultiFurnaceCore) tile_entity);
        else if(tile_entity instanceof TileEntityMancerWorkTable)
        	return new ContainerMancerWorkTable(player.inventory,
        			(TileEntityMancerWorkTable) tile_entity);
        else if(tile_entity instanceof TileEntityHollowWood)
        	return new ContainerHollowWood(player.inventory,
        			(TileEntityHollowWood) tile_entity);
        else if(tile_entity instanceof TileEntityLinkingChest)
        	return new ContainerLinkingChest(player.inventory, 
        			(TileEntityLinkingChest)tile_entity);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z)
    {
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        if (tile_entity instanceof TileEntityKineticGenerator)
            return new GuiKineticGenerator(player.inventory,
                    (TileEntityKineticGenerator) tile_entity);
        else if (tile_entity instanceof TileEntitySolarMistCollector)
            return new GuiSolarMistCollector(player.inventory,
                    (TileEntitySolarMistCollector) tile_entity);
        else if (tile_entity instanceof TileEntityMultiFurnaceCore)
            return new GuiMultiFurnace(player.inventory,
                    (TileEntityMultiFurnaceCore) tile_entity);
        else if(tile_entity instanceof TileEntityMancerWorkTable)
        	return new GuiMancerWorkTable(player.inventory,
        			(TileEntityMancerWorkTable) tile_entity);
        else if(tile_entity instanceof TileEntityHollowWood)
        	return new GuiHollowWood(player.inventory,
        			(TileEntityHollowWood) tile_entity);
        else if(tile_entity instanceof TileEntityLinkingChest)
        	return new GuiLinkingChest(player.inventory, 
        			(TileEntityLinkingChest)tile_entity);
        return null;
    }
}
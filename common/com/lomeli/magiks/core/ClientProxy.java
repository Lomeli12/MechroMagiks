package com.lomeli.magiks.core;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

import net.minecraftforge.client.MinecraftForgeClient;

import com.lomeli.magiks.blocks.ModBlocksMagiks;
import com.lomeli.magiks.core.DeveloperCapesAPI.DeveloperCapesAPI;
import com.lomeli.magiks.core.config.ConfigMod;
import com.lomeli.magiks.lib.RenderIDs;
import com.lomeli.magiks.client.render.item.*;
import com.lomeli.magiks.tileentity.*;
import com.lomeli.magiks.tileentity.renderer.*;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderThings()
    {
        RenderIDs.kineticGID = RenderingRegistry.getNextAvailableRenderId();
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKineticGenerator.class, 
                new TileEntityKineticGeneratorRenderer());
        MinecraftForgeClient.registerItemRenderer(ModBlocksMagiks.kineticGenerator.blockID,
                new ItemKineticGeneratorRenderer());
        ClientRegistry.registerTileEntity(TileEntityKineticGenerator.class, "keneticGen", 
                new TileEntityKineticGeneratorRenderer());
        
        renderCapes(ConfigMod.capesEnabled);
    }

    public void renderCapes(boolean capes)
    {
        if (capes == true)
        {
            DeveloperCapesAPI
                    .getInstance()
                    .init("https://dl.dropbox.com/u/17430088/Minecraft%20Mods/DivingGear/UserCapes.txt");
        } else
        {
        }
    }
}

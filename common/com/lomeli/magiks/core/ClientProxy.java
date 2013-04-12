package com.lomeli.magiks.core;

import net.minecraftforge.client.MinecraftForgeClient;

import com.lomeli.magiks.blocks.ModBlocksMagiks;
import com.lomeli.magiks.client.render.item.ItemKineticGeneratorRenderer;
import com.lomeli.magiks.client.render.item.ItemSolarMistCollectorRenderer;
import com.lomeli.magiks.core.DeveloperCapesAPI.DeveloperCapesAPI;
import com.lomeli.magiks.core.config.ConfigMod;
import com.lomeli.magiks.lib.RenderIDs;
import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;
import com.lomeli.magiks.tileentity.TileEntitySolarMistCollector;
import com.lomeli.magiks.tileentity.renderer.TileEntityKineticGeneratorRenderer;
import com.lomeli.magiks.tileentity.renderer.TileEntitySolarMistCollectorRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderThings()
    {
        RenderIDs.kineticGID = RenderingRegistry.getNextAvailableRenderId();
        RenderIDs.solarGID = RenderingRegistry.getNextAvailableRenderId();

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityKineticGenerator.class,
                new TileEntityKineticGeneratorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntitySolarMistCollector.class,
                new TileEntitySolarMistCollectorRenderer());

        MinecraftForgeClient.registerItemRenderer(
                ModBlocksMagiks.kineticGenerator.blockID,
                new ItemKineticGeneratorRenderer());
        MinecraftForgeClient.registerItemRenderer(
                ModBlocksMagiks.solarMistCollector.blockID,
                new ItemSolarMistCollectorRenderer());

        ClientRegistry.registerTileEntity(TileEntityKineticGenerator.class,
                "keneticGen", new TileEntityKineticGeneratorRenderer());
        ClientRegistry.registerTileEntity(TileEntitySolarMistCollector.class,
                "solarGen", new TileEntitySolarMistCollectorRenderer());

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

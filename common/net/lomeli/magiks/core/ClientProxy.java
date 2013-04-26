package net.lomeli.magiks.core;

import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.client.render.item.ItemKineticGeneratorRenderer;
import net.lomeli.magiks.client.render.item.ItemSolarMistCollectorRenderer;
import net.lomeli.magiks.core.DeveloperCapesAPI.DeveloperCapesAPI;
import net.lomeli.magiks.core.config.ConfigMod;
import net.lomeli.magiks.lib.RenderIDs;
import net.lomeli.magiks.tileentity.TileEntityKineticGenerator;
import net.lomeli.magiks.tileentity.TileEntityMultiFurnaceCore;
import net.lomeli.magiks.tileentity.TileEntityMultiFurnaceDummy;
import net.lomeli.magiks.tileentity.TileEntitySolarMistCollector;
import net.lomeli.magiks.tileentity.renderer.TileEntityKineticGeneratorRenderer;
import net.lomeli.magiks.tileentity.renderer.TileEntitySolarMistCollectorRenderer;
import net.minecraftforge.client.MinecraftForgeClient;


import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerThings()
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

        GameRegistry.registerTileEntity(TileEntityMultiFurnaceCore.class,
                "dupefurnace");
        GameRegistry.registerTileEntity(TileEntityMultiFurnaceDummy.class,
                "dupedummy");

        try
        {
        	renderCapes(ConfigMod.capesEnabled);
        }catch(Exception e)
        {
        	e.getStackTrace();
        }
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

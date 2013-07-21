package net.lomeli.magiks.core;

import net.lomeli.magiks.client.render.item.ItemCoilRenderer;
import net.lomeli.magiks.client.render.item.ItemHollowWoodRenderer;
import net.lomeli.magiks.client.render.item.ItemKineticGeneratorRenderer;
import net.lomeli.magiks.client.render.item.ItemLinkingChestRenderer;
import net.lomeli.magiks.client.render.item.ItemMancerWorkTableRenderer;
import net.lomeli.magiks.client.render.item.ItemSimplePipeRenderer;
import net.lomeli.magiks.client.render.item.ItemSolarMistCollectorRenderer;
import net.lomeli.magiks.core.handler.VersionCheckTickHandler;
import net.lomeli.magiks.lib.ItemIDs;
import net.lomeli.magiks.lib.RenderIDs;
import net.lomeli.magiks.tileentity.TileEntityBuilder;
import net.lomeli.magiks.tileentity.TileEntityBuilderDummy;
import net.lomeli.magiks.tileentity.TileEntityCoil;
import net.lomeli.magiks.tileentity.TileEntityHollowWood;
import net.lomeli.magiks.tileentity.TileEntityJouleBox;
import net.lomeli.magiks.tileentity.TileEntityKineticGenerator;
import net.lomeli.magiks.tileentity.TileEntityLinkingChest;
import net.lomeli.magiks.tileentity.TileEntityMancerWorkTable;
import net.lomeli.magiks.tileentity.TileEntityMultiFurnaceCore;
import net.lomeli.magiks.tileentity.TileEntityMultiFurnaceDummy;
import net.lomeli.magiks.tileentity.TileEntityOreCrusher;
import net.lomeli.magiks.tileentity.TileEntitySimplePipe;
import net.lomeli.magiks.tileentity.TileEntitySolarMistCollector;
import net.lomeli.magiks.tileentity.renderer.TileEntityCoilRenderer;
import net.lomeli.magiks.tileentity.renderer.TileEntityKineticGeneratorRenderer;
import net.lomeli.magiks.tileentity.renderer.TileEntityLinkingChestRenderer;
import net.lomeli.magiks.tileentity.renderer.TileEntitySimplePipeRenderer;
import net.lomeli.magiks.tileentity.renderer.TileEntitySolarMistCollectorRenderer;
import net.lomeli.magiks.tileentity.renderer.TileEntiyMancerWorkTableRenderer;
import net.lomeli.magiks.tileentity.renderer.TileEntityHollowWoodRenderer;

import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
	@Override
	public void initTickRegistry()
	{
		TickRegistry.registerTickHandler(new VersionCheckTickHandler(), Side.CLIENT);
	}
	
    @Override
    public void registerThings()
    {
        RenderIDs.kineticGID = RenderingRegistry.getNextAvailableRenderId();
        RenderIDs.solarGID = RenderingRegistry.getNextAvailableRenderId();
        RenderIDs.coilID = RenderingRegistry.getNextAvailableRenderId();
        RenderIDs.mancerWTID = RenderingRegistry.getNextAvailableRenderId();
        RenderIDs.hollowWoodID = RenderingRegistry.getNextAvailableRenderId();
        RenderIDs.chestID = RenderingRegistry.getNextAvailableRenderId();
        RenderIDs.simplePipeID = RenderingRegistry.getNextAvailableRenderId();
        
        RenderIDs.liquidNeoStillID = RenderingRegistry.getNextAvailableRenderId();

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityKineticGenerator.class,
                new TileEntityKineticGeneratorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntitySolarMistCollector.class,
                new TileEntitySolarMistCollectorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCoil.class,
                new TileEntityCoilRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(
        		TileEntityMancerWorkTable.class,
        		new TileEntiyMancerWorkTableRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(
        		TileEntityHollowWood.class,
        		new TileEntityHollowWoodRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(
        		TileEntityLinkingChest.class,
        		new TileEntityLinkingChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(
        		TileEntitySimplePipe.class,
        		new TileEntitySimplePipeRenderer());

        MinecraftForgeClient.registerItemRenderer(
                ItemIDs.kineticGenID,
                new ItemKineticGeneratorRenderer());
        MinecraftForgeClient.registerItemRenderer(
        		ItemIDs.solarGenID,
                new ItemSolarMistCollectorRenderer());
        MinecraftForgeClient.registerItemRenderer(ItemIDs.smallCoilID, 
        		new ItemCoilRenderer());
        MinecraftForgeClient.registerItemRenderer(ItemIDs.mancerWorkTableID,
        		new ItemMancerWorkTableRenderer());
        MinecraftForgeClient.registerItemRenderer(ItemIDs.hollowWoodID,
        		new ItemHollowWoodRenderer());
        MinecraftForgeClient.registerItemRenderer(ItemIDs.linkingChestID,
        		new ItemLinkingChestRenderer());
        MinecraftForgeClient.registerItemRenderer(ItemIDs.simplePipeID,
        		new ItemSimplePipeRenderer());

        ClientRegistry.registerTileEntity(TileEntityKineticGenerator.class,
                "keneticGen", new TileEntityKineticGeneratorRenderer());
        ClientRegistry.registerTileEntity(TileEntitySolarMistCollector.class,
                "solarGen", new TileEntitySolarMistCollectorRenderer());
        ClientRegistry.registerTileEntity(TileEntityCoil.class, "smallcoil", 
        		new TileEntityCoilRenderer());
        ClientRegistry.registerTileEntity(TileEntityMancerWorkTable.class, "mancerWT",
        		new TileEntiyMancerWorkTableRenderer());
        ClientRegistry.registerTileEntity(TileEntityHollowWood.class, "hallowWood",
        		new TileEntityHollowWoodRenderer());
        ClientRegistry.registerTileEntity(TileEntityLinkingChest.class, "linkingChest",
        		new TileEntityLinkingChestRenderer());
        ClientRegistry.registerTileEntity(TileEntitySimplePipe.class, "simplePipe",
        		new TileEntitySimplePipeRenderer());
        

        GameRegistry.registerTileEntity(TileEntityMultiFurnaceCore.class,
                "dupefurnace");
        GameRegistry.registerTileEntity(TileEntityMultiFurnaceDummy.class,
                "dupedummy");
        GameRegistry.registerTileEntity(TileEntityOreCrusher.class, "orecrusher");
        GameRegistry.registerTileEntity(TileEntityBuilder.class, "builder");
        GameRegistry.registerTileEntity(TileEntityJouleBox.class, "joulebox");
        GameRegistry.registerTileEntity(TileEntityBuilderDummy.class, "builderdummy");
    }
}

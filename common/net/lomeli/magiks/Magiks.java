package net.lomeli.magiks;

import java.io.IOException;
import java.util.logging.Level;

import net.lomeli.lomlib.util.LogHelper;

import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.blocks.worldgen.MagikWorldGen;
import net.lomeli.magiks.core.CommonProxy;
import net.lomeli.magiks.core.CreativeTabMagiks;
import net.lomeli.magiks.core.config.ConfigMod;
import net.lomeli.magiks.core.handler.GuiHandler;
import net.lomeli.magiks.core.handler.ItemDroppedHandler;
import net.lomeli.magiks.core.handler.PlayerInteractHandler;
import net.lomeli.magiks.core.handler.MagiksCraftingHandler;
import net.lomeli.magiks.core.helper.UpdateHelper;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.recipes.AddonRecipes;
import net.lomeli.magiks.recipes.MagiksRecipes;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ModStrings.MOD_ID, name = ModStrings.MOD_NAME, 
	version = ModStrings.VERSION, dependencies="required-after:LomLib@[1.0.2,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Magiks
{
    @SidedProxy(clientSide = ModStrings.CLIENT_PROXY, serverSide = ModStrings.COMMON_PROXY)
    public static CommonProxy proxy;

    @Instance(ModStrings.MOD_ID)
    public static Magiks instance;

    public static CreativeTabs modTab = new CreativeTabMagiks(
            CreativeTabs.getNextID(), ModStrings.MOD_NAME);

    public static String configDir;

    private GuiHandler guih = new GuiHandler();
    
    public static LogHelper logger;
    
    public static UpdateHelper updateInstance = new UpdateHelper();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = new LogHelper(ModStrings.MOD_NAME);
    	
    	try
        {
	        updateInstance.check(ModStrings.FILE_URL);
        } catch (Exception e)
        {
        	logger.log(Level.SEVERE, "Could not connect to update server...");
        }
    	
    	configDir = event.getModConfigurationDirectory() + "/Magiks/";

        ConfigMod.configureMod(configDir);
    	
    	ModBlocksMagiks.registerBlocks();
        ModBlocksMagiks.registerKinGenFuel();
        
        ModItemsMagiks.registerItems();
        ModItemsMagiks.addChargeableItems();

        MagiksRecipes.registerRecipes();
        MagiksRecipes.addDoubleOres();
        
        proxy.initTickRegistry();
    }

    @Mod.EventHandler
    public void main(FMLInitializationEvent event)
    {	
        NetworkRegistry.instance().registerGuiHandler(this, guih);

        MinecraftForge.EVENT_BUS.register(new ItemDroppedHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerInteractHandler());
        
        GameRegistry.registerWorldGenerator(new MagikWorldGen());
        GameRegistry.registerCraftingHandler(new MagiksCraftingHandler());

        proxy.registerThings();
        proxy.registerTileEntities();
    }
    
    @Mod.EventHandler
    public void postLoad(FMLPostInitializationEvent event) throws IOException
    {
    	AddonRecipes.getInstance().loadAddons();   
    }
}

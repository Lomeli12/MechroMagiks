package net.lomeli.magiks;

import java.io.IOException;

import net.lomeli.lomlib.util.LogHelper;

import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.blocks.worldgen.MagikWorldGen;
import net.lomeli.magiks.core.CommonProxy;
import net.lomeli.magiks.core.CreativeTabSIW;
import net.lomeli.magiks.core.config.ConfigMod;
import net.lomeli.magiks.core.handler.GuiHandler;
import net.lomeli.magiks.core.handler.ItemDroppedHandler;
import net.lomeli.magiks.core.handler.PlayerInteractHandler;
import net.lomeli.magiks.core.handler.MagiksCraftingHandler;
import net.lomeli.magiks.core.helper.UpdateHelper;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.lib.Strings;
import net.lomeli.magiks.recipes.AddonRecipes;
import net.lomeli.magiks.recipes.MagiksRecipes;
import net.lomeli.magiks.tileentity.TileEntityKineticGenerator;
import net.lomeli.magiks.tileentity.TileEntitySolarMistCollector;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
//import cpw.mods.fml.common.event.
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Strings.MOD_ID, name = Strings.MOD_NAME, 
	version = Strings.VERSION, dependencies="required-after:LomLib@[1.0.2,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Magiks
{
    @SidedProxy(clientSide = Strings.CLIENT_PROXY, serverSide = Strings.COMMON_PROXY)
    public static CommonProxy proxy;

    @Instance(Strings.MOD_ID)
    public static Magiks instance;

    public static CreativeTabs modTab = new CreativeTabSIW(
            CreativeTabs.getNextID(), Strings.MOD_NAME);

    public static String configDir;

    private GuiHandler guih = new GuiHandler();
    
    public static LogHelper logger;

    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        configDir = event.getModConfigurationDirectory() + "/Magiks/";

        ConfigMod.configureMod(configDir);
        
        logger = new LogHelper(Strings.MOD_NAME);
    }

    @Init
    public void main(FMLInitializationEvent event)
    {	
        NetworkRegistry.instance().registerGuiHandler(this, guih);

        MinecraftForge.EVENT_BUS.register(new ItemDroppedHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerInteractHandler());
        
        ModBlocksMagiks.registerBlocks();
        ModBlocksMagiks.registerKinGenFuel();
        
        ModItemsMagiks.registerItems();
        ModItemsMagiks.addChargeableItems();

        GameRegistry.registerWorldGenerator(new MagikWorldGen());
        GameRegistry.registerCraftingHandler(new MagiksCraftingHandler());
        
        MagiksRecipes.registerRecipes();
        MagiksRecipes.addDoubleOres();
        
        MagiksArrays.canRecieveMist.add(new TileEntitySolarMistCollector());
        MagiksArrays.canRecieveMist.add(new TileEntityKineticGenerator());

        proxy.registerThings();
        proxy.registerTileEntities();
    }

    @PostInit
    public void postLoad(FMLPostInitializationEvent event) throws IOException
    {
    	UpdateHelper.execute(Strings.FILE_URL);
    	AddonRecipes.getInstance().loadAddons();   
    }
}

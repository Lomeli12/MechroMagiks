package net.lomeli.magiks;

import net.lomeli.magiks.addons.AddonCheck;
import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.blocks.worldgen.MagikWorldGen;
import net.lomeli.magiks.core.CommonProxy;
import net.lomeli.magiks.core.CreativeTabSIW;
import net.lomeli.magiks.core.config.ConfigMod;
import net.lomeli.magiks.core.handler.GuiHandler;
import net.lomeli.magiks.core.handler.ItemDroppedHandler;
import net.lomeli.magiks.core.handler.PlayerInteractHandler;
import net.lomeli.magiks.core.handler.WandCraftingHandler;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.lib.Strings;
import net.lomeli.magiks.recipes.MagiksRecipes;
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
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Strings.modID, name = Strings.modName, version = Strings.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Magiks
{
    @SidedProxy(clientSide = Strings.clientProxy, serverSide = Strings.commonProxy)
    public static CommonProxy proxy;

    @Instance(Strings.modID)
    public static Magiks instance;

    public static CreativeTabs modTab = new CreativeTabSIW(
            CreativeTabs.getNextID(), Strings.modName);

    public static String configDir;

    private GuiHandler guih = new GuiHandler();

    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        configDir = event.getModConfigurationDirectory() + "\\Magiks\\";

        ConfigMod.configureItemID(configDir);
    }

    @Init
    public void main(FMLInitializationEvent event)
    {
        NetworkRegistry.instance().registerGuiHandler(this, guih);

        MinecraftForge.EVENT_BUS.register(new ItemDroppedHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerInteractHandler());

        ModItemsMagiks.registerItems();
        ModItemsMagiks.addChargeableItems();

        ModBlocksMagiks.registerBlocks();
        ModBlocksMagiks.registerKinGenFuel();

        GameRegistry.registerWorldGenerator(new MagikWorldGen());
        GameRegistry.registerCraftingHandler(new WandCraftingHandler());
        
        MagiksRecipes.registerBlockRecipes();
        MagiksRecipes.registerItemRecipes();
        MagiksRecipes.registerFurnaceRecipes();

        proxy.registerThings();
        proxy.registerTileEntities();

    }

    @PostInit
    public void postLoad(FMLPostInitializationEvent event)
    {
    	AddonCheck.checkAddons();
    }
}

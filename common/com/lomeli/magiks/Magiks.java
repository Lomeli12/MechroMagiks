package com.lomeli.magiks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import com.lomeli.magiks.blocks.ModBlocksMagiks;
import com.lomeli.magiks.blocks.worldgen.MagikWorldGen;
import com.lomeli.magiks.core.CommonProxy;
import com.lomeli.magiks.core.CreativeTabSIW;
import com.lomeli.magiks.core.config.ConfigMod;
import com.lomeli.magiks.core.handler.GuiHandler;
import com.lomeli.magiks.core.handler.ItemDroppedHandler;
import com.lomeli.magiks.core.handler.PlayerInteractHandler;
import com.lomeli.magiks.core.handler.WandCraftingHandler;
import com.lomeli.magiks.items.ModItemsMagiks;
import com.lomeli.magiks.lib.Strings;

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

        ModBlocksMagiks.registerBlockRecipes();

        ModItemsMagiks.registerItemRecipes();
        ModItemsMagiks.registerFurnaceRecipes();

        GameRegistry.registerWorldGenerator(new MagikWorldGen());
        GameRegistry.registerCraftingHandler(new WandCraftingHandler());
        
        proxy.registerRenderThings();

    }

    @PostInit
    public void postLoad(FMLPostInitializationEvent event)
    {
    }
}

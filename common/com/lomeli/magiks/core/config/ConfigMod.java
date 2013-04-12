package com.lomeli.magiks.core.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

import com.lomeli.magiks.lib.Ints;

public class ConfigMod
{

    public static boolean capesEnabled;

    public static void configureItemID(String loc)
    {
        Configuration config = new Configuration(new File(loc,
                "MechroMagikIDs.cfg"));

        config.load();

        Ints.flyingRingID = config.get("Items", "FlyingRing", 7000)
                .getInt(7000);
        Ints.enchantedGemID = config.get("Items", "EnchantedGem", 7001).getInt(
                7001);
        Ints.enchantedDiamondID = config.get("Items", "EnchantedDiamond", 7002)
                .getInt(7002);
        Ints.ironBandID = config.get("Items", "IronBand", 7003).getInt(7003);
        Ints.ironStickID = config.get("Items", "IronStick", 7004).getInt(7004);
        Ints.ingotStamaticID = config.get("Items", "IngotStamatic", 7005)
                .getInt(7005);
        Ints.ingotIgniousID = config.get("Items", "IngotIgnious", 7006).getInt(
                7006);
        Ints.darkMatterID = config.get("Items", "darkMatter", 7007)
                .getInt(7007);
        Ints.deprivedDustID = config.get("Items", "deprivedLightStone", 7008)
                .getInt(7008);
        Ints.mistPanelID = config.get("Items", "mistPanel", 7009).getInt(7009);

        Ints.empoweredPickID = config.get("Tools", "EmpoweredPick", 7020)
                .getInt(7020);
        Ints.pirasVarinhaID = config.get("Tools", "FireWand", 7021)
                .getInt(7021);
        Ints.diggersWandID = config.get("Tools", "MiningWand-Level0", 7022)
                .getInt(7022);

        Ints.enchantedOreID = config.get("Blocks", "EnchantedOre", 700).getInt(
                100);
        Ints.enchantedBlockID = config.get("Blocks", "EnchantedBlock", 701)
                .getInt(701);
        Ints.kineticGenID = config.get("Machines", "KineticGen", 702).getInt(
                702);
        Ints.manceryBlockID = config.get("Blocks", "ManceryBlock", 703).getInt(
                703);
        Ints.manceryGlassID = config.get("Blocks", "ManceryGlass", 704).getInt(
                704);
        Ints.stamaticOreID = config.get("Blocks", "OreStamatic", 705).getInt(
                705);
        Ints.igniousOreID = config.get("Blocks", "OreIgnious", 706).getInt(706);
        Ints.solarGenID = config.get("Machines", "SolarCollector", 707).getInt(
                707);

        config.save();
    }

    public static void configureOptions(String loc)
    {
        Configuration config = new Configuration(new File(loc,
                "MechroMagikOptions.cfg"));

        config.load();

        capesEnabled = config.get(null, "capesEnabled", true).getBoolean(true);

        config.save();
    }
}

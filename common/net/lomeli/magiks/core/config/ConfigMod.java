package net.lomeli.magiks.core.config;

import java.io.File;

import net.lomeli.magiks.lib.Ints;
import net.lomeli.magiks.lib.ItemIDs;
import net.minecraftforge.common.Configuration;

public class ConfigMod
{
    public static boolean capesEnabled;
    public static boolean disablePiras;
    
    public static void configureMod(String Loc)
    {
    	configureItemIDs(Loc);
    	configureBlockIDs(Loc);
    	configureOptions(Loc);
    }
    
    public static void configureItemIDs(String loc)
    {
    	System.out.println(loc);
        Configuration config = new Configuration(new File(loc,
                "MechroMagikIDs.cfg"));

        config.load();

        ItemIDs.flyingRingID = config.get("Items", "FlyingRing", 7000)
                .getInt(7000);
        ItemIDs.enchantedGemID = config.get("Items", "EnchantedGem", 7001).getInt(
                7001);
        ItemIDs.enchantedDiamondID = config.get("Items", "EnchantedDiamond", 7002)
                .getInt(7002);
        ItemIDs.ironBandID = config.get("Items", "IronBand", 7003).getInt(7003);
        ItemIDs.ironStickID = config.get("Items", "IronStick", 7004).getInt(7004);
        ItemIDs.ingotStamaticID = config.get("Ingots", "IngotStamatic", 7005)
                .getInt(7005);
        ItemIDs.ingotIgniousID = config.get("Ingots", "IngotIgnious", 7006).getInt(
                7006);
        ItemIDs.darkMatterID = config.get("Items", "darkMatter", 7007)
                .getInt(7007);
        ItemIDs.deprivedDustID = config.get("Items", "deprivedLightStone", 7008)
                .getInt(7008);
        ItemIDs.mistPanelID = config.get("Items", "mistPanel", 7009).getInt(7009);
        ItemIDs.burningUpgradeID = config.get("Items", "burningUpgrades", 7010)
                .getInt(7010);
        ItemIDs.ironPlateID = config.get("Items", "ironPlate", 7011).getInt(711);

        ItemIDs.empoweredPickID = config.get("Tools", "EmpoweredPick", 7020)
                .getInt(7020);
        ItemIDs.pirasVarinhaID = config.get("Wands", "FireWand", 7021)
                .getInt(7021);
        ItemIDs.diggersWandID = config.get("Wands", "MiningWand-Stone", 7022)
                .getInt(7022);
        ItemIDs.diggersWandIronID = config.get("Wands", "MiningWand-Iron", 7038)
        		.getInt(7038);
        ItemIDs.diggersWandDiamondID = config.get("Wands", "MiningWand-Diamond", 7039)
        		.getInt(7039);

        ItemIDs.basicWandID = config.get("Wands", "basicwand", 7023).getInt(7023);
        ItemIDs.chemistWandID = config.get("Wands", "chemistwand", 7024).getInt(
                7024);
        ItemIDs.alchemistWandID = config.get("Wands", "alchemistwand", 7025)
                .getInt(7025);
        ItemIDs.wandID = config.get("Wands", "wand", 7026).getInt(7026);
        
        ItemIDs.spencerSwordID = config.get("Tools", "SpencerSword", 7027).getInt(7027);
        ItemIDs.emeraldAmuletID = config.get("Tools", "EmeraldAmulet", 7028).getInt(7028);
        ItemIDs.weatherRocketsID = config.get("Items", "Weather Rockets", 7029).getInt(7029);
        
        ItemIDs.ingotCopperID = config.get("Ingots", "IngotCopper", 7030).getInt(7030);
        ItemIDs.ingotTinID = config.get("Ingots", "IngotTin", 7031).getInt(7031);
        ItemIDs.ingotSilverID = config.get("Ingots", "IngotSilver", 7032).getInt(7032);
        
        ItemIDs.chestLinkerID = config.get("Tools", "chestLinker", 7033).getInt(7033);
        ItemIDs.circuitID = config.get("Items", "circuit", 7034).getInt(7034);
        ItemIDs.copperWireID = config.get("Items", "copperWire", 7035).getInt(7035);
        ItemIDs.wirelessRecieverID = config.get("Items", "wirelessReciever", 7036).getInt(7036);
        ItemIDs.advRecieverID = config.get("Items", "advReciever", 7037).getInt(7037);
        ItemIDs.bluePrintID = config.get("Items", "BluePrints", 7043).getInt(7043);
        ItemIDs.processedFoodID = config.get("Foods", "ProcessedFood", 7040).getInt(7040);
        ItemIDs.ingotVesiID = config.get("Ingots", "vesiIngot", 7041).getInt(7041);
        ItemIDs.dustIngotsID = config.get("Dusts", "ironDust", 7042).getInt(7042);
        ItemIDs.smeltingUpgradeID = config.get("Itmes", "smeltingUpgrade", 7051).getInt(7051);
        ItemIDs.grindingPickID = config.get("Tools", "grindingPick", 7052).getInt(7052);

        config.save();
    }
    
    public static void configureBlockIDs(String loc)
    {
    	System.out.println(loc);
    	Configuration config = new Configuration(new File(loc,
                "MechroMagikIDs.cfg"));

        config.load();
        
    	ItemIDs.enchantedOreID = config.get("Blocks", "EnchantedOre", 700).getInt(
                 100);
        ItemIDs.enchantedBlockID = config.get("Blocks", "EnchantedBlock", 701)
                 .getInt(701);
        ItemIDs.kineticGenID = config.get("Machines", "KineticGen", 702).getInt(
                 702);
        ItemIDs.manceryBlockID = config.get("Blocks", "ManceryBlock", 703).getInt(
                 703);
        ItemIDs.manceryGlassID = config.get("Blocks", "ManceryGlass", 704).getInt(
                 704);
        ItemIDs.stamaticOreID = config.get("Blocks", "OreStamatic", 705).getInt(
                 705);
        ItemIDs.igniousOreID = config.get("Blocks", "OreIgnious", 706).getInt(706);
        ItemIDs.solarGenID = config.get("Machines", "SolarCollector", 707).getInt(
                 707);
        ItemIDs.manceryPaneID = config.get("Blocks", "manceryPane", 708).getInt(708);
        ItemIDs.dupeFurnaceID = config.get("Machines", "DupeFurnace", 709).getInt(
                 709);
        ItemIDs.dupeDummyID = config.get("Blocks", "BuringStone", 710).getInt(710);
        ItemIDs.manceryBrickID = config.get("Blocks", "ManceryBrick", 711).getInt(
                 711);
        ItemIDs.obsidianStairsID = config.get("Blocks", "ObsidianStairs", 712).getInt(712);
        ItemIDs.manceryStoneStairsID = config.get("Blocks", "ManceryStoneStairs", 713).getInt(713);
        ItemIDs.manceryBrickStairsID = config.get("Blocks", "ManceryBrickStairs", 714).getInt(714);
        ItemIDs.burningBrickStairsID = config.get("Blocks", "BurningStoneStairs", 715).getInt(715);
        ItemIDs.netherIgniousID = config.get("Blocks", "NetherIgnious", 716).getInt(716);
        ItemIDs.copperOreID = config.get("Blocks", "copperOre", 717).getInt(717);
        ItemIDs.tinOreID = config.get("Blocks", "tinOre", 718).getInt(718);
        ItemIDs.silverOreID = config.get("Blocks", "silverOre", 719).getInt(719);
        ItemIDs.smallCoilID = config.get("Machines", "smallCoil", 720).getInt(720);
        ItemIDs.mancerWorkTableID = config.get("Machines", "mancerWT", 721).getInt(721);
        ItemIDs.hollowWoodID = config.get("Blocks", "hallowWood", 722).getInt(722);
        ItemIDs.linkingChestID = config.get("Blocks", "linkingChest", 723).getInt(723);
        ItemIDs.oreCrusherID = config.get("Machines", "oreCrusher", 724).getInt(724);
        ItemIDs.stamaticBlockID = config.get("Blocks", "stamaticBlock", 725).getInt(725);
        ItemIDs.igniousBlockID = config.get("Blocks", "igniousBlock", 726).getInt(726);
        ItemIDs.builderCoreID = config.get("Machines", "builderCore", 727).getInt(727);
        ItemIDs.builderDummyID = config.get("Machines", "builderDummy", 728).getInt(728);
        ItemIDs.neoStillID = config.get("Liquids", "liquidNeoniteStll", 729).getInt(729);
        ItemIDs.jouleBoxID = config.get("Machines", "jouleBox", 730).getInt(730);
        
        config.save();
    }

	@SuppressWarnings("static-access")
	public static void configureOptions(String loc)
    {
		Configuration config2 = new Configuration(new File(loc, "MechroMagikOptions.cfg"));
        
        config2.load();
        
        capesEnabled = config2.get(config2.CATEGORY_GENERAL, "capesEnabled", true).getBoolean(true);
        disablePiras = config2.get(config2.CATEGORY_GENERAL, "disablePiras", false)
                .getBoolean(false);
        Ints.mjMistConversionRate = config2.get(config2.CATEGORY_GENERAL, "mjMistConversionRate", 16)
        		.getInt(16);
        
        Ints.neoniteGen = config2.get("OreGen", "neonite", 3).getInt(3);
        Ints.igniousGen = config2.get("OreGen", "ignious", 4).getInt(4);
        Ints.nIgniousGen = config2.get("OreGen", "netherIgnious", 15).getInt(15);
        Ints.stamaticGen = config2.get("OreGen", "stamatic", 15).getInt(15);
        Ints.copperGen = config2.get("OreGen", "copper", 10).getInt(10);
        Ints.tinGen = config2.get("OreGen", "tin", 7).getInt(7);
        Ints.silverGen = config2.get("OreGen", "silver", 7).getInt(7);
        
        
        
        config2.save();   
    }
}

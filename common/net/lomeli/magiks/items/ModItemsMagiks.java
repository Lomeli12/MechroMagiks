package net.lomeli.magiks.items;

import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.items.magik.ItemAmulets;
import net.lomeli.magiks.items.magik.ItemChestLink;
import net.lomeli.magiks.items.magik.ItemFlyingRing;
import net.lomeli.magiks.items.magik.ItemPirasVarinha;
import net.lomeli.magiks.items.magik.ItemWands;
import net.lomeli.magiks.items.science.ItemMachineBluePrints;
import net.lomeli.magiks.items.science.ItemWeatherRockets;
import net.lomeli.magiks.items.tools.EnumWandStrength;
import net.lomeli.magiks.items.tools.ItemEmpoweredPick;
import net.lomeli.magiks.items.tools.ItemLevelingSword;
import net.lomeli.magiks.items.tools.ItemMiningWands;
import net.lomeli.magiks.lib.ItemIDs;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModItemsMagiks
{	
    public static Item flyingRing;
    public static Item neoniteGem;
    public static Item neonitePick;
    public static Item enchantedDiamond;
    public static Item levelingSword;
    public static Item ironBand;
    public static Item ironStick;
    public static Item emeraldAmulet;
    public static Item darkMatter;
    public static Item mistPanel;
    public static Item deprivedDust;
    public static Item burningUpgrade;
    public static Item ironPlate;
    public static Item weatherRockets;
    public static Item chestLinker;
    public static Item electroicCircuit;
    public static Item copperWire;
    public static Item wirelessReciever;
    public static Item advReciever;
    public static Item testdye;
    
    // ores
    public static Item ingotStamatic;
    public static Item ingotIgnious;
    public static Item ingotCopper;
    public static Item ingotTin;
    public static Item ingotSilver;
    
    //blueprints
    public static Item bluePrint;
    /*
    public static Item coilBluePrint;
    public static Item weatherBluePrint;
    public static Item maceratorBluePrint;
    public static Item areaDetoxicBluePrint;
    public static Item matterGeneratorBluePrint;
    public static Item lavaGeneratorBluePrint;
    public static Item matterCondenserBluePrint;*/
    
    // wands
    public static Item basicWand;
    public static Item chemistWand;
    public static Item alchemistWand;
    public static Item pirasVarinha;
    
    public static Item diggersWandS;
    public static Item diggersWandI;
    public static Item diggersWandD;

    public static Item wand;

    public static void registerItems()
    {
        flyingRing = new ItemFlyingRing(ItemIDs.flyingRingID, "flyingring")
                .setUnlocalizedName("flyingring");
        neoniteGem = new ItemGeneric(ItemIDs.enchantedGemID, "neoniteGem", true)
                .setUnlocalizedName("enchantedGem");
        enchantedDiamond = new ItemGeneric(ItemIDs.enchantedDiamondID,
                "empowereddiamond", true)
                .setUnlocalizedName("enchantedDiamond");
        ironBand = new ItemGeneric(ItemIDs.ironBandID, "ironband", false)
                .setUnlocalizedName("ironBand");
        ironStick = new ItemGeneric(ItemIDs.ironStickID, "ironstick", false)
                .setUnlocalizedName("ironStick");
        emeraldAmulet = new ItemAmulets(ItemIDs.emeraldAmuletID, "amuletGold", true, 50000)
                .setUnlocalizedName("amulet");
        ingotStamatic = new ItemGeneric(ItemIDs.ingotStamaticID, "ingotstamatic",
                false).setUnlocalizedName("ingotstamatic");
        ingotIgnious = new ItemGeneric(ItemIDs.ingotIgniousID, "ingotignious",
                false).setUnlocalizedName("ingotignious");
        mistPanel = new ItemGeneric(ItemIDs.mistPanelID, "lighthungryplates",
                false).setUnlocalizedName("mistPanel");
        darkMatter = new ItemGeneric(ItemIDs.darkMatterID, "darkmatter", true)
                .setUnlocalizedName("darkmatter");
        deprivedDust = new ItemGeneric(ItemIDs.deprivedDustID,
                "deprivedglowstone", false).setUnlocalizedName("deprivedDust");
        burningUpgrade = new ItemGeneric(ItemIDs.burningUpgradeID,
                "burningupgrade", false).setUnlocalizedName("burningupgrade")
                .setMaxStackSize(2);
        ironPlate = new ItemGeneric(ItemIDs.ironPlateID, "ironplate", false)
                .setUnlocalizedName("ironplate");
        ingotCopper = new ItemGeneric(ItemIDs.ingotCopperID, "ingotCopper", false)
        		.setUnlocalizedName("ingotCopper");
        ingotTin = new ItemGeneric(ItemIDs.ingotTinID, "ingotTin", false)
			.setUnlocalizedName("ingotTin");
        ingotSilver = new ItemGeneric(ItemIDs.ingotSilverID, "ingotSilver", false)
			.setUnlocalizedName("ingotSilver");
        chestLinker = new ItemChestLink(ItemIDs.chestLinkerID, "chestLinker")
        	.setUnlocalizedName("chestLinker");
        electroicCircuit = new ItemGeneric(ItemIDs.circuitID, "electronicCircuit", false)
        	.setUnlocalizedName("electronicCircuit");
        copperWire = new ItemGeneric(ItemIDs.copperWireID, "copperWire", false)
        	.setUnlocalizedName("copperWire");
        wirelessReciever = new ItemGeneric(ItemIDs.wirelessRecieverID, Strings.NO_TEXTURE, false)
        	.setUnlocalizedName("wirelessReciever");
        advReciever = new ItemGeneric(ItemIDs.advRecieverID, Strings.NO_TEXTURE, true)
        	.setUnlocalizedName("advReciever");
        
        weatherRockets = new ItemWeatherRockets(ItemIDs.weatherRocketsID, "rockets");
        
        wand = new ItemGeneric(ItemIDs.wandID, "magicianwand", false)
            .setCreativeTab(null).setUnlocalizedName("wand");

        basicWand = new ItemWands(ItemIDs.basicWandID, "basicwand", false, 50)
                .setUnlocalizedName("basicwand");
        chemistWand = new ItemWands(ItemIDs.chemistWandID, "chemistwand", false,
                100).setUnlocalizedName("chemistwand");
        alchemistWand = new ItemWands(ItemIDs.alchemistWandID, "alchemistwand",
                true, 300).setUnlocalizedName("alchemistwand");

        neonitePick = new ItemEmpoweredPick(ItemIDs.empoweredPickID,
                EnumToolMaterial.IRON, "pickaxeEmpowered")
                .setUnlocalizedName("empoweredPick");
        levelingSword = new ItemLevelingSword(ItemIDs.spencerSwordID, EnumToolMaterial.IRON,
                "levelingsword").setUnlocalizedName("lvlSword");
        pirasVarinha = new ItemPirasVarinha(ItemIDs.pirasVarinhaID,
                "pirasvarinha", false).setUnlocalizedName("pirasvarinha");
        diggersWandS = new ItemMiningWands(ItemIDs.diggersWandID, "diggerswand",
                EnumWandStrength.STONE).setUnlocalizedName("diggerswandS");
        diggersWandI = new ItemMiningWands(ItemIDs.diggersWandIronID, "diggerswand",
        		EnumWandStrength.IRON).setUnlocalizedName("diggersWandI");
        diggersWandD = new ItemMiningWands(ItemIDs.diggersWandDiamondID, "diggerswand",
        		EnumWandStrength.DIAMOND).setUnlocalizedName("diggersWandD");
        bluePrint = new ItemMachineBluePrints(ItemIDs.bluePrintID);

        LanguageRegistry.addName(flyingRing, "ShadowWing's Gale");
        LanguageRegistry.addName(neoniteGem, "Neonite Gem");
        LanguageRegistry.addName(neonitePick, "Neonite Pickaxe");
        LanguageRegistry.addName(enchantedDiamond, "Enchanted Diamond");
        LanguageRegistry.addName(levelingSword, "Sir Spencer's Blade");
        LanguageRegistry.addName(ironBand, "Iron Band");
        LanguageRegistry.addName(ironStick, "Iron Stick");
        LanguageRegistry.addName(emeraldAmulet, "Emerald Amulet");
        LanguageRegistry.addName(ingotStamatic, "Stamatic Ingot");
        LanguageRegistry.addName(ingotIgnious, "\u00a74Ignious Ingot");
        LanguageRegistry.addName(pirasVarinha, "\u00a74Piras Varinha");
        LanguageRegistry.addName(diggersWandS, "\u00a7eDigger's Wand");
        LanguageRegistry.addName(diggersWandI, "\u00a76Digger's Wand");
        LanguageRegistry.addName(diggersWandD, "\u00a7bDigger's Wand");
        LanguageRegistry.addName(mistPanel, "Light Hungry Plates");
        LanguageRegistry.addName(darkMatter, "Dark Matter");
        LanguageRegistry.addName(deprivedDust, "Deprived Glowstone");
        LanguageRegistry.addName(basicWand, "Basic Wand");
        LanguageRegistry.addName(chemistWand, "Chemist's Wand");
        LanguageRegistry.addName(alchemistWand, "Alchemist's Wand");
        LanguageRegistry.addName(ironPlate, "Iron Plate");
        LanguageRegistry.addName(burningUpgrade, "Burning Upgrade");
        LanguageRegistry.addName(wand, "Magician's Wand");
        LanguageRegistry.addName(ingotCopper, "Copper Ingot");
        LanguageRegistry.addName(ingotTin, "Tin Ingot");
        LanguageRegistry.addName(ingotSilver, "Silver Ingot");
        LanguageRegistry.addName(chestLinker, "Chest Linker");
        LanguageRegistry.addName(electroicCircuit, "Electronic Circuit");
        LanguageRegistry.addName(copperWire, "Copper Wire");
        LanguageRegistry.addName(wirelessReciever, "Wireless Reciever");
        LanguageRegistry.addName(advReciever, "Advanced Wireless Reciever");
        
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 0),  "Sunny Rocket");
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 1),  "Morning Rocket");
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 2),  "Noctornal Rocket");
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 3),  "Dark Rocket");
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 4),  "Cloudy Rocket");
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 5),  "Ender Rocket");
        
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 0), "Small Coil BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 1), "Atmospheric Disruptor BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 2), "Ore Crusher BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 3), "Area Detoxifier BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 4), "Matter Generator BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 5), "Lava Generator BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 6), "Matter Conderser BluePrint");
        
        OreDictionary.registerOre("ingotCopper", new ItemStack(ingotCopper));
        OreDictionary.registerOre("ingotTin", new ItemStack(ingotTin));
        OreDictionary.registerOre("ingotSilver", new ItemStack(ingotSilver));
    }

    public static void addChargeableItems()
    {
        MagiksArrays.rechargeableItems.add(new ItemStack(flyingRing));
        MagiksArrays.rechargeableItems.add(new ItemStack(pirasVarinha));
        MagiksArrays.rechargeableItems.add(new ItemStack(emeraldAmulet));
        
        MagiksArrays.rechargeableItems.add(new ItemStack(diggersWandS));
        MagiksArrays.rechargeableItems.add(new ItemStack(diggersWandI));
        MagiksArrays.rechargeableItems.add(new ItemStack(diggersWandD));

        MagiksArrays.rechargeableItems.add(new ItemStack(basicWand));
        MagiksArrays.rechargeableItems.add(new ItemStack(chemistWand));
        MagiksArrays.rechargeableItems.add(new ItemStack(alchemistWand));

        MagiksArrays.damageOnCraft.add(new ItemStack(basicWand));
        MagiksArrays.damageOnCraft.add(new ItemStack(chemistWand));
        MagiksArrays.damageOnCraft.add(new ItemStack(alchemistWand));

        MagiksArrays.wands.add(new ItemStack(basicWand));
        MagiksArrays.wands.add(new ItemStack(chemistWand));
        MagiksArrays.wands.add(new ItemStack(alchemistWand));
    }
}

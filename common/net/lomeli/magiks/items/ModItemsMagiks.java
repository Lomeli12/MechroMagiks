package net.lomeli.magiks.items;

import net.lomeli.lomlib.util.ToolTipUtil;
import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.items.magik.ItemAmulets;
import net.lomeli.magiks.items.magik.ItemFlyingRing;
import net.lomeli.magiks.items.magik.ItemPirasVarinha;
import net.lomeli.magiks.items.magik.ItemWands;
import net.lomeli.magiks.items.science.ItemChestLink;
import net.lomeli.magiks.items.science.ItemMachineBluePrints;
import net.lomeli.magiks.items.science.ItemUpgrades;
import net.lomeli.magiks.items.science.ItemWeatherRockets;
import net.lomeli.magiks.items.tools.EnumWandStrength;
import net.lomeli.magiks.items.tools.ItemEmpoweredPick;
import net.lomeli.magiks.items.tools.ItemLevelingSword;
import net.lomeli.magiks.items.tools.ItemMiningWands;
import net.lomeli.magiks.lib.ItemIDs;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModItemsMagiks
{	
    //Tools
    public static Item neonitePick, chestLinker, weatherRockets, 
    	burningUpgrade, smeltingUpgrade, grindingPick;
    
    //crafting materails
    public static Item mistPanel, deprivedDust, electroicCircuit, 
    	ironBand, ironStick, ironPlate, copperWire, wirelessReciever,
    	advReciever;
    
    //Magik items
    public static Item darkMatter, emeraldAmulet, levelingSword,
    	enchantedDiamond, flyingRing;
    
    // ores
    public static Item ingotStamatic, ingotIgnious, ingotCopper, 
    	ingotTin, ingotSilver, ingotVesi, neoniteGem;
    
    // dust
    public static Item dustIron, dustGold, dustStamatic, dustIgnious, 
    	dustCopper, dustTin, dustSilver, dustLead, dustVesi;
    
    //blueprints
    public static Item bluePrint;
    
    // wands
    public static Item basicWand, chemistWand, alchemistWand;
    public static Item pirasVarinha;
    
    public static Item diggersWandS, diggersWandI, diggersWandD;

    public static Item wand;

    public static void registerItems()
    {
        flyingRing = new ItemFlyingRing(ItemIDs.flyingRingID, "tools/flyingring")
                .setUnlocalizedName("flyingring");
        neoniteGem = new ItemGeneric(ItemIDs.enchantedGemID, "ingots/neoniteGem", true)
                .setUnlocalizedName("enchantedGem");
        enchantedDiamond = new ItemGeneric(ItemIDs.enchantedDiamondID,
                "empowereddiamond", true)
                .setUnlocalizedName("enchantedDiamond");
        ironBand = new ItemGeneric(ItemIDs.ironBandID, "ironband", false)
                .setUnlocalizedName("ironBand");
        ironStick = new ItemGeneric(ItemIDs.ironStickID, "ironstick", false)
                .setUnlocalizedName("ironStick");
        emeraldAmulet = new ItemAmulets(ItemIDs.emeraldAmuletID, "tools/amuletGold", true, 1000)
                .setUnlocalizedName("amulet");
        ingotStamatic = new ItemGeneric(ItemIDs.ingotStamaticID, "ingots/ingotstamatic",
                false).setUnlocalizedName("ingotstamatic");
        ingotIgnious = new ItemGeneric(ItemIDs.ingotIgniousID, "ingots/ingotignious",
                false).setUnlocalizedName("ingotignious");
        mistPanel = new ItemGeneric(ItemIDs.mistPanelID, "lighthungryplates",
                false).setUnlocalizedName("mistPanel");
        darkMatter = new ItemGeneric(ItemIDs.darkMatterID, "darkmatter", true)
                .setUnlocalizedName("darkmatter");
        deprivedDust = new ItemGeneric(ItemIDs.deprivedDustID,
                "deprivedglowstone", false).setUnlocalizedName("deprivedDust");
        burningUpgrade = new ItemUpgrades(ItemIDs.burningUpgradeID,
                "burningupgrade").setDescription("Increase smelting luck by 2.5%").setUnlocalizedName("burningupgrade");
        ironPlate = new ItemGeneric(ItemIDs.ironPlateID, "ironplate", false)
                .setUnlocalizedName("ironplate");
        ingotCopper = new ItemGeneric(ItemIDs.ingotCopperID, "ingots/ingotCopper", false)
        		.setUnlocalizedName("ingotCopper");
        ingotTin = new ItemGeneric(ItemIDs.ingotTinID, "ingots/ingotTin", false)
			.setUnlocalizedName("ingotTin");
        ingotSilver = new ItemGeneric(ItemIDs.ingotSilverID, "ingots/ingotSilver", false)
			.setUnlocalizedName("ingotSilver");
        chestLinker = new ItemChestLink(ItemIDs.chestLinkerID, "tools/chestLinker")
        	.setUnlocalizedName("chestLinker");
        electroicCircuit = new ItemGeneric(ItemIDs.circuitID, "electronicCircuit", false)
        	.setUnlocalizedName("electronicCircuit");
        copperWire = new ItemGeneric(ItemIDs.copperWireID, "copperWire", false)
        	.setUnlocalizedName("copperWire");
        wirelessReciever = new ItemGeneric(ItemIDs.wirelessRecieverID, "wirelessReciever", false)
        	.setUnlocalizedName("wirelessReciever");
        advReciever = new ItemGeneric(ItemIDs.advRecieverID, "advWirelessReciever", true)
        	.setUnlocalizedName("advReciever");
        smeltingUpgrade = new ItemUpgrades(ItemIDs.smeltingUpgradeID, "speedUpgrade")
        	.setDescription("Speeds up smelting by 5 ticks").setUnlocalizedName("speed");
        
        weatherRockets = new ItemWeatherRockets(ItemIDs.weatherRocketsID, "rockets")
        	.setUnlocalizedName("rockets");
        
        wand = new ItemGeneric(ItemIDs.wandID, "magicianwand", false)
            .setCreativeTab(null).setUnlocalizedName("wand");

        basicWand = new ItemWands(ItemIDs.basicWandID, "tools/basicwand", false, 50)
                .setUnlocalizedName("basicwand");
        chemistWand = new ItemWands(ItemIDs.chemistWandID, "tools/chemistwand", false,
                100).setUnlocalizedName("chemistwand");
        alchemistWand = new ItemWands(ItemIDs.alchemistWandID, "tools/alchemistwand",
                true, 300).setUnlocalizedName("alchemistwand");

        neonitePick = new ItemEmpoweredPick(ItemIDs.empoweredPickID,
                EnumToolMaterial.IRON, "tools/pickaxeEmpowered")
                .setUnlocalizedName("empoweredPick");
        levelingSword = new ItemLevelingSword(ItemIDs.spencerSwordID, EnumToolMaterial.IRON,
                "tools/levelingsword").setUnlocalizedName("lvlSword");
        pirasVarinha = new ItemPirasVarinha(ItemIDs.pirasVarinhaID,
                "tools/pirasvarinha", false).setUnlocalizedName("pirasvarinha");
        diggersWandS = new ItemMiningWands(ItemIDs.diggersWandID, "tools/diggerswand",
                EnumWandStrength.STONE).setUnlocalizedName("diggerswandS");
        diggersWandI = new ItemMiningWands(ItemIDs.diggersWandIronID, "tools/diggerswand",
        		EnumWandStrength.IRON).setUnlocalizedName("diggersWandI");
        diggersWandD = new ItemMiningWands(ItemIDs.diggersWandDiamondID, "tools/diggerswand",
        		EnumWandStrength.DIAMOND).setUnlocalizedName("diggersWandD");
        bluePrint = new ItemMachineBluePrints(ItemIDs.bluePrintID)
        	.setUnlocalizedName("blueprints");
        ingotVesi = new ItemGeneric(ItemIDs.ingotVesiID, "ingots/ingotVesi", false)
        	.setUnlocalizedName("vesiingot");
        dustIron = new ItemGeneric(ItemIDs.dustIronID, "dusts/irondust", false)
        	.setUnlocalizedName("dustiron");
        dustGold = new ItemGeneric(ItemIDs.dustGoldID, "dusts/golddust", false)
        	.setUnlocalizedName("dustgold");
        dustStamatic = new ItemGeneric(ItemIDs.dustStamaticID, "dusts/stamaticdust", false)
        	.setUnlocalizedName("duststamatic");
        dustIgnious = new ItemGeneric(ItemIDs.dustIgniousID, "dusts/igniousdust", false)
        	.setUnlocalizedName("dustignious");
        dustCopper = new ItemGeneric(ItemIDs.dustCopperID, "dusts/copperdust", false)
        	.setUnlocalizedName("dustcopper");
        dustTin = new ItemGeneric(ItemIDs.dustTinID, "dusts/tindust", false)
    		.setUnlocalizedName("dusttin");
        dustSilver = new ItemGeneric(ItemIDs.dustSilverID, "dusts/silverdust", false)
    		.setUnlocalizedName("dustsilver");
        dustLead = new ItemGeneric(ItemIDs.dustLeadID, "dusts/leaddust", false)
    		.setUnlocalizedName("dustlead");
        dustVesi = new ItemGeneric(ItemIDs.dustVesiID, "dusts/vesidust", false)
        	.setUnlocalizedName("dustvesi");
        grindingPick = new ItemGeneric(ItemIDs.grindingPickID, "tools/grindingPick", false)
        	.setMaxDamage(5).setMaxStackSize(1).setUnlocalizedName("grindingPick");
        
        OreDictionary.registerOre("ingotCopper", ingotCopper);
        OreDictionary.registerOre("ingotTin", ingotTin);
        OreDictionary.registerOre("ingotSilver", ingotSilver);
        
        OreDictionary.registerOre("copperWire", copperWire);
        OreDictionary.registerOre("electronicCircuit", electroicCircuit);
        
        OreDictionary.registerOre("dustIron", dustIron);
        OreDictionary.registerOre("dustGold", dustGold);
        OreDictionary.registerOre("dustCopper", dustCopper);
        OreDictionary.registerOre("dustTin", dustTin);
        OreDictionary.registerOre("dustSilver", dustSilver);
        OreDictionary.registerOre("dustLead", dustLead);

        LanguageRegistry.addName(flyingRing, "ShadowWing's Gale");
        LanguageRegistry.addName(neoniteGem, "Neonite Gem");
        LanguageRegistry.addName(neonitePick, "Neonite Pickaxe");
        LanguageRegistry.addName(enchantedDiamond, "Enchanted Diamond");
        LanguageRegistry.addName(levelingSword, "Sir Spencer's Blade");
        LanguageRegistry.addName(ironBand, "Iron Band");
        LanguageRegistry.addName(ironStick, "Iron Stick");
        LanguageRegistry.addName(emeraldAmulet, "Emerald Amulet");
        LanguageRegistry.addName(ingotStamatic, "Stamatic Ingot");
        LanguageRegistry.addName(ingotIgnious, ToolTipUtil.RED + "Ignious Ingot");
        LanguageRegistry.addName(pirasVarinha, ToolTipUtil.RED + "Piras Varinha");
        LanguageRegistry.addName(diggersWandS, ToolTipUtil.YELLOW + "Digger's Wand");
        LanguageRegistry.addName(diggersWandI, ToolTipUtil.ORANGE + "Digger's Wand");
        LanguageRegistry.addName(diggersWandD, ToolTipUtil.CYAN + "Digger's Wand");
        LanguageRegistry.addName(mistPanel, "Light Hungry Plates");
        LanguageRegistry.addName(darkMatter, "Dark Matter");
        LanguageRegistry.addName(deprivedDust, "Deprived Glowstone");
        LanguageRegistry.addName(basicWand, "Basic Wand");
        LanguageRegistry.addName(chemistWand, "Chemist's Wand");
        LanguageRegistry.addName(alchemistWand, "Alchemist's Wand");
        LanguageRegistry.addName(ironPlate, "Iron Plate");
        LanguageRegistry.addName(burningUpgrade, ToolTipUtil.LIGHT_RED + "Burning Upgrade");
        LanguageRegistry.addName(wand, "Magician's Wand");
        LanguageRegistry.addName(ingotCopper, "Copper Ingot");
        LanguageRegistry.addName(ingotTin, "Tin Ingot");
        LanguageRegistry.addName(ingotSilver, "Silver Ingot");
        LanguageRegistry.addName(chestLinker, "Chest Linker");
        LanguageRegistry.addName(electroicCircuit, "Electronic Circuit");
        LanguageRegistry.addName(copperWire, "Copper Wire");
        LanguageRegistry.addName(smeltingUpgrade, ToolTipUtil.CYAN + "Smelting Upgrade");
        LanguageRegistry.addName(grindingPick, "Grinding Pick");
        
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 0),  "Sunny Rocket");
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 1),  "Morning Rocket");
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 2),  "Noctornal Rocket");
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 3),  "Dark Rocket");
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 4),  "Cloudy Rocket");
        LanguageRegistry.addName(new ItemStack(weatherRockets, 1, 5),  "Ender Rocket");
        
        LanguageRegistry.addName(wirelessReciever, "Wireless Reciever");
        LanguageRegistry.addName(advReciever, "Advanced Wireless Reciever");
        
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 0), "Large Mist Coil BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 1), "Atmospheric Disruptor BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 2), "Adv. Ore Crusher BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 3), "Area Detoxifier BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 4), "Matter Generator BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 5), "Lava Generator BluePrint");
        LanguageRegistry.addName(new ItemStack(bluePrint, 1, 6), "Matter Condenser BluePrint");
        
        LanguageRegistry.addName(ingotVesi, "Vesi Ingot");
        LanguageRegistry.addName(dustIron, "Iron Dust");
        LanguageRegistry.addName(dustGold, "Gold Dust");
        LanguageRegistry.addName(dustStamatic, "Stamatic Dust");
        LanguageRegistry.addName(dustIgnious, "Ignious Dust");
        LanguageRegistry.addName(dustCopper, "Copper Dust");
        LanguageRegistry.addName(dustTin, "Tin Dust");
        LanguageRegistry.addName(dustSilver, "Silver Dust");
        LanguageRegistry.addName(dustLead, "Lead Dust");
        LanguageRegistry.addName(dustVesi, "Vesi Dust");
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
        MagiksArrays.damageOnCraft.add(new ItemStack(grindingPick));

        MagiksArrays.wands.add(new ItemStack(basicWand));
        MagiksArrays.wands.add(new ItemStack(chemistWand));
        MagiksArrays.wands.add(new ItemStack(alchemistWand));
    }
}

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

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModItemsMagiks
{	
	public static String[] foodNames = { "Lean Steak", "Lean Pork", "Lean Chicken", "DeBoned Fish", 
    	"Enriched Apple", "Seedless Melon", "Peeled Carrot", "Peeled Potato", "Pink Sludge", "Pumken Slice", 
    	"Soylent Green" };
	public static String[] rockets = { "Sunny Rocket", "Morning Rocket", 
    	"Noctornal Rocket", "Dark Rocket", "Cloudy Rocket", "Ender Rocket"};
	public static String[] bluePrints = { "Large Mist Coil BluePrint", "Atmospheric Disruptor BluePrint", 
		"Adv. Ore Crusher BluePrint", "Area Detoxifier BluePrint", "Matter Generator BluePrint", 
		"Lava Generator BluePrint", "Matter Condenser BluePrint" };
	public static String[] dustNames = { "Iron Dust", "Gold Dust", "Stamatic Dust", "Ignious Dust", 
		"Copper Dust", "Tin Dust", "Silver Dust", "Lead Dust", "Vesi Dust"};
	
    //Tools
    public static Item neonitePick, chestLinker, 
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
    public static Item ingotDust;
    public static ItemStack dustIron, dustGold, dustStamatic, dustIgnious, 
    	dustCopper, dustTin, dustSilver, dustLead, dustVesi;
    
    //blueprints
    public static Item bluePrint;
    public static ItemStack bpCoil, bpAtmos, bpOre, bpArea, 
    	bpGen, bpLava, bpMatter;
    
    //Rockets
    public static Item weatherRockets;
    public static ItemStack sunRocket, mornRocket, nocRocket,
    	darkRocket, cloudRocket, endRocket;
    
    // wands
    public static Item basicWand, chemistWand, alchemistWand;
    public static Item pirasVarinha;
    
    public static Item diggersWandS, diggersWandI, diggersWandD;

    public static Item wand;
    
    //Food
    public static Item processedFood;
    public static ItemStack pSteak, pPork, pChicken, pFish, pApple, 
    	pMelon, pCarrot, pPotato, pSludge, pPumpkin, soylentGreen;

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
        
        ingotVesi = new ItemGeneric(ItemIDs.ingotVesiID, "ingots/ingotVesi", false)
        	.setUnlocalizedName("vesiingot");
        grindingPick = new ItemGeneric(ItemIDs.grindingPickID, "tools/grindingPick", false)
        	.setMaxDamage(5).setMaxStackSize(1).setUnlocalizedName("grindingPick");
        
        OreDictionary.registerOre("ingotCopper", ingotCopper);
        OreDictionary.registerOre("ingotTin", ingotTin);
        OreDictionary.registerOre("ingotSilver", ingotSilver);
        
        OreDictionary.registerOre("copperWire", copperWire);
        OreDictionary.registerOre("electronicCircuit", electroicCircuit);
        
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
        
        LanguageRegistry.addName(wirelessReciever, "Wireless Reciever");
        LanguageRegistry.addName(advReciever, "Advanced Wireless Reciever");
        
        LanguageRegistry.addName(ingotVesi, "Vesi Ingot");
        
        // Meta Data items
        ingotDust = new ItemDusts(ItemIDs.dustIngotsID);
        GameRegistry.registerItem(ingotDust, "dusts");
        for(int i = 0; i < 9; i++)
        {
        	LanguageRegistry.addName(new ItemStack(ingotDust , 1, i), dustNames[i]);
        }
        
        dustIron = new ItemStack(ingotDust, 1, 0);
        dustGold = new ItemStack(ingotDust, 1, 1);
        dustStamatic = new ItemStack(ingotDust, 1, 2);
        dustIgnious  = new ItemStack(ingotDust, 1, 3);
    	dustCopper = new ItemStack(ingotDust, 1, 4);
    	dustTin = new ItemStack(ingotDust, 1, 5);
    	dustSilver = new ItemStack(ingotDust, 1, 6);
    	dustLead = new ItemStack(ingotDust, 1, 7);
    	dustVesi = new ItemStack(ingotDust, 1, 8);
    	
    	OreDictionary.registerOre("dustIron", dustIron);
        OreDictionary.registerOre("dustGold", dustGold);
        OreDictionary.registerOre("dustCopper", dustCopper);
        OreDictionary.registerOre("dustTin", dustTin);
        OreDictionary.registerOre("dustSilver", dustSilver);
        OreDictionary.registerOre("dustLead", dustLead);
        
        weatherRockets = new ItemWeatherRockets(ItemIDs.weatherRocketsID, "rockets").setUnlocalizedName("rockets");
        GameRegistry.registerItem(weatherRockets, "rockets");
        for(int i = 0; i < 6; i++)
        {
        	LanguageRegistry.addName(new ItemStack(weatherRockets, 1, i),  rockets[i]);
        }
        
        sunRocket = new ItemStack(weatherRockets, 1, 0);
        mornRocket = new ItemStack(weatherRockets, 1, 1); 
        nocRocket = new ItemStack(weatherRockets, 1, 2);
    	darkRocket = new ItemStack(weatherRockets, 1, 3);
    	cloudRocket = new ItemStack(weatherRockets, 1, 4);
    	endRocket = new ItemStack(weatherRockets, 1, 5);
        
    	bluePrint = new ItemMachineBluePrints(ItemIDs.bluePrintID).setUnlocalizedName("bluePrint");
    	GameRegistry.registerItem(bluePrint, "bluePrint");
    	for(int i = 0; i < 7; i++)
    	{
    		LanguageRegistry.addName(new ItemStack(bluePrint, 1, i), bluePrints[i]);
    	}
    	
    	bpCoil = new ItemStack(bluePrint, 1, 0);
    	bpAtmos = new ItemStack(bluePrint, 1, 1);
    	bpOre = new ItemStack(bluePrint, 1, 2);
    	bpArea = new ItemStack(bluePrint, 1, 3);
    	bpGen = new ItemStack(bluePrint, 1, 4);
    	bpLava = new ItemStack(bluePrint, 1, 5);
    	bpMatter = new ItemStack(bluePrint, 1, 6);
    	
        processedFood = new ItemProcessedFood(9000).setUnlocalizedName("processedFood");
        GameRegistry.registerItem(processedFood, "processedFood");
        for(int i = 0; i < 11; i++)
        {
        	LanguageRegistry.addName(new ItemStack(processedFood, 1, i), foodNames[i]);
        }
        
        pSteak = new ItemStack(processedFood, 1, 0);
        pPork = new ItemStack(processedFood, 1, 1);
        pChicken = new ItemStack(processedFood, 1, 2);
        pFish = new ItemStack(processedFood, 1, 3);
        pApple = new ItemStack(processedFood, 1, 4);
        pMelon = new ItemStack(processedFood, 1, 5);
        pCarrot = new ItemStack(processedFood, 1, 6);
        pPotato = new ItemStack(processedFood, 1, 7);
        pSludge = new ItemStack(processedFood, 1, 8);
        pPumpkin = new ItemStack(processedFood, 1, 9);
        soylentGreen = new ItemStack(processedFood, 1, 10);
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

package com.lomeli.magiks.items;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.lomeli.magiks.api.libs.MagiksArrays;
import com.lomeli.magiks.blocks.ModBlocksMagiks;
import com.lomeli.magiks.items.magik.ItemAmulets;
import com.lomeli.magiks.items.magik.ItemFlyingRing;
import com.lomeli.magiks.items.magik.ItemPirasVarinha;
import com.lomeli.magiks.items.magik.ItemWands;
import com.lomeli.magiks.items.tools.ItemEmpoweredPick;
import com.lomeli.magiks.items.tools.ItemLevelingSword;
import com.lomeli.magiks.items.tools.ItemMiningWands;
import com.lomeli.magiks.lib.Ints;

import cpw.mods.fml.common.registry.GameRegistry;
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
    public static Item ingotStamatic;
    public static Item ingotIgnious;
    public static Item darkMatter;
    public static Item mistPanel;
    public static Item deprivedDust;
    public static Item burningUpgrade;
    public static Item ironPlate;

    // wands
    public static Item basicWand;
    public static Item chemistWand;
    public static Item alchemistWand;
    public static Item pirasVarinha;
    public static Item diggersWand;

    public static Item wand;

    public static void registerItems()
    {
        flyingRing = new ItemFlyingRing(Ints.flyingRingID, "flyingring")
                .setUnlocalizedName("flyingring");
        neoniteGem = new ItemGeneric(Ints.enchantedGemID, "neoniteGem", true)
                .setUnlocalizedName("enchantedGem");
        enchantedDiamond = new ItemGeneric(Ints.enchantedDiamondID,
                "empowereddiamond", true)
                .setUnlocalizedName("enchantedDiamond");
        ironBand = new ItemGeneric(Ints.ironBandID, "ironband", false)
                .setUnlocalizedName("ironBand");
        ironStick = new ItemGeneric(Ints.ironStickID, "ironstick", false)
                .setUnlocalizedName("ironStick");
        emeraldAmulet = new ItemAmulets(9001, "amuletGold", true, 50000)
                .setUnlocalizedName("amulet");
        ingotStamatic = new ItemGeneric(Ints.ingotStamaticID, "ingotstamatic",
                false).setUnlocalizedName("ingotstamatic");
        ingotIgnious = new ItemGeneric(Ints.ingotIgniousID, "ingotignious",
                false).setUnlocalizedName("ingotignious");
        mistPanel = new ItemGeneric(Ints.mistPanelID, "lighthungryplates",
                false).setUnlocalizedName("mistPanel");
        darkMatter = new ItemGeneric(Ints.darkMatterID, "darkmatter", true)
                .setUnlocalizedName("darkmatter");
        deprivedDust = new ItemGeneric(Ints.deprivedDustID,
                "deprivedglowstone", false).setUnlocalizedName("deprivedDust");
        burningUpgrade = new ItemGeneric(Ints.burningUpgradeID,
                "burningupgrade", false).setUnlocalizedName("burningupgrade")
                .setMaxStackSize(2);
        ironPlate = new ItemGeneric(Ints.ironPlateID, "ironplate", false)
                .setUnlocalizedName("ironplate");
        wand = new ItemGeneric(Ints.wandID, "magicianwand", false)
            .setCreativeTab(null).setUnlocalizedName("wand");

        basicWand = new ItemWands(Ints.basicWandID, "basicwand", false, 50)
                .setUnlocalizedName("basicwand");
        chemistWand = new ItemWands(Ints.chemistWandID, "chemistwand", false,
                100).setUnlocalizedName("chemistwand");
        alchemistWand = new ItemWands(Ints.alchemistWandID, "alchemistwand",
                true, 300).setUnlocalizedName("alchemistwand");

        neonitePick = new ItemEmpoweredPick(Ints.empoweredPickID,
                EnumToolMaterial.IRON, "pickaxeEmpowered")
                .setUnlocalizedName("empoweredPick");
        levelingSword = new ItemLevelingSword(9000, EnumToolMaterial.IRON,
                "levelingsword").setUnlocalizedName("lvlSword");
        pirasVarinha = new ItemPirasVarinha(Ints.pirasVarinhaID,
                "pirasvarinha", false).setUnlocalizedName("pirasvarinha");
        diggersWand = new ItemMiningWands(Ints.diggersWandID, "diggerswand",
                false, 300, 0).setUnlocalizedName("diggerswand");

        LanguageRegistry.addName(flyingRing, "ShadowWing's Gale");
        LanguageRegistry.addName(neoniteGem, "Neonite Gem");
        LanguageRegistry.addName(neonitePick, "Neonite Pickaxe");
        LanguageRegistry.addName(enchantedDiamond, "Enchanted Diamond");
        LanguageRegistry.addName(levelingSword, "Sir Spencer's Blade");
        LanguageRegistry.addName(ironBand, "Iron Band");
        LanguageRegistry.addName(ironStick, "Iron Stick");
        LanguageRegistry.addName(emeraldAmulet, "Emerald Amulet");
        LanguageRegistry.addName(ingotStamatic, "Stamatic Ingot");
        LanguageRegistry.addName(ingotIgnious, "Ignious Ingot");
        LanguageRegistry.addName(pirasVarinha, "Piras Varinha");
        LanguageRegistry.addName(diggersWand, "Digger's Wand");
        LanguageRegistry.addName(mistPanel, "Light Hungry Plates");
        LanguageRegistry.addName(darkMatter, "Dark Matter");
        LanguageRegistry.addName(deprivedDust, "Deprived Glowstone");
        LanguageRegistry.addName(basicWand, "Basic Wand");
        LanguageRegistry.addName(chemistWand, "Chemist's Wand");
        LanguageRegistry.addName(alchemistWand, "Alchemist's Wand");
        LanguageRegistry.addName(ironPlate, "Iron Plate");
        LanguageRegistry.addName(burningUpgrade, "Burning Upgrade");
        LanguageRegistry.addName(wand, "Magician's Wand");
    }

    public static void registerItemRecipes()
    {
        GameRegistry
                .addRecipe(new ItemStack(enchantedDiamond, 1), new Object[] {
                        "GEG", "EDE", "RER", 'G', Item.lightStoneDust, 'R',
                        Item.redstone, 'D', Item.diamond, 'E', neoniteGem });
        GameRegistry.addRecipe(new ItemStack(ironStick, 2), new Object[] {
                "  I", " I ", "I  ", 'I', Item.ingotIron });
        GameRegistry.addRecipe(new ItemStack(ironBand, 1), new Object[] {
                " I ", "IBI", " I ", 'I', ironStick, 'B', Item.bucketLava });
        GameRegistry.addRecipe(new ItemStack(flyingRing, 1), new Object[] {
                "GLR", "DID", "FEF", 'G', enchantedDiamond, 'D', Item.diamond,
                'L', Item.lightStoneDust, 'E', Item.enderPearl, 'I', ironBand,
                'R', Item.redstone, 'F', Item.feather });
        GameRegistry.addRecipe(new ItemStack(darkMatter, 1), new Object[] {
                "GMG", "MOM", "GMG", 'G', neoniteGem, 'M',
                ModBlocksMagiks.manceryBlock, 'O', enchantedDiamond });
        GameRegistry.addShapelessRecipe(new ItemStack(deprivedDust, 1),
                new Object[] { Item.lightStoneDust, Block.slowSand,
                        ingotStamatic });
        GameRegistry.addRecipe(new ItemStack(mistPanel, 1), new Object[] {
                "RDR", "DSD", "III", 'R', Item.redstone, 'S',
                Block.daylightSensor, 'D', deprivedDust, 'I', ironPlate });
        GameRegistry.addShapelessRecipe(new ItemStack(ironPlate, 1),
                new Object[] { Item.ingotIron, Item.netherQuartz,
                        Block.stoneSingleSlab });
        GameRegistry.addRecipe(new ItemStack(burningUpgrade, 1), new Object[] {
                " F ", "BEB", "III", 'B', Item.blazePowder, 'E',
                Item.eyeOfEnder, 'I', ironPlate, 'F',Item.emerald });

        GameRegistry.addRecipe(new ItemStack(neonitePick, 1), new Object[] {
                "GDG", "EIE", " I ", 'G', enchantedDiamond, 'D',
                Item.pickaxeDiamond, 'E', neoniteGem, 'I', ironStick });
        GameRegistry.addRecipe(new ItemStack(pirasVarinha, 1), new Object[] {
                "GNG", "IGI", "IWI", 'G', Item.ingotGold, 'N',
                enchantedDiamond, 'I', ingotIgnious, 'W',
                new ItemStack(alchemistWand, 1, -1) });
        GameRegistry.addRecipe(new ItemStack(levelingSword), new Object[] {
                " I ", " I ", "GDG", 'I', Item.ingotIron, 'G', neoniteGem, 'D',
                darkMatter });

        GameRegistry.addRecipe(new ItemStack(basicWand, 1), new Object[] {
                " IE", "ISI", "RI ", 'E', Item.emerald, 'R', Item.redstone,
                'S', Item.stick, 'I', ingotStamatic });
        GameRegistry.addRecipe(new ItemStack(chemistWand, 1), new Object[] {
                " IE", "ISI", "RI ", 'E', Item.lightStoneDust, 'R',
                Item.diamond, 'S', basicWand, 'I', ingotStamatic });
        GameRegistry.addRecipe(new ItemStack(alchemistWand, 1), new Object[] {
                " IE", "ISI", "RI ", 'E', enchantedDiamond, 'R', darkMatter,
                'S', chemistWand, 'I', ingotStamatic });
        
        GameRegistry.addRecipe(new ItemStack(wand, 1), new Object[]
                {
                    " CB","CSC","BC ", 'S',Item.stick, 'C',Item.coal,
                    'B',(new ItemStack(Item.dyePowder, 1, 15))
                });
    }

    public static void registerFurnaceRecipes()
    {
        GameRegistry.addSmelting(ModBlocksMagiks.stamaticOre.blockID,
                new ItemStack(ingotStamatic), 5);
        GameRegistry.addSmelting(ModBlocksMagiks.igniousOre.blockID,
                new ItemStack(ingotIgnious), 10);
    }

    public static void addChargeableItems()
    {
        MagiksArrays.rechargeableItems.add(new ItemStack(flyingRing));
        MagiksArrays.rechargeableItems.add(new ItemStack(pirasVarinha));
        MagiksArrays.rechargeableItems.add(new ItemStack(emeraldAmulet));
        MagiksArrays.rechargeableItems.add(new ItemStack(diggersWand));
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

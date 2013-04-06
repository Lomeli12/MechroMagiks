package com.lomeli.magiks.items;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.lomeli.magiks.blocks.ModBlocksMagiks;
import com.lomeli.magiks.items.magik.ItemAmulets;
import com.lomeli.magiks.items.magik.ItemFlyingRing;
import com.lomeli.magiks.items.tools.ItemEmpoweredPick;
import com.lomeli.magiks.items.tools.ItemLevelingSword;
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

        neonitePick = new ItemEmpoweredPick(Ints.empoweredPickID,
                EnumToolMaterial.IRON, "pickaxeEmpowered")
                .setUnlocalizedName("empoweredPick");
        levelingSword = new ItemLevelingSword(9000, EnumToolMaterial.IRON,
                "pickaxeEmpowered").setUnlocalizedName("lvlSword");

        LanguageRegistry.addName(flyingRing, "Flying Ring");
        LanguageRegistry.addName(neoniteGem, "Neonite Gem");
        LanguageRegistry.addName(neonitePick, "Neonite Pickaxe");
        LanguageRegistry.addName(enchantedDiamond, "Enchanted Diamond");
        LanguageRegistry.addName(levelingSword, "RPGer's Sword");
        LanguageRegistry.addName(ironBand, "Iron Band");
        LanguageRegistry.addName(ironStick, "Iron Stick");
        LanguageRegistry.addName(emeraldAmulet, "Emerald Amulet");
        LanguageRegistry.addName(ingotStamatic, "Stamatic Ingot");
    }

    public static void registerItemRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(levelingSword, 1), new Object[] {
                "S", "S", "S", 'S', Item.stick });
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

        GameRegistry.addRecipe(new ItemStack(neonitePick, 1), new Object[] {
                "GDG", "EIE", " I ", 'G', enchantedDiamond, 'D',
                Item.pickaxeDiamond, 'E', neoniteGem, 'I', ironStick });
    }

    public static void registerFurnaceRecipes()
    {
        GameRegistry.addSmelting(ModBlocksMagiks.stamaticOre.blockID,
                (new ItemStack(ingotStamatic)), 5);
    }
}

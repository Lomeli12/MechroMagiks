package com.lomeli.magiks.api.libs;

/**
 * If you want you items to be able to charge using the various generators, or
 * be affected by certain items, add them to the various arrays
 * 
 * Example:
 * public static Item newMagikItem = new Item(900).setUnlocalizedName("newitem");
 * LanguageRegistry.addName(newMagikItem, "Magik Item);
 * 
 * rechargeableItems.add(newMagikItem);
 * 
 * MagiksArrays.
 * 
 */
import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class MagiksArrays
{
    /**
     * Add any items that can be recharged with Mist to this array.
     */
    public static List<ItemStack> rechargeableItems = new ArrayList<ItemStack>();

    /**
     * Adding items to this array will allow them to be converted
     * to Mist via the Kinetic Generator.
     */
    public static List<ItemStack> kineticGenFuel = new ArrayList<ItemStack>();
    
    /**
     * For now, any items added to this array will recharge the flying ring by 50
     */
    public static List<ItemStack> flyingRingFuel = new ArrayList<ItemStack>();
    
    /**
     * Damages items when used in crafting recipe
     */
    public static List<ItemStack> damageOnCraft = new ArrayList<ItemStack>();
}

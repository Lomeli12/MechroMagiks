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
    public static List<ItemStack> rechargeableItems = new ArrayList<ItemStack>();

    public static List<ItemStack> kineticGenFuel = new ArrayList<ItemStack>();
}

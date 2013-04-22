package net.lomeli.magiks.addons.ee3;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;

public class EE3ItemAPI 
{
	public static ItemStack getBlock(String itemString, int meta)
    {
        ItemStack item = null;

        try
        {
            String itemClass = "com.pahimar.ee3.block.ModBlocks";
            Object obj = Class.forName(itemClass).getField(itemString)
                    .get(null);
            if (obj instanceof Block)
            {
                item = new ItemStack((Block) obj, 1, meta);
            } else if (obj instanceof ItemStack)
            {
                item = (ItemStack) obj;
            }
        } catch (Exception ex)
        {
            FMLLog.warning("[MechroMagik] Could not retrieve block identified by: "
                    + itemString);
        }
        return item;
    }

    public static ItemStack getBlock(String itemString)
    {
        ItemStack item = null;

        try
        {
            String itemClass = "com.pahimar.ee3.block.ModBlocks";
            Object obj = Class.forName(itemClass).getField(itemString)
                    .get(null);
            if (obj instanceof Block)
            {
                item = new ItemStack((Block) obj);
            } else if (obj instanceof ItemStack)
            {
                item = (ItemStack) obj;
            }
        } catch (Exception ex)
        {
            FMLLog.warning("[MechroMagik] Could not retrieve block identified by: "
                    + itemString);
        }
        return item;
    }
    
    public static ItemStack getItem(String itemString, int meta)
    {
        ItemStack item = null;

        try
        {
            String itemClass = "com.pahimar.ee3.item.ModItems";
            Object obj = Class.forName(itemClass).getField(itemString)
                    .get(null);
            if (obj instanceof Item)
            {
                item = new ItemStack((Item) obj, 1, meta);
            } else if (obj instanceof ItemStack)
            {
                item = (ItemStack) obj;
            }
        } catch (Exception ex)
        {
            FMLLog.warning("[MechroMagik] Could not retrieve item identified by: "
                    + itemString);
        }
        return item;
    }

    public static ItemStack getItem(String itemString)
    {
        ItemStack item = null;

        try
        {
            String itemClass = "com.pahimar.ee3.item.ModItems";
            Object obj = Class.forName(itemClass).getField(itemString)
                    .get(null);
            if (obj instanceof Item)
            {
                item = new ItemStack((Item) obj);
            } else if (obj instanceof ItemStack)
            {
                item = (ItemStack) obj;
            }
        } catch (Exception ex)
        {
            FMLLog.warning("[MechroMagik] Could not retrieve item identified by: "
                    + itemString);
        }
        return item;
    }

}

package com.lomeli.magiks.client.gui.slot;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import cpw.mods.fml.common.registry.GameRegistry;

//Just copied the code for regular crafting table, will change when i need to
public class SlotMistCrafting extends Slot
{
    private final IInventory craftMatrix;

    private EntityPlayer thePlayer;

    private int amountCrafted;

    public SlotMistCrafting(EntityPlayer player, IInventory inventory,
            IInventory craftResult, int par2, int par3, int par4)
    {
        super(inventory, par2, par3, par4);
        thePlayer = player;
        craftMatrix = inventory;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            amountCrafted += Math.min(par1, this.getStack().stackSize);
        }

        return super.decrStackSize(par1);
    }

    @Override
    protected void onCrafting(ItemStack par1ItemStack, int par2)
    {
        amountCrafted += par2;
        this.onCrafting(par1ItemStack);
    }

    @Override
    protected void onCrafting(ItemStack par1ItemStack)
    {
        par1ItemStack.onCrafting(thePlayer.worldObj, thePlayer, amountCrafted);
        amountCrafted = 0;

        if (par1ItemStack.itemID == Block.workbench.blockID)
        {
            thePlayer.addStat(AchievementList.buildWorkBench, 1);
        } else if (par1ItemStack.itemID == Item.pickaxeWood.itemID)
        {
            thePlayer.addStat(AchievementList.buildPickaxe, 1);
        } else if (par1ItemStack.itemID == Block.furnaceIdle.blockID)
        {
            thePlayer.addStat(AchievementList.buildFurnace, 1);
        } else if (par1ItemStack.itemID == Item.hoeWood.itemID)
        {
            thePlayer.addStat(AchievementList.buildHoe, 1);
        } else if (par1ItemStack.itemID == Item.bread.itemID)
        {
            thePlayer.addStat(AchievementList.makeBread, 1);
        } else if (par1ItemStack.itemID == Item.cake.itemID)
        {
            thePlayer.addStat(AchievementList.bakeCake, 1);
        } else if (par1ItemStack.itemID == Item.pickaxeStone.itemID)
        {
            thePlayer.addStat(AchievementList.buildBetterPickaxe, 1);
        } else if (par1ItemStack.itemID == Item.swordWood.itemID)
        {
            thePlayer.addStat(AchievementList.buildSword, 1);
        } else if (par1ItemStack.itemID == Block.enchantmentTable.blockID)
        {
            thePlayer.addStat(AchievementList.enchantments, 1);
        } else if (par1ItemStack.itemID == Block.bookShelf.blockID)
        {
            thePlayer.addStat(AchievementList.bookcase, 1);
        }
    }

    @Override
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer,
            ItemStack par2ItemStack)
    {
        GameRegistry
                .onItemCrafted(par1EntityPlayer, par2ItemStack, craftMatrix);
        this.onCrafting(par2ItemStack);

        for (int var3 = 0; var3 < craftMatrix.getSizeInventory(); ++var3)
        {
            ItemStack var4 = craftMatrix.getStackInSlot(var3);

            if (var4 != null)
            {
                craftMatrix.decrStackSize(var3, 1);

                if (var4.getItem().hasContainerItem())
                {
                    ItemStack var5 = var4.getItem().getContainerItemStack(var4);

                    if (var5.isItemStackDamageable()
                            && var5.getItemDamage() > var5.getMaxDamage())
                    {
                        MinecraftForge.EVENT_BUS
                                .post(new PlayerDestroyItemEvent(thePlayer,
                                        var5));
                        var5 = null;
                    }

                    if (var5 != null
                            && (!var4.getItem()
                                    .doesContainerItemLeaveCraftingGrid(var4) || !thePlayer.inventory
                                    .addItemStackToInventory(var5)))
                    {
                        if (craftMatrix.getStackInSlot(var3) == null)
                        {
                            craftMatrix.setInventorySlotContents(var3, var5);
                        } else
                        {
                            thePlayer.dropPlayerItem(var5);
                        }
                    }
                }
            }
        }
    }

}

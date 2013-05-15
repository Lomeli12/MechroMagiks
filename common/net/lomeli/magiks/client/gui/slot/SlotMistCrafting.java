package net.lomeli.magiks.client.gui.slot;

import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.tileentity.TileEntityMancerWorkTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class SlotMistCrafting extends SlotCrafting
{
    /** The craft matrix inventory linked to this result slot. */
    private final IInventory craftMatrix;

    /** The player that is using the GUI where this slot resides. */
    private EntityPlayer thePlayer;
    
    private TileEntityMancerWorkTable tileEntity;

    public SlotMistCrafting(EntityPlayer player, IInventory inventory1, 
    		IInventory inventory2, int par4, int par5, int par6, TileEntityMancerWorkTable tile)
    {
        super(player, inventory1, inventory2, par4, par5, par6);
        this.thePlayer = player;
        this.craftMatrix = inventory1;
        this.tileEntity = tile;
    }
    
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        GameRegistry.onItemCrafted(par1EntityPlayer, par2ItemStack, craftMatrix);
        onCrafting(par2ItemStack);
        
        if(this.tileEntity.getCurrentMode() == 0)
        {
        	if(this.tileEntity.getStackInSlot(17) != null 
                			&& this.tileEntity.getStackInSlot(17).getItem() == Item.paper)
        		this.tileEntity.decrStackSize(17, 1);
        }
        else if(this.tileEntity.getCurrentMode() == 1)
        {
        	if(this.tileEntity.getStackInSlot(17) != null 
        			&& this.tileEntity.getStackInSlot(17).getItem() == ModItemsMagiks.electroicCircuit)
        		this.tileEntity.decrStackSize(17, 1);
        }
        
        for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = this.craftMatrix.getStackInSlot(i);

            if (itemstack1 != null)
            {
                this.craftMatrix.decrStackSize(i, 1);

                
                if (itemstack1.getItem().hasContainerItem())
                {
                    ItemStack itemstack2 = itemstack1.getItem().getContainerItemStack(itemstack1);

                    if (itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
                    {
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(thePlayer, itemstack2));
                        itemstack2 = null;
                    }

                    if (itemstack2 != null && (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !this.thePlayer.inventory.addItemStackToInventory(itemstack2)))
                    {
                        if (this.craftMatrix.getStackInSlot(i) == null)
                        
                        	this.craftMatrix.setInventorySlotContents(i, itemstack2);
                        else
                            this.thePlayer.dropPlayerItem(itemstack2);
                    }
                }
            }
        }
    }
}
package net.lomeli.magiks.blocks.machine;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.tileentity.TileEntityJouleBox;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockJouleBox extends BlockContainer
{	
	private int tick;
	
	public BlockJouleBox(int id)
    {
	    super(id, Material.rock);
	    this.setCreativeTab(Magiks.modTab);
    }
	
	@Override
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(ModStrings.MOD_ID + ":mysticjoule");
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
            return false;
        else
        {
        	TileEntityJouleBox tile = new TileEntityJouleBox();
        	
        	if(tile != null)
        	{
        		for(ItemStack item : MagiksArrays.wands)
        		{
        			if(player.inventory.getCurrentItem() != null &&
        					player.inventory.getCurrentItem().itemID == item.itemID)
        			{
        				tick++;
        				if(tick >= 2)
        				{
        					player.addChatMessage("Can Recieve: " + tile.getPowerProvider().getMaxEnergyReceived()
        						+ "MJ Outputting: " + tile.getMistLevel() + " Mist Heat: " + tile.getHeatLevel() + "C");
        					tick = 0;
        				}
        			}
        		}
        	}
        	return true;
        }
    }
	
	@Override
	public TileEntity createNewTileEntity(World world)
    {
		return new TileEntityJouleBox();
    }

	@Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
}

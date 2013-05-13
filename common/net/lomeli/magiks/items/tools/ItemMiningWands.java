package net.lomeli.magiks.items.tools;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.lomeli.magiks.core.handler.MiscHandler;
import net.lomeli.magiks.items.ItemGeneric;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemMiningWands extends ItemGeneric
{
    private EnumWandStrength wandStrength;
    public ItemMiningWands(int par1, String Texture, EnumWandStrength strength)
    {
        super(par1, Texture, false);
        this.setMaxDamage(strength.getDurability());
        this.wandStrength = strength;
    }
    int tick = 0;
    
    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, 
    		World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	int regBlock = world.getBlockId(x, y, z);
    	int meta = world.getBlockMetadata(x, y, z);
    	boolean result = false;
    	
    	if(Block.blocksList[regBlock] != null)
    	{
    		Block selected = Block.blocksList[regBlock];
    		int harvest = MinecraftForge.getBlockHarvestLevel(selected, meta, "pickaxe");
    		if(player.isSneaking())
    		{
    			tick++;
    			if(tick == 2)
    			{
    				switch(harvest)
    				{
    					case 0:
    						player.sendChatToPlayer("Mining Level: Wood");
    						break;
    					case 1:
    						player.sendChatToPlayer("Mining Level: Stone");
    						break;
    					case 2:
    						player.sendChatToPlayer("Mining Level: Iron");
    						break;
    					case 3:
    						player.sendChatToPlayer("Mining Level: Diamond");
    						break;
    					default:
    						player.sendChatToPlayer("Mining Level: Unknown");
    						break;
    				}
    				tick = 0;
    			}
    			
    		}
    		else
    		{
    			if(harvest <= this.wandStrength.getHarvestLevel() && selected != Block.bedrock)
    			{
    				world.destroyBlock(x, y, z, false);
    				world.notifyBlockOfNeighborChange(x, y, z, world.getBlockId(x, y, z));
    				world.markBlockForUpdate(x, y, z);
    				selected.dropBlockAsItem(world, (int)player.posX, (int)player.posY, 
    					(int)player.posZ, meta, 0);
    				itemstack.damageItem(1, player);
    			}
    		}
    	}
    	
    	return result;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
    	infoList.add("Mining Level: " + wandStrength.getName());
    	if(MiscHandler.doAdditionalInfo())
    	{
    		infoList.add("Right-Click: Insta-Mine");
    		infoList.add("SHIFT-Right-Click: Get Required Mining Level");
    	}
    	else
    	{
    		switch(this.wandStrength.getHarvestLevel())
    		{
    			case 0:
    				infoList.add(MiscHandler.additionalInfoInstructions("e"));
    				break;
    			case 1:
    				infoList.add(MiscHandler.additionalInfoInstructions("8"));
    				break;
    			case 2:
    				infoList.add(MiscHandler.additionalInfoInstructions("7"));
    				break;
    			case 3:
    				infoList.add(MiscHandler.additionalInfoInstructions("b"));
    				break;
    			default:
    				infoList.add(MiscHandler.additionalInfoInstructions("e"));
    				break;
    				
    		}
    	}
    }
}

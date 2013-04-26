package net.lomeli.magiks.api.helpers;

import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MistConnectionHelper 
{
	@SideOnly(Side.CLIENT)
	public static void renderConnection(TileEntity tileEntity_one, TileEntity tileEntity_two)
	{
		int x1 = tileEntity_one.xCoord;
		int y1 = tileEntity_one.yCoord;
		int z1 = tileEntity_one.zCoord;
		int x2 = tileEntity_two.xCoord;
		int y2 = tileEntity_two.yCoord;
		int z2 = tileEntity_two.zCoord;
		
		if(x1 > x2 && y1 > y2 && z1 > z2)
		{
			
		}
		else if(x1 < x2 && y1 < y2 && z1 < z2)
		{
			for (int x = x1; x < x2; x++) 
			{ 
			    for(int y = y1; y < y2; y++) 
			    { 
			        for ( int z = z1; z < z2; z++)
			        {
			        	tileEntity_one.worldObj.spawnParticle("largeexplode", x, y, z, 1D, 0, 0);
			        }
			    }
			}
		}
		else
		{
			
		}
	}

}

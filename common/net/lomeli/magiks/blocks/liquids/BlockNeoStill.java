package net.lomeli.magiks.blocks.liquids;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockNeoStill extends BlockFluidFinite
{

	public BlockNeoStill(int id, Fluid fluid)
    {
	    super(id, fluid, Material.water);
	    this.setCreativeTab(Magiks.modTab);
	    this.setLightValue(1.0F);
	    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

	@Override
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(ModStrings.MOD_ID + ":liquids/neo");
    }
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if(world != null && entity != null)
		{
			if(entity instanceof EntityPlayerMP)
			{
				try
				{
					((EntityPlayerMP)entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 30, 10));
					((EntityPlayerMP)entity).addPotionEffect(new PotionEffect(Potion.field_76444_x.getId(), 30, 1));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(entity instanceof EntityClientPlayerMP)
			{
				try
				{
					((EntityClientPlayerMP)entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 30, 10));
					((EntityClientPlayerMP)entity).addPotionEffect(new PotionEffect(Potion.field_76444_x.getId(), 30, 1));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(entity instanceof EntityLiving || entity instanceof EntityPlayer)
			{
				try
				{
					((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 30, 10));
					((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.field_76444_x.getId(), 30, 1));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}

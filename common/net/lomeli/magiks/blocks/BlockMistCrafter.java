package net.lomeli.magiks.blocks;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
//import com.lomeli.magiks.lib.GuiIDs;

public class BlockMistCrafter extends Block
{

    private Icon[] icons = new Icon[3];

    public BlockMistCrafter(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.setCreativeTab(Magiks.modTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        icons[0] = iconRegister.registerIcon(Strings.modID.toLowerCase() + ":customcraft\\bottom");
        icons[1] = iconRegister.registerIcon(Strings.modID.toLowerCase() + ":customcraft\\top");
        icons[2] = iconRegister.registerIcon(Strings.modID.toLowerCase() + ":customcraft\\sides");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return side == 0 ? icons[0] : side == 1 ? icons[1]
                : side != meta ? icons[2] : icons[2];
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int i, float f, float g, float t)
    {/*
      * if (player.isSneaking()) return false; else { if (!world.isRemote) {
      * player.openGui(Magiks.instance, GuiIDs.mistCraft, world, x, y, z); }
      * 
      * }
      */
        return true;
    }
}

package net.lomeli.magiks.tileentity.renderer;

import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.lib.Models;
import net.lomeli.magiks.tileentity.TileEntityLinkingChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityLinkingChestRenderer extends TileEntitySpecialRenderer {

    private ModelChest modelChest = new ModelChest();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) 
    {
        if (tileEntity instanceof TileEntityLinkingChest) 
        {
        	TileEntityLinkingChest tileLinkingChest = (TileEntityLinkingChest) tileEntity;
            ForgeDirection direction = null;

            if (tileLinkingChest.getWorldObj() != null) 
            {
                direction = ForgeDirection.getOrientation(tileLinkingChest.getBlockMetadata());
            }

            FMLClientHandler.instance().getClient().renderEngine.func_110577_a(
            		new ResourceLocation(ModStrings.MOD_ID, 
            		Models.MODEL_TEXTURES + "linkingChest.png"));
            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short angle = 0;

            if (direction != null) 
            {
                if (direction == ForgeDirection.NORTH)
                    angle = 180;
                else if (direction == ForgeDirection.SOUTH)
                    angle = 0;
                else if (direction == ForgeDirection.WEST)
                    angle = 90;
                else if (direction == ForgeDirection.EAST)
                    angle = -90;
            }

            GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float adjustedLidAngle = tileLinkingChest.prevLidAngle + (tileLinkingChest.lidAngle - tileLinkingChest.prevLidAngle) * tick;
            adjustedLidAngle = 1.0F - adjustedLidAngle;
            adjustedLidAngle = 1.0F - adjustedLidAngle * adjustedLidAngle * adjustedLidAngle;
            modelChest.chestLid.rotateAngleX = -(adjustedLidAngle * (float) Math.PI / 2.0F);
            modelChest.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}

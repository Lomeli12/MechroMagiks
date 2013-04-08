package com.lomeli.magiks.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import com.lomeli.magiks.lib.Strings;
import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;
import com.lomeli.magiks.inventory.ContainerKineticGenerator;

public class GuiKineticGenerator extends GuiContainer
{
    private TileEntityKineticGenerator tileentitykingen;
    
    public GuiKineticGenerator(InventoryPlayer par1Container, TileEntityKineticGenerator tileentitykingen)
    {
        super(new ContainerKineticGenerator(par1Container, tileentitykingen));
        ySize = 176;
        this.tileentitykingen = tileentitykingen;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        String containerName = tileentitykingen.isInvNameLocalized() ? tileentitykingen.getInvName() : StatCollector.translateToLocal(tileentitykingen.getInvName());
        fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal(Strings.containterKeneticGen), 8, ySize - 96 + 2, 4210752);
    }

}

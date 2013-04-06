package com.lomeli.magiks.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;

public class GuiKineticGenerator extends GuiContainer
{
    private TileEntityKineticGenerator tileentitykingen;
    
    public GuiKineticGenerator(Container par1Container, TileEntityKineticGenerator tileentitykingen)
    {
        super(par1Container);
        
        this.tileentitykingen = tileentitykingen;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        
        
    }

}

package com.lomeli.magiks.client.render.entity;

import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;

import com.lomeli.magiks.client.model.ModelEnergyCore;
import com.lomeli.magiks.entity.EntityEnergyCore;

public class RenderEnergyCore extends RenderEntity
{
    protected ModelEnergyCore energyCore;
    
    public RenderEnergyCore()
    {
        energyCore = new ModelEnergyCore();
    }
}

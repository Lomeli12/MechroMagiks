package com.lomeli.magiks.client.render.entity;

import net.minecraft.client.renderer.entity.RenderEntity;

import com.lomeli.magiks.client.model.ModelEnergyCore;

public class RenderEnergyCore extends RenderEntity
{
    protected ModelEnergyCore energyCore;

    public RenderEnergyCore()
    {
        energyCore = new ModelEnergyCore();
    }
}

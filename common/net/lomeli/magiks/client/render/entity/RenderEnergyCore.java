package net.lomeli.magiks.client.render.entity;

import net.lomeli.magiks.client.model.ModelEnergyCore;
import net.minecraft.client.renderer.entity.RenderEntity;


public class RenderEnergyCore extends RenderEntity
{
    protected ModelEnergyCore energyCore;

    public RenderEnergyCore()
    {
        energyCore = new ModelEnergyCore();
    }
}

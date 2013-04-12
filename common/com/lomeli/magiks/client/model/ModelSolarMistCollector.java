package com.lomeli.magiks.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSolarMistCollector extends ModelBase
{
    // fields
    ModelRenderer base;
    ModelRenderer antenna1;
    ModelRenderer antenna2;
    ModelRenderer antenna3;
    ModelRenderer antenna4;
    ModelRenderer crystal1;
    ModelRenderer crystal2;
    ModelRenderer crystal3;
    ModelRenderer crystal4;

    public ModelSolarMistCollector()
    {
        textureWidth = 64;
        textureHeight = 64;

        base = new ModelRenderer(this, 0, 0);
        base.addBox(0F, 0F, 0F, 16, 9, 16);
        base.setRotationPoint(-8F, 15F, -8F);
        base.setTextureSize(64, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        antenna1 = new ModelRenderer(this, 0, 25);
        antenna1.addBox(0F, 0F, 0F, 1, 5, 1);
        antenna1.setRotationPoint(-8F, 10F, -8F);
        antenna1.setTextureSize(64, 64);
        antenna1.mirror = true;
        setRotation(antenna1, 0F, 0F, 0F);
        antenna2 = new ModelRenderer(this, 0, 25);
        antenna2.addBox(0F, 0F, 0F, 1, 5, 1);
        antenna2.setRotationPoint(-8F, 10F, 7F);
        antenna2.setTextureSize(64, 64);
        antenna2.mirror = true;
        setRotation(antenna2, 0F, 0F, 0F);
        antenna3 = new ModelRenderer(this, 0, 25);
        antenna3.addBox(0F, 0F, 0F, 1, 5, 1);
        antenna3.setRotationPoint(7F, 10F, -8F);
        antenna3.setTextureSize(64, 64);
        antenna3.mirror = true;
        setRotation(antenna3, 0F, 0F, 0F);
        antenna4 = new ModelRenderer(this, 0, 25);
        antenna4.addBox(0F, 0F, 0F, 1, 5, 1);
        antenna4.setRotationPoint(7F, 10F, 7F);
        antenna4.setTextureSize(64, 64);
        antenna4.mirror = true;
        setRotation(antenna4, 0F, 0F, 0F);
        crystal1 = new ModelRenderer(this, 4, 25);
        crystal1.addBox(7F, 0F, 7F, 1, 1, 1);
        crystal1.setRotationPoint(0F, 8F, 0F);
        crystal1.setTextureSize(64, 64);
        crystal1.mirror = true;
        setRotation(crystal1, 0F, 0F, 0F);
        crystal2 = new ModelRenderer(this, 4, 25);
        crystal2.addBox(7F, 0F, 7F, 1, 1, 1);
        crystal2.setRotationPoint(0F, 8F, -15F);
        crystal2.setTextureSize(64, 64);
        crystal2.mirror = true;
        setRotation(crystal2, 0F, 0F, 0F);
        crystal3 = new ModelRenderer(this, 4, 25);
        crystal3.addBox(7F, 0F, 7F, 1, 1, 1);
        crystal3.setRotationPoint(-15F, 8F, 0F);
        crystal3.setTextureSize(64, 64);
        crystal3.mirror = true;
        setRotation(crystal3, 0F, 0F, 0F);
        crystal4 = new ModelRenderer(this, 4, 25);
        crystal4.addBox(7F, 0F, 7F, 1, 1, 1);
        crystal4.setRotationPoint(-15F, 8F, -15F);
        crystal4.setTextureSize(64, 64);
        crystal4.mirror = true;
        setRotation(crystal4, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3,
            float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        base.render(f5);
        antenna1.render(f5);
        antenna2.render(f5);
        antenna3.render(f5);
        antenna4.render(f5);
        crystal1.render(f5);
        crystal2.render(f5);
        crystal3.render(f5);
        crystal4.render(f5);
    }

    public void render(float f5)
    {
        base.render(f5);
        antenna1.render(f5);
        antenna2.render(f5);
        antenna3.render(f5);
        antenna4.render(f5);
        crystal1.render(f5);
        crystal2.render(f5);
        crystal3.render(f5);
        crystal4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3,
            float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}

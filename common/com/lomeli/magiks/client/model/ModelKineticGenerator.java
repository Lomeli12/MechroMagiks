package com.lomeli.magiks.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKineticGenerator extends ModelBase
{
    // fields
    ModelRenderer Base;
    ModelRenderer stand1;
    ModelRenderer stand2;
    ModelRenderer stand3;
    ModelRenderer stand4;
    ModelRenderer top;
    ModelRenderer side1;
    ModelRenderer side2;
    ModelRenderer side3;
    ModelRenderer side4;

    public ModelKineticGenerator()
    {
        textureWidth = 128;
        textureHeight = 64;

        Base = new ModelRenderer(this, 0, 0);
        Base.addBox(0F, 0F, 0F, 16, 8, 16);
        Base.setRotationPoint(-8F, 16F, -8F);
        Base.setTextureSize(128, 64);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        stand1 = new ModelRenderer(this, 0, 24);
        stand1.addBox(0F, 0F, 0F, 4, 2, 4);
        stand1.setRotationPoint(-2F, 14F, -2F);
        stand1.setTextureSize(128, 64);
        stand1.mirror = true;
        setRotation(stand1, 0F, 0F, 0F);
        stand2 = new ModelRenderer(this, 0, 24);
        stand2.addBox(0F, 0F, 0F, 4, 2, 4);
        stand2.setRotationPoint(-2F, -2F, -2F);
        stand2.setTextureSize(128, 64);
        stand2.mirror = true;
        setRotation(stand2, 0F, 0F, 0F);
        stand3 = new ModelRenderer(this, 16, 24);
        stand3.addBox(0F, 0F, 0F, 2, 3, 2);
        stand3.setRotationPoint(-1F, 11F, -1F);
        stand3.setTextureSize(128, 64);
        stand3.mirror = true;
        setRotation(stand3, 0F, 0F, 0F);
        stand4 = new ModelRenderer(this, 16, 24);
        stand4.addBox(0F, 0F, 0F, 2, 3, 2);
        stand4.setRotationPoint(-1F, 0F, -1F);
        stand4.setTextureSize(128, 64);
        stand4.mirror = true;
        setRotation(stand4, 0F, 0F, 0F);
        top = new ModelRenderer(this, 48, 0);
        top.addBox(0F, 0F, 0F, 16, 1, 16);
        top.setRotationPoint(-8F, -3F, -8F);
        top.setTextureSize(128, 64);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);
        side1 = new ModelRenderer(this, 64, 16);
        side1.addBox(0F, 0F, 0F, 0, 18, 16);
        side1.setRotationPoint(-8F, -2F, -8F);
        side1.setTextureSize(128, 64);
        side1.mirror = true;
        setRotation(side1, 0F, 0F, 0F);
        side2 = new ModelRenderer(this, 64, 16);
        side2.addBox(0F, 0F, 0F, 0, 18, 16);
        side2.setRotationPoint(8F, -2F, -8F);
        side2.setTextureSize(128, 64);
        side2.mirror = true;
        setRotation(side2, 0F, 0F, 0F);
        side3 = new ModelRenderer(this, 30, 25);
        side3.addBox(0F, 0F, 0F, 16, 18, 0);
        side3.setRotationPoint(-8F, -2F, -8F);
        side3.setTextureSize(128, 64);
        side3.mirror = true;
        setRotation(side3, 0F, 0F, 0F);
        side4 = new ModelRenderer(this, 30, 25);
        side4.addBox(0F, 0F, 0F, 16, 18, 0);
        side4.setRotationPoint(-8F, -2F, 8F);
        side4.setTextureSize(128, 64);
        side4.mirror = true;
        setRotation(side4, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3,
            float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Base.render(f5);
        stand1.render(f5);
        stand2.render(f5);
        stand3.render(f5);
        stand4.render(f5);
        top.render(f5);
        side1.render(f5);
        side2.render(f5);
        side3.render(f5);
        side4.render(f5);
    }

    public void render(float f5)
    {
        Base.render(f5);
        stand1.render(f5);
        stand2.render(f5);
        stand3.render(f5);
        stand4.render(f5);
        top.render(f5);
        side1.render(f5);
        side2.render(f5);
        side3.render(f5);
        side4.render(f5);
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

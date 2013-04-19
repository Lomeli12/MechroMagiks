package net.lomeli.magiks.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnergyCore extends ModelBase
{
    ModelRenderer peg1;
    ModelRenderer peg2;
    ModelRenderer peg3;

    public ModelEnergyCore()
    {
        textureWidth = 32;
        textureHeight = 32;

        peg1 = new ModelRenderer(this, 0, 0);
        peg1.addBox(0F, 0F, 0F, 2, 6, 2);
        peg1.setRotationPoint(-1F, 18F, -1F);
        peg1.setTextureSize(32, 32);
        peg1.mirror = true;
        setRotation(peg1, 0F, 0F, 0F);
        peg2 = new ModelRenderer(this, 8, 0);
        peg2.addBox(0F, 0F, 0F, 2, 2, 6);
        peg2.setRotationPoint(-1F, 20F, -3F);
        peg2.setTextureSize(32, 32);
        peg2.mirror = true;
        setRotation(peg2, 0F, 0F, 0F);
        peg3 = new ModelRenderer(this, 8, 8);
        peg3.addBox(0F, 0F, 0F, 6, 2, 2);
        peg3.setRotationPoint(-3F, 20F, -1F);
        peg3.setTextureSize(32, 32);
        peg3.mirror = true;
        setRotation(peg3, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3,
            float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        peg1.render(f5);
        peg2.render(f5);
        peg3.render(f5);
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

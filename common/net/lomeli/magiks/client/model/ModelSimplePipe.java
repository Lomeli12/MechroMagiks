package net.lomeli.magiks.client.model;

import net.lomeli.magiks.tileentity.TileEntitySimplePipe;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSimplePipe extends ModelBase
{
	ModelRenderer front;
	ModelRenderer back;
	ModelRenderer base;
	ModelRenderer right;
	ModelRenderer left;
	
	public ModelSimplePipe()
	{
		textureWidth = 64;
		textureHeight = 32;
		
		front = new ModelRenderer(this, 20, 10);
	    front.addBox(0F, 0F, 0F, 4, 4, 6);
	    front.setRotationPoint(-2F, 16F, -8F);
	    front.setTextureSize(64, 32);
	    front.mirror = true;
	    setRotation(front, 0F, 0F, 0F);
	    back = new ModelRenderer(this, 20, 0);
	    back.addBox(0F, 0F, 0F, 4, 4, 6);
	    back.setRotationPoint(-2F, 16F, 2F);
	    back.setTextureSize(64, 32);
	    back.mirror = true;
	    setRotation(back, 0F, 0F, 0F);
	    base = new ModelRenderer(this, 0, 0);
	    base.addBox(0F, 0F, 0F, 4, 4, 4);
	    base.setRotationPoint(-2F, 16F, -2F);
	    base.setTextureSize(64, 32);
	    base.mirror = true;
	    setRotation(base, 0F, 0F, 0F);
	    right = new ModelRenderer(this, 0, 8);
	    right.addBox(0F, 0F, 0F, 6, 4, 4);
	    right.setRotationPoint(-8F, 16F, -2F);
	    right.setTextureSize(64, 32);
	    right.mirror = true;
	    setRotation(right, 0F, 0F, 0F);
	    left = new ModelRenderer(this, 0, 16);
	    left.addBox(0F, 0F, 0F, 6, 4, 4);
	    left.setRotationPoint(2F, 16F, -2F);
	    left.setTextureSize(64, 32);
	    left.mirror = true;
	    setRotation(left, 0F, 0F, 0F);
	}

	@Override
    public void render(Entity entity, float f, float f1, float f2, float f3,
            float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        base.render(f5);
        back.render(f5);
        front.render(f5);
        left.render(f5);
        right.render(f5);
    }
	
	public void render(float f5)
    {
		base.render(f5);
        back.render(f5);
        front.render(f5);
        left.render(f5);
        right.render(f5);
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

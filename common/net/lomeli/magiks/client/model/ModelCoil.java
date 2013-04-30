package net.lomeli.magiks.client.model;

//import net.lomeli.magiks.lib.Models;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
//import net.minecraftforge.client.model.AdvancedModelLoader;
//import net.minecraftforge.client.model.IModelCustom;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCoil extends ModelBase
{
	ModelRenderer base;
    ModelRenderer cyc1;
    ModelRenderer cyc2;
    ModelRenderer cyc3;
    ModelRenderer cyc4;
    ModelRenderer cyc5;
    ModelRenderer cyc6;
    ModelRenderer coil1;
    ModelRenderer coil2;
    ModelRenderer coil3;
    ModelRenderer coil4;
    ModelRenderer coil5;
    ModelRenderer coil6;
    
    public ModelCoil()
    {
    	textureWidth = 128;
        textureHeight = 128;
        
        base = new ModelRenderer(this, 65, 0);
        base.addBox(0F, 0F, 0F, 12, 4, 12);
        base.setRotationPoint(-6F, 20F, -6F);
        base.setTextureSize(128, 128);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        cyc1 = new ModelRenderer(this, 40, 0);
        cyc1.addBox(-2F, 0F, -2F, 4, 10, 4);
        cyc1.setRotationPoint(0F, 10F, 0F);
        cyc1.setTextureSize(128, 128);
        cyc1.mirror = true;
        setRotation(cyc1, 0F, 0F, 0F);
        cyc2 = new ModelRenderer(this, 40, 0);
        cyc2.addBox(-2F, 0F, -2F, 4, 10, 4);
        cyc2.setRotationPoint(0F, 10F, 0F);
        cyc2.setTextureSize(128, 128);
        cyc2.mirror = true;
        setRotation(cyc2, 0F, 0.7853982F, 0F);
        cyc3 = new ModelRenderer(this, 40, 0);
        cyc3.addBox(-2F, 0F, -2F, 4, 10, 4);
        cyc3.setRotationPoint(0F, 10F, 0F);
        cyc3.setTextureSize(128, 128);
        cyc3.mirror = true;
        setRotation(cyc3, 0F, 0.5235988F, 0F);
        cyc4 = new ModelRenderer(this, 40, 0);
        cyc4.addBox(-2F, 0F, -2F, 4, 10, 4);
        cyc4.setRotationPoint(0F, 10F, 0F);
        cyc4.setTextureSize(128, 128);
        cyc4.mirror = true;
        setRotation(cyc4, 0F, 1.047198F, 0F);
        cyc5 = new ModelRenderer(this, 40, 0);
        cyc5.addBox(-2F, 0F, -2F, 4, 10, 4);
        cyc5.setRotationPoint(0F, 10F, 0F);
        cyc5.setTextureSize(128, 128);
        cyc5.mirror = true;
        setRotation(cyc5, 0F, -0.2617994F, 0F);
        cyc6 = new ModelRenderer(this, 40, 0);
        cyc6.addBox(-2F, 0F, -2F, 4, 10, 4);
        cyc6.setRotationPoint(0F, 10F, 0F);
        cyc6.setTextureSize(128, 128);
        cyc6.mirror = true;
        setRotation(cyc6, 0F, 0.2617994F, 0F);
        coil1 = new ModelRenderer(this, 0, 0);
        coil1.addBox(-4F, 0F, -4F, 8, 3, 8);
        coil1.setRotationPoint(0F, 8F, 0F);
        coil1.setTextureSize(128, 128);
        coil1.mirror = true;
        setRotation(coil1, 0F, 0F, 0F);
        coil2 = new ModelRenderer(this, 0, 0);
        coil2.addBox(-4F, 0F, -4F, 8, 3, 8);
        coil2.setRotationPoint(0F, 8F, 0F);
        coil2.setTextureSize(128, 128);
        coil2.mirror = true;
        setRotation(coil2, 0F, 0.7853982F, 0F);
        coil3 = new ModelRenderer(this, 0, 0);
        coil3.addBox(-4F, 0F, -4F, 8, 3, 8);
        coil3.setRotationPoint(0F, 8F, 0F);
        coil3.setTextureSize(128, 128);
        coil3.mirror = true;
        setRotation(coil3, 0F, 0.5235988F, 0F);
        coil4 = new ModelRenderer(this, 0, 0);
        coil4.addBox(-4F, 0F, -4F, 8, 3, 8);
        coil4.setRotationPoint(0F, 8F, 0F);
        coil4.setTextureSize(128, 128);
        coil4.mirror = true;
        setRotation(coil4, 0F, 1.047198F, 0F);
        coil5 = new ModelRenderer(this, 0, 0);
        coil5.addBox(-4F, 0F, -4F, 8, 3, 8);
        coil5.setRotationPoint(0F, 8F, 0F);
        coil5.setTextureSize(128, 128);
        coil5.mirror = true;
        setRotation(coil5, 0F, -0.2617994F, 0F);
        coil6 = new ModelRenderer(this, 0, 0);
        coil6.addBox(-4F, 0F, -4F, 8, 3, 8);
        coil6.setRotationPoint(0F, 8F, 0F);
        coil6.setTextureSize(128, 128);
        coil6.mirror = true;
        setRotation(coil6, 0F, 0.2617994F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
    	super.render(entity, f, f1, f2, f3, f4, f5);
    	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	base.render(f5);
    	cyc1.render(f5);
    	cyc2.render(f5);
    	cyc3.render(f5);
    	cyc4.render(f5);
    	cyc5.render(f5);
    	cyc6.render(f5);
    	coil1.render(f5);
    	coil2.render(f5);
    	coil3.render(f5);
    	coil4.render(f5);
    	coil5.render(f5);
    	coil6.render(f5);
    }
    
    public void render(float f5)
    {
    	base.render(f5);
    	cyc1.render(f5);
    	cyc2.render(f5);
    	cyc3.render(f5);
    	cyc4.render(f5);
    	cyc5.render(f5);
    	cyc6.render(f5);
    	coil1.render(f5);
    	coil2.render(f5);
    	coil3.render(f5);
    	coil4.render(f5);
    	coil5.render(f5);
    	coil6.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
    	super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
    
	/*
	private IModelCustom coilModel;
	
	public ModelCoil()
	{
		coilModel = AdvancedModelLoader.loadModel(Models.MODEL_COIL);
	}
	
	public void render()
	{
		coilModel.renderAll();
	}*/
}

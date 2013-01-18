
package hawksmachinery.redstone.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeDirection;

public class HMModelRedWire extends ModelBase
{
	ModelRenderer CENTER;
	ModelRenderer BOTTOM;
	ModelRenderer TOP;
	ModelRenderer FRONT;
	ModelRenderer BACK;
	ModelRenderer LEFT;
	ModelRenderer RIGHT;
	
	ModelRenderer[] parts = new ModelRenderer[6];
	
	public HMModelRedWire()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		
		this.CENTER = new ModelRenderer(this, 0, 0);
		this.CENTER.addBox(0F, 0F, 0F, 4, 4, 4);
		this.CENTER.setRotationPoint(-2F, 14F, -2F);
		this.CENTER.setTextureSize(64, 32);
		this.CENTER.mirror = true;
		this.setRotation(this.CENTER, 0F, 0F, 0F);
		this.BOTTOM = new ModelRenderer(this, 20, 22);
		this.BOTTOM.addBox(0F, 0F, 0F, 4, 6, 4);
		this.BOTTOM.setRotationPoint(-2F, 18F, -2F);
		this.BOTTOM.setTextureSize(64, 32);
		this.BOTTOM.mirror = true;
		this.setRotation(this.BOTTOM, 0F, 0F, 0F);
		this.TOP = new ModelRenderer(this, 20, 22);
		this.TOP.addBox(0F, 0F, 0F, 4, 6, 4);
		this.TOP.setRotationPoint(-2F, 8F, -2F);
		this.TOP.setTextureSize(64, 32);
		this.TOP.mirror = true;
		this.setRotation(this.TOP, 0F, 0F, 0F);
		this.FRONT = new ModelRenderer(this, 16, 0);
		this.FRONT.addBox(0F, 0F, 0F, 4, 4, 6);
		this.FRONT.setRotationPoint(-2F, 14F, -8F);
		this.FRONT.setTextureSize(64, 32);
		this.FRONT.mirror = true;
		this.setRotation(this.FRONT, 0F, 0F, 0F);
		this.BACK = new ModelRenderer(this, 16, 0);
		this.BACK.addBox(0F, 0F, 0F, 4, 4, 6);
		this.BACK.setRotationPoint(-2F, 14F, 2F);
		this.BACK.setTextureSize(64, 32);
		this.BACK.mirror = true;
		this.setRotation(this.BACK, 0F, 0F, 0F);
		this.LEFT = new ModelRenderer(this, 0, 24);
		this.LEFT.addBox(0F, 0F, 0F, 6, 4, 4);
		this.LEFT.setRotationPoint(2F, 14F, -2F);
		this.LEFT.setTextureSize(64, 32);
		this.LEFT.mirror = true;
		this.setRotation(this.LEFT, 0F, 0F, 0F);
		this.RIGHT = new ModelRenderer(this, 0, 24);
		this.RIGHT.addBox(0F, 0F, 0F, 6, 4, 4);
		this.RIGHT.setRotationPoint(-8F, 14F, -2F);
		this.RIGHT.setTextureSize(64, 32);
		this.RIGHT.mirror = true;
		this.setRotation(this.RIGHT, 0F, 0F, 0F);
		
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		CENTER.render(0.0625F);
		/*
		BOTTOM.render(0.0625F);
		TOP.render(0.0625F);
		FRONT.render(0.0625F);
		BACK.render(0.0625F);
		LEFT.render(0.0625F);
		RIGHT.render(0.0625F);
		*/
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
	}
	
}

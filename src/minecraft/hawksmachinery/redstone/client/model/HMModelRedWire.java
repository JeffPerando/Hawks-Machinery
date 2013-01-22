
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
		textureWidth = 64;
		textureHeight = 32;
		
		CENTER = new ModelRenderer(this, 0, 0);
		CENTER.addBox(0F, 0F, 0F, 4, 4, 4);
		CENTER.setRotationPoint(-2F, 14F, -2F);
		CENTER.setTextureSize(64, 32);
		CENTER.mirror = true;
		setRotation(CENTER, 0F, 0F, 0F);
		BOTTOM = new ModelRenderer(this, 20, 22);
		BOTTOM.addBox(0F, 0F, 0F, 4, 6, 4);
		BOTTOM.setRotationPoint(-2F, 18F, -2F);
		BOTTOM.setTextureSize(64, 32);
		BOTTOM.mirror = true;
		setRotation(BOTTOM, 0F, 0F, 0F);
		TOP = new ModelRenderer(this, 20, 22);
		TOP.addBox(0F, 0F, 0F, 4, 6, 4);
		TOP.setRotationPoint(-2F, 8F, -2F);
		TOP.setTextureSize(64, 32);
		TOP.mirror = true;
		setRotation(TOP, 0F, 0F, 0F);
		FRONT = new ModelRenderer(this, 16, 0);
		FRONT.addBox(0F, 0F, 0F, 4, 4, 6);
		FRONT.setRotationPoint(-2F, 14F, -8F);
		FRONT.setTextureSize(64, 32);
		FRONT.mirror = true;
		setRotation(FRONT, 0F, 0F, 0F);
		BACK = new ModelRenderer(this, 16, 0);
		BACK.addBox(0F, 0F, 0F, 4, 4, 6);
		BACK.setRotationPoint(-2F, 14F, 2F);
		BACK.setTextureSize(64, 32);
		BACK.mirror = true;
		setRotation(BACK, 0F, 0F, 0F);
		LEFT = new ModelRenderer(this, 0, 24);
		LEFT.addBox(0F, 0F, 0F, 6, 4, 4);
		LEFT.setRotationPoint(2F, 14F, -2F);
		LEFT.setTextureSize(64, 32);
		LEFT.mirror = true;
		setRotation(LEFT, 0F, 0F, 0F);
		RIGHT = new ModelRenderer(this, 0, 24);
		RIGHT.addBox(0F, 0F, 0F, 6, 4, 4);
		RIGHT.setRotationPoint(-8F, 14F, -2F);
		RIGHT.setTextureSize(64, 32);
		RIGHT.mirror = true;
		setRotation(RIGHT, 0F, 0F, 0F);
		
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.CENTER.render(0.0625F);
		/*
		this.BOTTOM.render(0.0625F);
		this.TOP.render(0.0625F);
		this.FRONT.render(0.0625F);
		this.BACK.render(0.0625F);
		this.LEFT.render(0.0625F);
		this.RIGHT.render(0.0625F);
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


package hawksmachinery;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class HMModelWasher extends ModelBase
{
	ModelRenderer AXIS;
	ModelRenderer BLADE_1;
	ModelRenderer BLADE_2;
	ModelRenderer BLADE_3;
	ModelRenderer BLADE_4;
	ModelRenderer BLADE_5;
	ModelRenderer BLADE_6;
	ModelRenderer BLADE_7;
	ModelRenderer BLADE_8;
	ModelRenderer BLADE_9;
	ModelRenderer CONTAINMENT_TUBE_1;
	ModelRenderer MOTOR;
	ModelRenderer CONTAINMENT_TUBE_2;
	ModelRenderer CONTAINMENT_TUBE_3;
	ModelRenderer CONTAINMENT_TUBE_4;
	ModelRenderer CONTAINMENT_TUBE_5;
	ModelRenderer CONTAINMENT_TUBE_6;
	ModelRenderer CONTAINMENT_TUBE_7;
	ModelRenderer CABLE;
	ModelRenderer CONTAINMENT_TUBE_8;
	ModelRenderer PLATFORM;
	ModelRenderer SUPPORT;
	
	public HMModelWasher()
	{
		textureWidth = 128;
		textureHeight = 128;
		
		AXIS = new ModelRenderer(this, 61, 0);
		AXIS.addBox(-1F, 0F, -1F, 2, 16, 2);
		AXIS.setRotationPoint(0F, 8F, 1F);
		AXIS.setTextureSize(128, 128);
		AXIS.mirror = true;
		setRotation(AXIS, 0F, 0F, 0F);
		BLADE_1 = new ModelRenderer(this, 38, 0);
		BLADE_1.addBox(0F, 0F, -2F, 6, 1, 4);
		BLADE_1.setRotationPoint(0F, 11F, 1F);
		BLADE_1.setTextureSize(128, 128);
		BLADE_1.mirror = true;
		setRotation(BLADE_1, 0.3490659F, 0F, 0F);
		BLADE_2 = new ModelRenderer(this, 38, 0);
		BLADE_2.addBox(0F, 0F, -2F, 6, 1, 4);
		BLADE_2.setRotationPoint(0F, 12F, 1F);
		BLADE_2.setTextureSize(128, 128);
		BLADE_2.mirror = true;
		setRotation(BLADE_2, 0.3490659F, 0.7853982F, 0F);
		BLADE_3 = new ModelRenderer(this, 38, 0);
		BLADE_3.addBox(0F, 0F, -2F, 6, 1, 4);
		BLADE_3.setRotationPoint(0F, 13F, 1F);
		BLADE_3.setTextureSize(128, 128);
		BLADE_3.mirror = true;
		setRotation(BLADE_3, 0.3490659F, 1.570796F, 0F);
		BLADE_4 = new ModelRenderer(this, 38, 0);
		BLADE_4.addBox(0F, 0F, -2F, 6, 1, 4);
		BLADE_4.setRotationPoint(0F, 14F, 1F);
		BLADE_4.setTextureSize(128, 128);
		BLADE_4.mirror = true;
		setRotation(BLADE_4, 0.3490659F, 2.356194F, 0F);
		BLADE_5 = new ModelRenderer(this, 38, 0);
		BLADE_5.addBox(0F, 0F, -2F, 6, 1, 4);
		BLADE_5.setRotationPoint(0F, 15F, 1F);
		BLADE_5.setTextureSize(128, 128);
		BLADE_5.mirror = true;
		setRotation(BLADE_5, 0.3490659F, 3.141593F, 0F);
		BLADE_6 = new ModelRenderer(this, 38, 0);
		BLADE_6.addBox(0F, 0F, -2F, 6, 1, 4);
		BLADE_6.setRotationPoint(0F, 16F, 1F);
		BLADE_6.setTextureSize(128, 128);
		BLADE_6.mirror = true;
		setRotation(BLADE_6, 0.3490659F, -2.356194F, 0F);
		BLADE_7 = new ModelRenderer(this, 38, 0);
		BLADE_7.addBox(0F, 0F, -2F, 6, 1, 4);
		BLADE_7.setRotationPoint(0F, 17F, 1F);
		BLADE_7.setTextureSize(128, 128);
		BLADE_7.mirror = true;
		setRotation(BLADE_7, 0.3490659F, -1.570796F, 0F);
		BLADE_8 = new ModelRenderer(this, 38, 0);
		BLADE_8.addBox(0F, 0F, -2F, 6, 1, 4);
		BLADE_8.setRotationPoint(0F, 18F, 1F);
		BLADE_8.setTextureSize(128, 128);
		BLADE_8.mirror = true;
		setRotation(BLADE_8, 0.3490659F, -0.7853982F, 0F);
		BLADE_9 = new ModelRenderer(this, 61, 20);
		BLADE_9.addBox(0F, 0F, -6F, 6, 1, 8);
		BLADE_9.setRotationPoint(0F, 19F, 1F);
		BLADE_9.setTextureSize(128, 128);
		BLADE_9.mirror = true;
		setRotation(BLADE_9, 0.3490659F, 0F, 0F);
		CONTAINMENT_TUBE_1 = new ModelRenderer(this, 19, 32);
		CONTAINMENT_TUBE_1.addBox(0F, 0F, -1F, 7, 14, 2);
		CONTAINMENT_TUBE_1.setRotationPoint(-4F, 10F, 7F);
		CONTAINMENT_TUBE_1.setTextureSize(128, 128);
		CONTAINMENT_TUBE_1.mirror = true;
		setRotation(CONTAINMENT_TUBE_1, 0F, 0F, 0F);
		MOTOR = new ModelRenderer(this, 0, 0);
		MOTOR.addBox(-1F, 0F, -1F, 10, 4, 9);
		MOTOR.setRotationPoint(-1F, 20F, 0F);
		MOTOR.setTextureSize(128, 128);
		MOTOR.mirror = true;
		setRotation(MOTOR, 0F, 0F, 0F);
		CONTAINMENT_TUBE_2 = new ModelRenderer(this, 38, 8);
		CONTAINMENT_TUBE_2.addBox(0F, 0F, 0F, 5, 14, 1);
		CONTAINMENT_TUBE_2.setRotationPoint(-6F, 10F, 3F);
		CONTAINMENT_TUBE_2.setTextureSize(128, 128);
		CONTAINMENT_TUBE_2.mirror = true;
		setRotation(CONTAINMENT_TUBE_2, 0F, -0.9948377F, 0F);
		CONTAINMENT_TUBE_3 = new ModelRenderer(this, 0, 30);
		CONTAINMENT_TUBE_3.addBox(0F, 0F, -1F, 5, 14, 1);
		CONTAINMENT_TUBE_3.setRotationPoint(3F, 10F, 8F);
		CONTAINMENT_TUBE_3.setTextureSize(128, 128);
		CONTAINMENT_TUBE_3.mirror = true;
		setRotation(CONTAINMENT_TUBE_3, 0F, 0.7853982F, 0F);
		CONTAINMENT_TUBE_4 = new ModelRenderer(this, 38, 26);
		CONTAINMENT_TUBE_4.addBox(0F, 0F, 0F, 2, 16, 6);
		CONTAINMENT_TUBE_4.setRotationPoint(-8F, 8F, -2F);
		CONTAINMENT_TUBE_4.setTextureSize(128, 128);
		CONTAINMENT_TUBE_4.mirror = true;
		setRotation(CONTAINMENT_TUBE_4, 0F, 0F, 0F);
		CONTAINMENT_TUBE_5 = new ModelRenderer(this, 0, 47);
		CONTAINMENT_TUBE_5.addBox(0F, 0F, 0F, 7, 14, 1);
		CONTAINMENT_TUBE_5.setRotationPoint(-7F, 10F, -2F);
		CONTAINMENT_TUBE_5.setTextureSize(128, 128);
		CONTAINMENT_TUBE_5.mirror = true;
		setRotation(CONTAINMENT_TUBE_5, 0F, 0.7853982F, 0F);
		CONTAINMENT_TUBE_6 = new ModelRenderer(this, 0, 13);
		CONTAINMENT_TUBE_6.addBox(0F, 0F, 0F, 4, 14, 2);
		CONTAINMENT_TUBE_6.setRotationPoint(-2F, 10F, -7F);
		CONTAINMENT_TUBE_6.setTextureSize(128, 128);
		CONTAINMENT_TUBE_6.mirror = true;
		setRotation(CONTAINMENT_TUBE_6, 0F, 0F, 0F);
		CONTAINMENT_TUBE_7 = new ModelRenderer(this, 15, 14);
		CONTAINMENT_TUBE_7.addBox(0F, 0F, 0F, 8, 14, 2);
		CONTAINMENT_TUBE_7.setRotationPoint(5F, 10F, 5F);
		CONTAINMENT_TUBE_7.setTextureSize(128, 128);
		CONTAINMENT_TUBE_7.mirror = true;
		setRotation(CONTAINMENT_TUBE_7, 0F, 1.570796F, 0F);
		CABLE = new ModelRenderer(this, 72, 0);
		CABLE.addBox(0F, 0F, 0F, 4, 1, 1);
		CABLE.setRotationPoint(-6F, 23F, 1F);
		CABLE.setTextureSize(128, 128);
		CABLE.mirror = true;
		setRotation(CABLE, 0F, 0F, 0F);
		CONTAINMENT_TUBE_8 = new ModelRenderer(this, 19, 50);
		CONTAINMENT_TUBE_8.addBox(0F, 0F, 0F, 6, 8, 1);
		CONTAINMENT_TUBE_8.setRotationPoint(2F, 10F, -6F);
		CONTAINMENT_TUBE_8.setTextureSize(128, 128);
		CONTAINMENT_TUBE_8.mirror = true;
		setRotation(CONTAINMENT_TUBE_8, 0F, -0.7853982F, 0F);
		PLATFORM = new ModelRenderer(this, 70, 4);
		PLATFORM.addBox(0F, 0F, 0F, 6, 3, 4);
		PLATFORM.setRotationPoint(0F, 21F, -8F);
		PLATFORM.setTextureSize(128, 128);
		PLATFORM.mirror = true;
		setRotation(PLATFORM, 0F, 0F, 0F);
		SUPPORT = new ModelRenderer(this, 38, 0);
		SUPPORT.addBox(-7F, 0F, -1F, 8, 1, 2);
		SUPPORT.setRotationPoint(0F, 8F, 1F);
		SUPPORT.setTextureSize(128, 128);
		SUPPORT.mirror = true;
		setRotation(SUPPORT, 0F, 0F, 0F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, null);
		AXIS.render(0.0625F);
		BLADE_1.render(0.0625F);
		BLADE_2.render(0.0625F);
		BLADE_3.render(0.0625F);
		BLADE_4.render(0.0625F);
		BLADE_5.render(0.0625F);
		BLADE_6.render(0.0625F);
		BLADE_7.render(0.0625F);
		BLADE_8.render(0.0625F);
		BLADE_9.render(0.0625F);
		CONTAINMENT_TUBE_1.render(0.0625F);
		MOTOR.render(0.0625F);
		CONTAINMENT_TUBE_2.render(0.0625F);
		CONTAINMENT_TUBE_3.render(0.0625F);
		CONTAINMENT_TUBE_4.render(0.0625F);
		CONTAINMENT_TUBE_5.render(0.0625F);
		CONTAINMENT_TUBE_6.render(0.0625F);
		CONTAINMENT_TUBE_7.render(0.0625F);
		CABLE.render(0.0625F);
		CONTAINMENT_TUBE_8.render(0.0625F);
		PLATFORM.render(0.0625F);
		SUPPORT.render(0.0625F);
		
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


package hawksmachinery.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HMModelSinterer extends ModelBase
{
	//fields
	ModelRenderer BASE;
	ModelRenderer MAIN_CHAMBER;
	ModelRenderer TEMPERATURE_CONTROL;
	ModelRenderer CONTROL_CPU;
	ModelRenderer KEYBOARD_LEG;
	ModelRenderer KEYBOARD;
	ModelRenderer TOP_HATCH_1_ROT;
	ModelRenderer TOP_HATCH_2_ROT;
	
	public HMModelSinterer()
	{
		textureWidth = 128;
		textureHeight = 128;
		
		BASE = new ModelRenderer(this, 0, 0);
		BASE.addBox(0F, 0F, 0F, 16, 1, 16);
		BASE.setRotationPoint(-8F, 23F, -8F);
		BASE.setTextureSize(128, 128);
		BASE.mirror = true;
		setRotation(BASE, 0F, 0F, 0F);
		MAIN_CHAMBER = new ModelRenderer(this, 0, 20);
		MAIN_CHAMBER.addBox(0F, 0F, 0F, 16, 15, 8);
		MAIN_CHAMBER.setRotationPoint(-8F, 8F, 0F);
		MAIN_CHAMBER.setTextureSize(128, 128);
		MAIN_CHAMBER.mirror = true;
		setRotation(MAIN_CHAMBER, 0F, 0F, 0F);
		TEMPERATURE_CONTROL = new ModelRenderer(this, 0, 45);
		TEMPERATURE_CONTROL.addBox(0F, 0F, 0F, 7, 4, 6);
		TEMPERATURE_CONTROL.setRotationPoint(-8F, 11F, -5F);
		TEMPERATURE_CONTROL.setTextureSize(128, 128);
		TEMPERATURE_CONTROL.mirror = true;
		setRotation(TEMPERATURE_CONTROL, 0.5235988F, 0F, 0F);
		CONTROL_CPU = new ModelRenderer(this, 0, 57);
		CONTROL_CPU.addBox(0F, 0F, 0F, 7, 12, 6);
		CONTROL_CPU.setRotationPoint(-8F, 11F, -5F);
		CONTROL_CPU.setTextureSize(128, 128);
		CONTROL_CPU.mirror = true;
		setRotation(CONTROL_CPU, 0F, 0F, 0F);
		KEYBOARD_LEG = new ModelRenderer(this, 50, 30);
		KEYBOARD_LEG.addBox(-1F, 0F, -1F, 2, 10, 2);
		KEYBOARD_LEG.setRotationPoint(4F, 13F, -4F);
		KEYBOARD_LEG.setTextureSize(128, 128);
		KEYBOARD_LEG.mirror = true;
		setRotation(KEYBOARD_LEG, 0F, 0.4363323F, 0F);
		KEYBOARD = new ModelRenderer(this, 50, 20);
		KEYBOARD.addBox(-4F, -1F, -3F, 8, 2, 6);
		KEYBOARD.setRotationPoint(4F, 13F, -4F);
		KEYBOARD.setTextureSize(128, 128);
		KEYBOARD.mirror = true;
		setRotation(KEYBOARD, 0.3665191F, 0.4363323F, 0F);
		TOP_HATCH_1_ROT = new ModelRenderer(this, 53, 0);
		TOP_HATCH_1_ROT.addBox(-5F, 0F, 0F, 10, 1, 4);
		TOP_HATCH_1_ROT.setRotationPoint(3F, 7F, 0F);
		TOP_HATCH_1_ROT.setTextureSize(128, 128);
		TOP_HATCH_1_ROT.mirror = true;
		setRotation(TOP_HATCH_1_ROT, 0F, 0F, 0F);
		TOP_HATCH_2_ROT = new ModelRenderer(this, 53, 5);
		TOP_HATCH_2_ROT.addBox(-5F, 0F, -4F, 10, 1, 4);
		TOP_HATCH_2_ROT.setRotationPoint(3F, 7F, 8F);
		TOP_HATCH_2_ROT.setTextureSize(128, 128);
		TOP_HATCH_2_ROT.mirror = true;
		setRotation(TOP_HATCH_2_ROT, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		BASE.render(0.0625F);
		MAIN_CHAMBER.render(0.0625F);
		TEMPERATURE_CONTROL.render(0.0625F);
		CONTROL_CPU.render(0.0625F);
		KEYBOARD_LEG.render(0.0625F);
		KEYBOARD.render(0.0625F);
		TOP_HATCH_1_ROT.render(0.0625F);
		TOP_HATCH_2_ROT.render(0.0625F);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
	
}

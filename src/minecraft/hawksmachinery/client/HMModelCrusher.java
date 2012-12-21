
package hawksmachinery.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HMModelCrusher extends ModelBase
{
	ModelRenderer BASE;
	ModelRenderer CRUSHING_CHAMBER;
	ModelRenderer INTAKE_1;
	ModelRenderer INTAKE_PANEL_1;
	ModelRenderer INTAKE_PANEL_2;
	ModelRenderer INTAKE_PANEL_3;
	ModelRenderer INTAKE_PANEL_4;
	ModelRenderer CRUSHING_BLADES_1_ROT;
	ModelRenderer CRUSHING_BLADES_2_ROT;
	ModelRenderer POWER_INTAKE;
	ModelRenderer RESIDUE_OUTLET_CHIMNEY_1;
	ModelRenderer RESIDUE_OUTLET_CHIMNEY_2;
	ModelRenderer MATERIAL_OUTLET_1;
	ModelRenderer MAIN_OUTLET;
	ModelRenderer MATERIAL_OUTLET_2;
	ModelRenderer SUPPORT;
	ModelRenderer FINAL;
	
	public HMModelCrusher()
	{
		textureWidth = 128;
		textureHeight = 128;
		
		BASE = new ModelRenderer(this, 0, 0);
		BASE.addBox(0F, 0F, 0F, 16, 1, 16);
		BASE.setRotationPoint(-8F, 23F, -8F);
		BASE.setTextureSize(128, 128);
		BASE.mirror = true;
		setRotation(BASE, 0F, 0F, 0F);
		CRUSHING_CHAMBER = new ModelRenderer(this, 0, 19);
		CRUSHING_CHAMBER.addBox(0F, 0F, 0F, 10, 10, 10);
		CRUSHING_CHAMBER.setRotationPoint(-8F, 13F, -5F);
		CRUSHING_CHAMBER.setTextureSize(128, 128);
		CRUSHING_CHAMBER.mirror = true;
		setRotation(CRUSHING_CHAMBER, 0F, 0F, 0F);
		INTAKE_1 = new ModelRenderer(this, 41, 19);
		INTAKE_1.addBox(0F, 0F, 0F, 4, 4, 4);
		INTAKE_1.setRotationPoint(-7F, 9F, -2F);
		INTAKE_1.setTextureSize(128, 128);
		INTAKE_1.mirror = true;
		setRotation(INTAKE_1, 0F, 0F, 0F);
		INTAKE_PANEL_1 = new ModelRenderer(this, 41, 28);
		INTAKE_PANEL_1.addBox(0F, 0F, 0F, 4, 4, 1);
		INTAKE_PANEL_1.setRotationPoint(-7F, 6F, 2F);
		INTAKE_PANEL_1.setTextureSize(128, 128);
		INTAKE_PANEL_1.mirror = true;
		setRotation(INTAKE_PANEL_1, 0F, 0F, 0F);
		INTAKE_PANEL_2 = new ModelRenderer(this, 41, 28);
		INTAKE_PANEL_2.addBox(0F, 0F, 0F, 4, 4, 1);
		INTAKE_PANEL_2.setRotationPoint(-7F, 6F, -3F);
		INTAKE_PANEL_2.setTextureSize(128, 128);
		INTAKE_PANEL_2.mirror = true;
		setRotation(INTAKE_PANEL_2, 0F, 0F, 0F);
		INTAKE_PANEL_3 = new ModelRenderer(this, 41, 28);
		INTAKE_PANEL_3.addBox(0F, 0F, 0F, 1, 4, 4);
		INTAKE_PANEL_3.setRotationPoint(-8F, 6F, -2F);
		INTAKE_PANEL_3.setTextureSize(128, 128);
		INTAKE_PANEL_3.mirror = true;
		setRotation(INTAKE_PANEL_3, 0F, 0F, 0F);
		INTAKE_PANEL_4 = new ModelRenderer(this, 41, 28);
		INTAKE_PANEL_4.addBox(0F, 0F, 0F, 1, 4, 4);
		INTAKE_PANEL_4.setRotationPoint(-3F, 6F, -2F);
		INTAKE_PANEL_4.setTextureSize(128, 128);
		INTAKE_PANEL_4.mirror = true;
		setRotation(INTAKE_PANEL_4, 0F, 0F, 0F);
		CRUSHING_BLADES_1_ROT = new ModelRenderer(this, 53, 28);
		CRUSHING_BLADES_1_ROT.addBox(-1F, 0F, -1F, 2, 4, 2);
		CRUSHING_BLADES_1_ROT.setRotationPoint(-5F, 8F, 0F);
		CRUSHING_BLADES_1_ROT.setTextureSize(128, 128);
		CRUSHING_BLADES_1_ROT.mirror = true;
		setRotation(CRUSHING_BLADES_1_ROT, 0F, 0F, 0F);
		CRUSHING_BLADES_2_ROT = new ModelRenderer(this, 53, 35);
		CRUSHING_BLADES_2_ROT.addBox(-1F, 0F, -1F, 2, 4, 2);
		CRUSHING_BLADES_2_ROT.setRotationPoint(-5F, 8F, 0F);
		CRUSHING_BLADES_2_ROT.setTextureSize(128, 128);
		CRUSHING_BLADES_2_ROT.mirror = true;
		setRotation(CRUSHING_BLADES_2_ROT, 0F, 0.7853982F, 0F);
		POWER_INTAKE = new ModelRenderer(this, 0, 41);
		POWER_INTAKE.addBox(0F, 0F, 0F, 11, 9, 3);
		POWER_INTAKE.setRotationPoint(-8F, 14F, 5F);
		POWER_INTAKE.setTextureSize(128, 128);
		POWER_INTAKE.mirror = true;
		setRotation(POWER_INTAKE, 0F, 0F, 0F);
		RESIDUE_OUTLET_CHIMNEY_1 = new ModelRenderer(this, 0, 54);
		RESIDUE_OUTLET_CHIMNEY_1.addBox(0F, 0F, 0F, 2, 5, 2);
		RESIDUE_OUTLET_CHIMNEY_1.setRotationPoint(-1F, 9F, 5F);
		RESIDUE_OUTLET_CHIMNEY_1.setTextureSize(128, 128);
		RESIDUE_OUTLET_CHIMNEY_1.mirror = true;
		setRotation(RESIDUE_OUTLET_CHIMNEY_1, 0F, 0F, 0F);
		RESIDUE_OUTLET_CHIMNEY_2 = new ModelRenderer(this, 0, 54);
		RESIDUE_OUTLET_CHIMNEY_2.addBox(0F, 0F, 0F, 2, 5, 2);
		RESIDUE_OUTLET_CHIMNEY_2.setRotationPoint(-4F, 9F, 5F);
		RESIDUE_OUTLET_CHIMNEY_2.setTextureSize(128, 128);
		RESIDUE_OUTLET_CHIMNEY_2.mirror = true;
		setRotation(RESIDUE_OUTLET_CHIMNEY_2, 0F, 0F, 0F);
		MATERIAL_OUTLET_1 = new ModelRenderer(this, 82, 0);
		MATERIAL_OUTLET_1.addBox(0F, 0F, 0F, 12, 3, 3);
		MATERIAL_OUTLET_1.setRotationPoint(-4F, 15F, -8F);
		MATERIAL_OUTLET_1.setTextureSize(128, 128);
		MATERIAL_OUTLET_1.mirror = true;
		setRotation(MATERIAL_OUTLET_1, 0F, 0F, 0.3490659F);
		MAIN_OUTLET = new ModelRenderer(this, 66, 0);
		MAIN_OUTLET.addBox(0F, 0F, 0F, 4, 8, 3);
		MAIN_OUTLET.setRotationPoint(-8F, 15F, -8F);
		MAIN_OUTLET.setTextureSize(128, 128);
		MAIN_OUTLET.mirror = true;
		setRotation(MAIN_OUTLET, 0F, 0F, 0F);
		MATERIAL_OUTLET_2 = new ModelRenderer(this, 82, 16);
		MATERIAL_OUTLET_2.addBox(0F, 0F, 0F, 3, 3, 9);
		MATERIAL_OUTLET_2.setRotationPoint(5F, 19F, -6F);
		MATERIAL_OUTLET_2.setTextureSize(128, 128);
		MATERIAL_OUTLET_2.mirror = true;
		setRotation(MATERIAL_OUTLET_2, -0.0872665F, 0F, 0F);
		SUPPORT = new ModelRenderer(this, 82, 7);
		SUPPORT.addBox(0F, 0F, 0F, 3, 4, 3);
		SUPPORT.setRotationPoint(5F, 19F, -8F);
		SUPPORT.setTextureSize(128, 128);
		SUPPORT.mirror = true;
		setRotation(SUPPORT, 0F, 0F, 0F);
		FINAL = new ModelRenderer(this, 96, 7);
		FINAL.addBox(0F, 0F, 0F, 3, 1, 6);
		FINAL.setRotationPoint(5F, 22F, 0F);
		FINAL.setTextureSize(128, 128);
		FINAL.mirror = true;
		setRotation(FINAL, 0F, 0F, 0F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, null);
		BASE.render(0.0625F);
		CRUSHING_CHAMBER.render(0.0625F);
		INTAKE_1.render(0.0625F);
		INTAKE_PANEL_1.render(0.0625F);
		INTAKE_PANEL_2.render(0.0625F);
		INTAKE_PANEL_3.render(0.0625F);
		INTAKE_PANEL_4.render(0.0625F);
		CRUSHING_BLADES_1_ROT.render(0.0625F);
		CRUSHING_BLADES_2_ROT.render(0.0625F);
		POWER_INTAKE.render(0.0625F);
		RESIDUE_OUTLET_CHIMNEY_1.render(0.0625F);
		RESIDUE_OUTLET_CHIMNEY_2.render(0.0625F);
		MATERIAL_OUTLET_1.render(0.0625F);
		MAIN_OUTLET.render(0.0625F);
		MATERIAL_OUTLET_2.render(0.0625F);
		SUPPORT.render(0.0625F);
		FINAL.render(0.0625F);
		
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

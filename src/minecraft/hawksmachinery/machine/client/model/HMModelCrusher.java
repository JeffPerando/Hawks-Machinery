
package hawksmachinery.machine.client.model;

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
		this.textureWidth = 128;
		this.textureHeight = 128;
		
		this.BASE = new ModelRenderer(this, 0, 0);
		this.BASE.addBox(0F, 0F, 0F, 16, 1, 16);
		this.BASE.setRotationPoint(-8F, 23F, -8F);
		this.BASE.setTextureSize(128, 128);
		this.BASE.mirror = true;
		this.setRotation(this.BASE, 0F, 0F, 0F);
		this.CRUSHING_CHAMBER = new ModelRenderer(this, 0, 19);
		this.CRUSHING_CHAMBER.addBox(0F, 0F, 0F, 10, 10, 10);
		this.CRUSHING_CHAMBER.setRotationPoint(-8F, 13F, -5F);
		this.CRUSHING_CHAMBER.setTextureSize(128, 128);
		this.CRUSHING_CHAMBER.mirror = true;
		this.setRotation(this.CRUSHING_CHAMBER, 0F, 0F, 0F);
		this.INTAKE_1 = new ModelRenderer(this, 41, 19);
		this.INTAKE_1.addBox(0F, 0F, 0F, 4, 4, 4);
		this.INTAKE_1.setRotationPoint(-7F, 9F, -2F);
		this.INTAKE_1.setTextureSize(128, 128);
		this.INTAKE_1.mirror = true;
		this.setRotation(this.INTAKE_1, 0F, 0F, 0F);
		this.INTAKE_PANEL_1 = new ModelRenderer(this, 41, 28);
		this.INTAKE_PANEL_1.addBox(0F, 0F, 0F, 4, 4, 1);
		this.INTAKE_PANEL_1.setRotationPoint(-7F, 6F, 2F);
		this.INTAKE_PANEL_1.setTextureSize(128, 128);
		this.INTAKE_PANEL_1.mirror = true;
		this.setRotation(this.INTAKE_PANEL_1, 0F, 0F, 0F);
		this.INTAKE_PANEL_2 = new ModelRenderer(this, 41, 28);
		this.INTAKE_PANEL_2.addBox(0F, 0F, 0F, 4, 4, 1);
		this.INTAKE_PANEL_2.setRotationPoint(-7F, 6F, -3F);
		this.INTAKE_PANEL_2.setTextureSize(128, 128);
		this.INTAKE_PANEL_2.mirror = true;
		this.setRotation(this.INTAKE_PANEL_2, 0F, 0F, 0F);
		this.INTAKE_PANEL_3 = new ModelRenderer(this, 41, 28);
		this.INTAKE_PANEL_3.addBox(0F, 0F, 0F, 1, 4, 4);
		this.INTAKE_PANEL_3.setRotationPoint(-8F, 6F, -2F);
		this.INTAKE_PANEL_3.setTextureSize(128, 128);
		this.INTAKE_PANEL_3.mirror = true;
		this.setRotation(this.INTAKE_PANEL_3, 0F, 0F, 0F);
		this.INTAKE_PANEL_4 = new ModelRenderer(this, 41, 28);
		this.INTAKE_PANEL_4.addBox(0F, 0F, 0F, 1, 4, 4);
		this.INTAKE_PANEL_4.setRotationPoint(-3F, 6F, -2F);
		this.INTAKE_PANEL_4.setTextureSize(128, 128);
		this.INTAKE_PANEL_4.mirror = true;
		this.setRotation(this.INTAKE_PANEL_4, 0F, 0F, 0F);
		this.CRUSHING_BLADES_1_ROT = new ModelRenderer(this, 53, 28);
		this.CRUSHING_BLADES_1_ROT.addBox(-1F, 0F, -1F, 2, 4, 2);
		this.CRUSHING_BLADES_1_ROT.setRotationPoint(-5F, 8F, 0F);
		this.CRUSHING_BLADES_1_ROT.setTextureSize(128, 128);
		this.CRUSHING_BLADES_1_ROT.mirror = true;
		this.setRotation(this.CRUSHING_BLADES_1_ROT, 0F, 0F, 0F);
		this.CRUSHING_BLADES_2_ROT = new ModelRenderer(this, 53, 35);
		this.CRUSHING_BLADES_2_ROT.addBox(-1F, 0F, -1F, 2, 4, 2);
		this.CRUSHING_BLADES_2_ROT.setRotationPoint(-5F, 8F, 0F);
		this.CRUSHING_BLADES_2_ROT.setTextureSize(128, 128);
		this.CRUSHING_BLADES_2_ROT.mirror = true;
		this.setRotation(this.CRUSHING_BLADES_2_ROT, 0F, 0.7853982F, 0F);
		this.POWER_INTAKE = new ModelRenderer(this, 0, 41);
		this.POWER_INTAKE.addBox(0F, 0F, 0F, 11, 9, 3);
		this.POWER_INTAKE.setRotationPoint(-8F, 14F, 5F);
		this.POWER_INTAKE.setTextureSize(128, 128);
		this.POWER_INTAKE.mirror = true;
		this.setRotation(this.POWER_INTAKE, 0F, 0F, 0F);
		this.RESIDUE_OUTLET_CHIMNEY_1 = new ModelRenderer(this, 0, 54);
		this.RESIDUE_OUTLET_CHIMNEY_1.addBox(0F, 0F, 0F, 2, 5, 2);
		this.RESIDUE_OUTLET_CHIMNEY_1.setRotationPoint(-1F, 9F, 5F);
		this.RESIDUE_OUTLET_CHIMNEY_1.setTextureSize(128, 128);
		this.RESIDUE_OUTLET_CHIMNEY_1.mirror = true;
		this.setRotation(this.RESIDUE_OUTLET_CHIMNEY_1, 0F, 0F, 0F);
		this.RESIDUE_OUTLET_CHIMNEY_2 = new ModelRenderer(this, 0, 54);
		this.RESIDUE_OUTLET_CHIMNEY_2.addBox(0F, 0F, 0F, 2, 5, 2);
		this.RESIDUE_OUTLET_CHIMNEY_2.setRotationPoint(-4F, 9F, 5F);
		this.RESIDUE_OUTLET_CHIMNEY_2.setTextureSize(128, 128);
		this.RESIDUE_OUTLET_CHIMNEY_2.mirror = true;
		this.setRotation(this.RESIDUE_OUTLET_CHIMNEY_2, 0F, 0F, 0F);
		this.MATERIAL_OUTLET_1 = new ModelRenderer(this, 82, 0);
		this.MATERIAL_OUTLET_1.addBox(0F, 0F, 0F, 12, 3, 3);
		this.MATERIAL_OUTLET_1.setRotationPoint(-4F, 15F, -8F);
		this.MATERIAL_OUTLET_1.setTextureSize(128, 128);
		this.MATERIAL_OUTLET_1.mirror = true;
		this.setRotation(this.MATERIAL_OUTLET_1, 0F, 0F, 0.3490659F);
		this.MAIN_OUTLET = new ModelRenderer(this, 66, 0);
		this.MAIN_OUTLET.addBox(0F, 0F, 0F, 4, 8, 3);
		this.MAIN_OUTLET.setRotationPoint(-8F, 15F, -8F);
		this.MAIN_OUTLET.setTextureSize(128, 128);
		this.MAIN_OUTLET.mirror = true;
		this.setRotation(this.MAIN_OUTLET, 0F, 0F, 0F);
		this.MATERIAL_OUTLET_2 = new ModelRenderer(this, 82, 16);
		this.MATERIAL_OUTLET_2.addBox(0F, 0F, 0F, 3, 3, 9);
		this.MATERIAL_OUTLET_2.setRotationPoint(5F, 19F, -6F);
		this.MATERIAL_OUTLET_2.setTextureSize(128, 128);
		this.MATERIAL_OUTLET_2.mirror = true;
		this.setRotation(this.MATERIAL_OUTLET_2, -0.0872665F, 0F, 0F);
		this.SUPPORT = new ModelRenderer(this, 82, 7);
		this.SUPPORT.addBox(0F, 0F, 0F, 3, 4, 3);
		this.SUPPORT.setRotationPoint(5F, 19F, -8F);
		this.SUPPORT.setTextureSize(128, 128);
		this.SUPPORT.mirror = true;
		this.setRotation(this.SUPPORT, 0F, 0F, 0F);
		this.FINAL = new ModelRenderer(this, 96, 7);
		this.FINAL.addBox(0F, 0F, 0F, 3, 1, 6);
		this.FINAL.setRotationPoint(5F, 22F, 0F);
		this.FINAL.setTextureSize(128, 128);
		this.FINAL.mirror = true;
		this.setRotation(this.FINAL, 0F, 0F, 0F);
		
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, null);
		this.BASE.render(0.0625F);
		this.CRUSHING_CHAMBER.render(0.0625F);
		this.INTAKE_1.render(0.0625F);
		this.INTAKE_PANEL_1.render(0.0625F);
		this.INTAKE_PANEL_2.render(0.0625F);
		this.INTAKE_PANEL_3.render(0.0625F);
		this.INTAKE_PANEL_4.render(0.0625F);
		this.CRUSHING_BLADES_1_ROT.render(0.0625F);
		this.CRUSHING_BLADES_2_ROT.render(0.0625F);
		this.POWER_INTAKE.render(0.0625F);
		this.RESIDUE_OUTLET_CHIMNEY_1.render(0.0625F);
		this.RESIDUE_OUTLET_CHIMNEY_2.render(0.0625F);
		this.MATERIAL_OUTLET_1.render(0.0625F);
		this.MAIN_OUTLET.render(0.0625F);
		this.MATERIAL_OUTLET_2.render(0.0625F);
		this.SUPPORT.render(0.0625F);
		this.FINAL.render(0.0625F);
		
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


package hawksmachinery;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class HMModelFireBlock extends ModelBase
{
	ModelRenderer Base;
	ModelRenderer Plating1;
	ModelRenderer Plating2;
	ModelRenderer Plating3;
	ModelRenderer Plating4;
	ModelRenderer Tube1;
	ModelRenderer Tube2;
	ModelRenderer Tube3;
	ModelRenderer Tube4;
	ModelRenderer TubeHolder;
	
	public HMModelFireBlock()
	{
		textureWidth = 64;
		textureHeight = 128;
		
		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(0F, 0F, 0F, 16, 6, 16);
		Base.setRotationPoint(-8F, 18F, -8F);
		Base.setTextureSize(64, 128);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Plating1 = new ModelRenderer(this, 0, 33);
		Plating1.addBox(0F, 0F, 0F, 1, 10, 14);
		Plating1.setRotationPoint(6F, 8F, -7F);
		Plating1.setTextureSize(64, 128);
		Plating1.mirror = true;
		setRotation(Plating1, 0F, 0F, 0F);
		Plating2 = new ModelRenderer(this, 0, 22);
		Plating2.addBox(0F, 0F, 0F, 14, 10, 1);
		Plating2.setRotationPoint(-7F, 8F, -7F);
		Plating2.setTextureSize(64, 128);
		Plating2.mirror = true;
		setRotation(Plating2, 0F, 0F, 0F);
		Plating3 = new ModelRenderer(this, 0, 33);
		Plating3.addBox(0F, 0F, 0F, 1, 10, 14);
		Plating3.setRotationPoint(-7F, 8F, -7F);
		Plating3.setTextureSize(64, 128);
		Plating3.mirror = true;
		setRotation(Plating3, 0F, 0F, 0F);
		Plating4 = new ModelRenderer(this, 0, 22);
		Plating4.addBox(0F, 0F, 0F, 14, 10, 1);
		Plating4.setRotationPoint(-7F, 8F, 6F);
		Plating4.setTextureSize(64, 128);
		Plating4.mirror = true;
		setRotation(Plating4, 0F, 0F, 0F);
		Tube1 = new ModelRenderer(this, 34, 22);
		Tube1.addBox(0F, 0F, 0F, 2, 9, 2);
		Tube1.setRotationPoint(-4F, 9F, -4F);
		Tube1.setTextureSize(64, 128);
		Tube1.mirror = true;
		setRotation(Tube1, 0F, 0F, 0F);
		Tube2 = new ModelRenderer(this, 34, 22);
		Tube2.addBox(0F, 0F, 0F, 2, 9, 2);
		Tube2.setRotationPoint(2F, 9F, -4F);
		Tube2.setTextureSize(64, 128);
		Tube2.mirror = true;
		setRotation(Tube2, 0F, 0F, 0F);
		Tube3 = new ModelRenderer(this, 34, 22);
		Tube3.addBox(0F, 0F, 0F, 2, 9, 2);
		Tube3.setRotationPoint(-4F, 9F, 2F);
		Tube3.setTextureSize(64, 128);
		Tube3.mirror = true;
		setRotation(Tube3, 0F, 0F, 0F);
		Tube4 = new ModelRenderer(this, 34, 22);
		Tube4.addBox(0F, 0F, 0F, 2, 9, 2);
		Tube4.setRotationPoint(2F, 9F, 2F);
		Tube4.setTextureSize(64, 128);
		Tube4.mirror = true;
		setRotation(Tube4, 0F, 0F, 0F);
		TubeHolder = new ModelRenderer(this, 0, 57);
		TubeHolder.addBox(0F, 0F, 0F, 10, 1, 10);
		TubeHolder.setRotationPoint(-5F, 17F, -5F);
		TubeHolder.setTextureSize(64, 128);
		TubeHolder.mirror = true;
		setRotation(TubeHolder, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Base.render(0.0625F);
		Plating1.render(0.0625F);
		Plating2.render(0.0625F);
		Plating3.render(0.0625F);
		Plating4.render(0.0625F);
		Tube1.render(0.0625F);
		Tube2.render(0.0625F);
		Tube3.render(0.0625F);
		Tube4.render(0.0625F);
		TubeHolder.render(0.0625F);
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


package hawksmachinery;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class HawkModelSmeltingLaser extends ModelBase
{
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	
	public HawkModelSmeltingLaser()
	{
		textureWidth = 64;
		textureHeight = 32;
		
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 14, 0, 14);
		Shape1.setRotationPoint(-7F, 8F, -7F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 14);
		Shape2.addBox(0F, 0F, 0F, 2, 10, 2);
		Shape2.setRotationPoint(-1F, 8F, -1F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 56, 3);
		Shape3.addBox(0F, 0F, 0F, 2, 6, 1);
		Shape3.setRotationPoint(-1F, 8F, 1F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 56, 3);
		Shape4.addBox(0F, 0F, 0F, 2, 6, 1);
		Shape4.setRotationPoint(-1F, 8F, -2F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 56, 3);
		Shape5.addBox(0F, 0F, 0F, 1, 6, 2);
		Shape5.setRotationPoint(-2F, 8F, -1F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 56, 3);
		Shape6.addBox(0F, 0F, 0F, 1, 6, 2);
		Shape6.setRotationPoint(1F, 8F, -1F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 56, 0);
		Shape7.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape7.setRotationPoint(-1F, 18F, -1F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}
	
}

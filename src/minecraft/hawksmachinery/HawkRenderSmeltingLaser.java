
package hawksmachinery;

import org.lwjgl.opengl.GL11;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkRenderSmeltingLaser extends TileEntitySpecialRenderer
{
	public HawkModelSmeltingLaser model;
	
	public HawkRenderSmeltingLaser()
	{
		this.model = new HawkModelSmeltingLaser();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale)
	{
		bindTextureByName(HawksMachinery.TEXTURE_PATH + "/SmeltingLaser.png");
		GL11.glPushMatrix();
		GL11.glTranslated((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glScaled(1.0F, -1F, -1F);
		GL11.glPopMatrix();
		
	}
	
}


package hawksmachinery;

import hawksmachinery.container.HMContainerStarForge;
import hawksmachinery.tileentity.HMTileEntityStarForge;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMGUIStarForge extends GuiContainer
{
	public static HawksMachinery BASEMOD;
	private HMTileEntityStarForge tileEntity;
	
	private int containerWidth;
	private int containerHeight;	
	
	public HMGUIStarForge(InventoryPlayer playerInv, HMTileEntityStarForge tileEntity)
	{
		super(new HMContainerStarForge(playerInv, tileEntity));
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(BASEMOD.GUI_PATH + "/StarForge.png"));
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
	}
	
}


package hawksmachinery;

import org.lwjgl.opengl.GL11;
import hawksmachinery.container.HMContainerSinterer;
import hawksmachinery.tileentity.HMTileEntitySinterer;
import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;

/**
 *
 *
 *
 * @author Elusivehawk
 */
public class HMGUISinterer extends GuiContainer
{
	public static HawksMachinery BASEMOD;
	private HMTileEntitySinterer tileEntity;
	
	private int containerWidth;
	private int containerHeight;
	
	public HMGUISinterer(InventoryPlayer playerInv, HMTileEntitySinterer tileEntity)
	{
		super(new HMContainerSinterer(playerInv, tileEntity));
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(BASEMOD.GUI_PATH + "/Sinterer.png"));
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
		this.drawTexturedModalRect(this.containerWidth - 32, this.containerHeight - 16, 0, 232, 240, 16);
		
		if (this.tileEntity.machineHP > 0)
		{
			int lightYPos = 0;
			
			if (this.tileEntity.machineHP <= 5)
			{
				lightYPos = 0;
			}
			else if (this.tileEntity.machineHP <= 10)
			{
				lightYPos = 10;
			}
			else if (this.tileEntity.machineHP <= 15)
			{
				lightYPos = 20;
			}
			else if (this.tileEntity.machineHP <= 20)
			{
				lightYPos = 30;
			}
			
			for (int counter = 0; counter < this.tileEntity.machineHP; ++counter)
			{
				this.drawTexturedModalRect((this.containerWidth - 12) + (counter * 10), this.containerHeight - 12, lightYPos, 248, 10, 8);
				
			}
			
		}
		
	}
	
}

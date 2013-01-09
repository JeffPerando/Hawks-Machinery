
package hawksmachinery.client.gui;

import hawksmachinery.common.HawksMachinery;
import hawksmachinery.common.container.HMContainerCrusher;
import hawksmachinery.common.tileentity.HMTileEntityCrusher;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMGUICrusher extends GuiContainer
{
	private HMTileEntityCrusher tileEntity;
	
	private int containerWidth;
	private int containerHeight;	
	
	public HMGUICrusher(InventoryPlayer par1InventoryPlayer, HMTileEntityCrusher tileEntity)
	{
		super(new HMContainerCrusher(par1InventoryPlayer, tileEntity));
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString(StatCollector.translateToLocal("tile.HMCrusher.name"), 65, 6, 4210752);
		
		this.fontRenderer.drawString("Input:", 10, 28, 4210752);
		this.fontRenderer.drawString("Battery:", 10, 53, 4210752);
		
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
		
	}
	
	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(HawksMachinery.GUI_PATH + "/Crusher.png"));
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
		if (this.tileEntity.workTicks > 0)
		{
			int scale = this.tileEntity.calculateCrushingDuration(24);
			this.drawTexturedModalRect(this.containerWidth + 77, this.containerHeight + 24, 176, 0, 23 - scale, 20);
			
		}
		
	}
	
}

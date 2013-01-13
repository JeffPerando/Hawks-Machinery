
package hawksmachinery.machine.client.gui;

import hawksmachinery.core.common.HMCore;
import hawksmachinery.machine.common.container.HMContainerWasher;
import hawksmachinery.machine.common.tileentity.HMTileEntityWasher;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMGUIWasher extends GuiContainer
{
	private HMTileEntityWasher tileEntity;
	
	private int containerWidth;
	private int containerHeight;
	
	public HMGUIWasher(InventoryPlayer playerInv, HMTileEntityWasher tileEntity)
	{
		super(new HMContainerWasher(playerInv, tileEntity));
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString(StatCollector.translateToLocal("tile.HMWasher.name"), 65, 6, 4210752);
		
	}
	
	/**
	 * Draw the background layer for the GuiContainer (everything behind the
	 * items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(HMCore.GUI_PATH + "/Washer.png"));
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
		if (this.tileEntity.workTicks > 0)
		{
			int scale = this.tileEntity.getWashingStatus(this.tileEntity.TICKS_REQUIRED);
			this.drawTexturedModalRect(containerWidth + 52, containerHeight + 30, 176, 0, 52 - scale, 20);
		}
		/*
		if (this.tileEntity.waterTank.getLiquid() != null)
		{
			if (this.tileEntity.waterTank.getLiquid().amount > 0)
			{
				double percent = (double)(this.tileEntity.waterTank.getLiquid().amount / HMTileEntityWasher.BUCKET_UNIT) / (double)this.tileEntity.WATER_LIMIT;
				int waterLvl = (int)(percent * 71) + 1;
				this.drawTexturedModalRect(containerWidth + 8, containerHeight + 78 - waterLvl, 178, 94 - waterLvl, 17, waterLvl);
				
			}
			
		}
		*/
	}
	
}

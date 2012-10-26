
package hawksmachinery;

import hawksmachinery.tileentity.HMTileEntityCrusher;
import org.lwjgl.opengl.GL11;
import universalelectricity.electricity.ElectricInfo;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;
import net.minecraft.src.Tuple;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMGUICrusher extends GuiContainer
{
	public static HawksMachinery BASEMOD;
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
		this.fontRenderer.drawString("Crusher", 65, 6, 4210752);
		
		this.fontRenderer.drawString("Input:", 10, 28, 4210752);
		this.fontRenderer.drawString("Battery:", 10, 53, 4210752);
		
		this.fontRenderer.drawString("Status: "+this.tileEntity.getCrusherStatus(), 75, 48, 4210752);
		this.fontRenderer.drawString(ElectricInfo.getDisplayShort(this.tileEntity.getVoltage(), ElectricInfo.ElectricUnit.VOLTAGE), 75, 60, 4210752);
		this.fontRenderer.drawString(ElectricInfo.getDisplayShort(this.tileEntity.electricityStored, ElectricInfo.ElectricUnit.WATT), 75, 70, 4210752);
		
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
		
	}
	
	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(BASEMOD.GUI_PATH + "/Crusher.png"));
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
		if (this.tileEntity.workTicks > 0)
		{
			int scale = this.tileEntity.calculateCrushingDuration(24);
			this.drawTexturedModalRect(this.containerWidth + 77, this.containerHeight + 24, 176, 0, 23 - scale, 20);
		}
		
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

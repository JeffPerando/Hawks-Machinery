
package hawksmachinery;

import hawksmachinery.tileentity.HawkTileEntityGrinder;
import org.lwjgl.opengl.GL11;
import universalelectricity.UniversalElectricity;
import universalelectricity.electricity.ElectricInfo;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkGUIGrinder extends GuiContainer
{
	public static HawksMachinery BASEMOD;
	private HawkTileEntityGrinder tileEntity;
	
	private int containerWidth;
	private int containerHeight;	
	
	public HawkGUIGrinder(InventoryPlayer par1InventoryPlayer, HawkTileEntityGrinder tileEntity)
	{
		super(new HawkContainerGrinder(par1InventoryPlayer, tileEntity));
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer()
	{
		this.fontRenderer.drawString("Crusher", 65, 6, 4210752);
		
		this.fontRenderer.drawString("Crushing:", 10, 28, 4210752);
		this.fontRenderer.drawString("Battery:", 10, 53, 4210752);
		
		this.fontRenderer.drawString("Status: "+this.tileEntity.getGrinderStatus(), 75, 48, 4210752);
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
		int var4 = this.mc.renderEngine.getTexture(BASEMOD.GUI_PATH + "/Grinder.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
		if(this.tileEntity.workTicks > 0)
		{
			int scale = this.tileEntity.getGrindingStatus(24);
			this.drawTexturedModalRect(containerWidth + 77, containerHeight + 24, 176, 0, 23 - scale, 20);
		}
	}
	
}

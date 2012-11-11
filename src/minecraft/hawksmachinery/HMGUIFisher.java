
package hawksmachinery;

import org.lwjgl.opengl.GL11;
import universalelectricity.electricity.ElectricInfo;
import hawksmachinery.tileentity.HMTileEntityFisher;
import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMGUIFisher extends GuiContainer
{
	public static HawksMachinery BASEMOD;
	private HMTileEntityFisher tileEntity;
	
	private int containerWidth;
	private int containerHeight;	
	
	public HMGUIFisher(InventoryPlayer playerInv, HMTileEntityFisher tileEntity)
	{
		super(null);//TODO Make HMContainerFisher.
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString("Fisher", 65, 6, 4210752);
		this.fontRenderer.drawString(ElectricInfo.getDisplayShort(this.tileEntity.electricityStored, ElectricInfo.ElectricUnit.WATT), 75, 70, 4210752);
		
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(BASEMOD.GUI_PATH + "/Fisher.png"));
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
	}
	
}


package hawksmachinery;

import hawksmachinery.container.HMContainerFisher;
import hawksmachinery.tileentity.HMTileEntityFisher;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;
import org.lwjgl.opengl.GL11;
import universalelectricity.electricity.ElectricInfo;

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
		super(new HMContainerFisher(playerInv, tileEntity));
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString("Fisher", 72, 4, 4210752);
		this.fontRenderer.drawString(ElectricInfo.getDisplayShort(this.tileEntity.electricityStored, ElectricInfo.ElectricUnit.WATT), 80, 62, 4210752);
		
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 110, this.ySize - 94, 4210752);
		
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

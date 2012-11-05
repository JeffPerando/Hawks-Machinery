
package hawksmachinery;

import org.lwjgl.opengl.GL11;
import hawksmachinery.tileentity.HMTileEntityTeleporterSender;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMGUIEndiumTeleSender extends GuiContainer
{
	public static HawksMachinery BASEMOD;
	public HMTileEntityTeleporterSender tileEntity;
	
	private int containerWidth;
	private int containerHeight;
	
	public HMGUIEndiumTeleSender(InventoryPlayer player, HMTileEntityTeleporterSender tileEntity)
	{
		super(new HMContainerTeleporter(player));
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		
		Integer slotNumber = 1;
		
		for (int y = 0; y < 4; ++y)
		{
			for (int x = 0; x < 4; ++x)
			{
				this.controlList.add(new HMGuiButtonEndiumTele(slotNumber, this.containerWidth + (9 + (16 * x)), this.containerHeight + (9 + (16 * y)), 16, 16, slotNumber.toString()));
				++slotNumber;
			}
			
		}
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(BASEMOD.GUI_PATH + "/EndiumTeleporter.png"));
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
	}
	
	
}

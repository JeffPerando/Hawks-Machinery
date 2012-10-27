
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
		this.controlList.add(new GuiButton(0, this.containerHeight - 9, this.containerWidth - 9, 16, 16, null));
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(BASEMOD.GUI_PATH + "/EndiumTeleporter.png"));
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
	}
	
	
}

/**
 * 
 */
package hawksmachinery;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;
import net.minecraft.src.basiccomponents.BasicComponents;

import org.lwjgl.opengl.GL11;

/**
 * @author Elusivehawk
 *
 */
public class HawkGUIGrinder extends GuiContainer
{
	private HawkTileEntityGrinder tileEntity;
	
	private int containerWidth;
	private int containerHeight;	
	
	public HawkGUIGrinder(InventoryPlayer par1InventoryPlayer, HawkTileEntityGrinder tileEntity)
    {
	       super(new HawkContainerGrinder(par1InventoryPlayer, tileEntity));
	       this.tileEntity = tileEntity;
    }

	protected void drawGuiContainerForegroundLayer()
	{
		this.fontRenderer.drawString("Grinder", 65, 6, 4210752);
  
       this.fontRenderer.drawString("Grinding:", 10, 28, 4210752);
       this.fontRenderer.drawString("Battery:", 10, 53, 4210752);
       
       String displayText = "";
       
       if(this.tileEntity.isDisabled())
       {
       		displayText = "Disabled!";
       }
       else if(this.tileEntity.workTicks > 0)
       {
    	   displayText = "Ready";
       }
       else
       {
    	   displayText = "Idle";
       }
       
       this.fontRenderer.drawString("Status: "+displayText, 90, 48, 4210752);
       this.fontRenderer.drawString("Voltage: "+(int)this.tileEntity.getVoltage(), 89, 60, 4210752);

       this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	
	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
   @Override
   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
   {
       int var4 = this.mc.renderEngine.getTexture(HawkManager.guiPath + "/Grinder.png");
       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
       this.mc.renderEngine.bindTexture(var4);
       containerWidth = (this.width - this.xSize) / 2;
       containerHeight = (this.height - this.ySize) / 2;
       this.drawTexturedModalRect(containerWidth, containerHeight, 0, 0, this.xSize, this.ySize);

       if(this.tileEntity.workTicks > 0)
       {
	       int scale = (int)(((double)this.tileEntity.workTicks/this.tileEntity.ticksNeededtoProcess)*23);
	       this.drawTexturedModalRect(containerWidth + 77, containerHeight + 24, 176, 0, 23 - scale, 20);
       }
   }

}

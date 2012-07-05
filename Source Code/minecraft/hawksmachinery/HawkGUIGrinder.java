/**
 * 
 */
package hawksmachinery;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.*;
import net.minecraft.src.forge.*;
import net.minecraft.src.universalelectricity.*;
import net.minecraft.src.universalelectricity.components.ContainerElectricFurnace;
import net.minecraft.src.universalelectricity.components.TileEntityElectricFurnace;

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
	       this.fontRenderer.drawString("Grinder", 60, 6, 4210752);
	       
	       String displayText = "Grinder";
	       
	       if(this.tileEntity.isDisabled())
	       {
	       		displayText = "?!!";
	       }
	       else
	       {
	    	   displayText = Math.round((double)this.tileEntity.electricityStored/(double)this.tileEntity.getElectricityCapacity()*100)+"%";
	       }
	       this.fontRenderer.drawString("Electricity: "+displayText, 80, 53, 4210752);
	       this.fontRenderer.drawString("Grinding:", 10, 28, 4210752);
	       this.fontRenderer.drawString("Battery:", 10, 53, 4210752);
	       this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	   }
	
	
	@Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        int var4 = this.mc.renderEngine.getTexture("/universalcomponents/BatteryBox.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        containerWidth = (this.width - this.xSize) / 2;
        containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(containerWidth, containerHeight, 0, 0, this.xSize, this.ySize);
        int scale = (int)(((double)this.tileEntity.electricityStored/this.tileEntity.getElectricityCapacity())*72);
        this.drawTexturedModalRect(containerWidth + 87, containerHeight + 51, 176, 0, scale, 20);
    }

}

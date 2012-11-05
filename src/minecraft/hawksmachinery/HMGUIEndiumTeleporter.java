
package hawksmachinery;

import org.lwjgl.opengl.GL11;
import hawksmachinery.tileentity.HMTileEntityTeleporter;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMGUIEndiumTeleporter extends GuiContainer
{
	public static HawksMachinery BASEMOD;
	public HMTileEntityTeleporter tileEntity;
	
	private int containerWidth;
	private int containerHeight;
	
	public HMGUIEndiumTeleporter(InventoryPlayer player, HMTileEntityTeleporter tileEntity)
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
		
		Integer slotNumber = 1; //TODO Remove text on buttons.
		
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
		
		for (int counter = 0; counter < 3; ++counter)
		{
			switch (this.tileEntity.coordArray[counter])
			{
				case 1: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 176, 0, 16, 16); break;
				case 2: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 192, 0, 16, 16); break;
				case 3: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 208, 0, 16, 16); break;
				case 4: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 224, 0, 16, 16); break;
				case 5: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 176, 16, 16, 16); break;
				case 6: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 192, 16, 16, 16); break;
				case 7: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 208, 16, 16, 16); break;
				case 8: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 224, 16, 16, 16); break;
				case 9: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 176, 32, 16, 16); break;
				case 10: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 192, 32, 16, 16); break;
				case 11: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 208, 32, 16, 16); break;
				case 12: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 224, 32, 16, 16); break;
				case 13: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 176, 48, 16, 16); break;
				case 14: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 192, 48, 16, 16); break;
				case 15: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 208, 48, 16, 16); break;
				case 16: this.drawTexturedModalRect(this.containerWidth + (95 + (16 * counter)), this.containerHeight + 14, 224, 48, 16, 16); break;
				
			}
			
		}
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 0){this.tileEntity.wipeCoords();} else {this.tileEntity.updateCoords(button.id);};
		
	}
	
}

package hawksmachinery.client;

import hawksmachinery.common.HawksMachinery;
import hawksmachinery.common.container.HMContainerWasher;
import hawksmachinery.common.tileentity.HMTileEntityWasher;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;

import universalelectricity.core.electricity.ElectricInfo;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMGUIWasher extends GuiContainer
{
    public static HawksMachinery BASEMOD;
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

        this.fontRenderer.drawString(ElectricInfo.getDisplayShort(this.tileEntity.getVoltage(), ElectricInfo.ElectricUnit.VOLTAGE), 116, 60, 4210752);
        this.fontRenderer.drawString(ElectricInfo.getDisplayShort(this.tileEntity.electricityStored, ElectricInfo.ElectricUnit.WATT), 116, 70, 4210752);
        this.fontRenderer.drawString(this.tileEntity.waterUnits + "/" + this.tileEntity.WATER_LIMIT + " Water", 116, 50, 4210752);

    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the
     * items)
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(BASEMOD.GUI_PATH + "/Washer.png"));
        this.containerWidth = (this.width - this.xSize) / 2;
        this.containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
        // percent washed progress bar
        if (this.tileEntity.workTicks > 0)
        {
            int scale = this.tileEntity.getWashingStatus(this.tileEntity.TICKS_REQUIRED);
            this.drawTexturedModalRect(containerWidth + 52, containerHeight + 28, 176, 0, 52 - scale, 20);
        }
        // percent water filled bar has to be double or else results in zero
        double percent = (double)this.tileEntity.waterUnits / (double)this.tileEntity.WATER_LIMIT;
        int waterLvl = (int)( percent * 71)+1;
        // 17w 71h for water bar texture
        this.drawTexturedModalRect(containerWidth + 8, containerHeight + 77 - waterLvl, 178, 94 - waterLvl, 17, waterLvl);
        
        /*
         * this.drawTexturedModalRect(this.containerWidth - 32,
         * this.containerHeight - 16, 0, 232, 240, 16);
         * 
         * if (this.tileEntity.machineHP > 0) { int lightYPos = 0;
         * 
         * if (this.tileEntity.machineHP <= 5) { lightYPos = 0; } else if
         * (this.tileEntity.machineHP <= 10) { lightYPos = 10; } else if
         * (this.tileEntity.machineHP <= 15) { lightYPos = 20; } else if
         * (this.tileEntity.machineHP <= 20) { lightYPos = 30; }
         * 
         * for (int counter = 0; counter < this.tileEntity.machineHP; ++counter)
         * { this.drawTexturedModalRect((this.containerWidth - 12) + (counter *
         * 10), this.containerHeight - 12, lightYPos, 248, 10, 8);
         * 
         * }
         * 
         * }
         */
    }
}

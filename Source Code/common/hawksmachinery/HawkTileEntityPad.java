
package hawksmachinery;

import hawksmachinery.padAPI.HawkPadAPICore;
import universalelectricity.electricity.TileEntityElectricUnit;
import universalelectricity.extend.IElectricUnit;
import universalelectricity.extend.IRedstoneReceptor;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * The tile entity for the Pad!
 * 
 * @author Elusivehawk
 */
public class HawkTileEntityPad extends TileEntityElectricUnit implements IElectricUnit, IRedstoneReceptor
{
	private static HawkPadAPICore apiCore = new HawkPadAPICore();
	
	public float electricityStored;
	
	public boolean isBeingRedstoned;
	
	public ItemStack padItem;
	
	@Override
	public void onUpdate(float amps, float voltage, ForgeDirection side)
	{
		super.onUpdate(amps, voltage, side);
		
		apiCore.onPadUpdate(this.padItem, this.electricityStored, this.isBeingRedstoned, this.xCoord, this.yCoord, this.zCoord, this.worldObj);
		
		if (this.electricityStored + amps <= apiCore.getPadElectricityLimit(this.padItem, this.electricityStored, this.isBeingRedstoned))
		{
			this.electricityStored = this.electricityStored + amps;
		}
		
		if (this.electricityStored - apiCore.getRequiredElectricityForPad(this.padItem, this.electricityStored, this.isBeingRedstoned) <= 0)
		{
			this.electricityStored = this.electricityStored - apiCore.getRequiredElectricityForPad(this.padItem, this.electricityStored, this.isBeingRedstoned);
		}
		
	}
	
	@Override
	public void onDisable(int duration)
	{
		//Note: Leave blank, Pads can't be disabled.
	}
	
	@Override
	public boolean isDisabled()
	{
		return false; //Leave this as-is, for now.
	}
	
	@Override
	public void onPowerOn()
	{
		this.isBeingRedstoned = true;
	}
	
	@Override
	public void onPowerOff()
	{
		this.isBeingRedstoned = false;
	}
	
	@Override
	public float electricityRequest()
	{
		return apiCore.getRequiredElectricityForPad(this.padItem, this.electricityStored, this.isBeingRedstoned);
	}
	
	@Override
	public boolean canConnect(ForgeDirection side)
	{
		return false;
		//TODO: Fill this out.
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return false;
		//TODO: Fill this out.
	}
	
	@Override
	public float getVoltage()
	{
		return 120;
	}
	
	@Override
	public int getTickInterval()
	{
		return 1;
	}
	
}


package hawksmachinery;

import universalelectricity.extend.IElectricUnit;
import universalelectricity.extend.IRedstoneReceptor;
import net.minecraft.src.Block;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * The tile entity for the Pad!
 * 
 * @author Elusivehawk
 */
public class HawkTileEntityPad extends TileEntity implements IElectricUnit, IRedstoneReceptor
{
	
	@Override
	public void onUpdate(float amps, float voltage, ForgeDirection side)
	{
		
	}
	
	@Override
	public void onDisable(int duration)
	{
		//Note: Leave blank, Pads can't be disabled.
	}
	
	@Override
	public boolean isDisabled()
	{
		return false;
	}
	
	@Override
	public void onPowerOn()
	{
		
	}
	
	@Override
	public void onPowerOff()
	{
		
	}
	
	@Override
	public float electricityRequest()
	{
		return 0;
	}
	
	@Override
	public boolean canConnect(ForgeDirection side)
	{
		return false;
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return false;
	}
	
	@Override
	public float getVoltage()
	{
		return 120;
	}
	
	@Override
	public int getTickInterval()
	{
		return 0;
	}
	
}

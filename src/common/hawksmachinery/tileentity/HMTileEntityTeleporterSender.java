
package hawksmachinery.tileentity;

import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityTeleporterSender extends HMTileEntityMachine
{
	public HMTileEntityTeleporterSender()
	{
		ELECTRICITY_LIMIT = 100000;
		ELECTRICITY_REQUIRED = 200;
		voltage = 120;
		
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side.ordinal() == 0;
	}
	
}

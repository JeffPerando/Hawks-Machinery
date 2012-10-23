
package hawksmachinery.tileentity;

import hawksmachinery.interfaces.HMEndiumTeleporterCoords;
import hawksmachinery.interfaces.HMTeleportationHelper;
import net.minecraft.src.Entity;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityTeleporterSender extends HMTileEntityMachine
{
	public HMEndiumTeleporterCoords coords;
	
	public HMTileEntityTeleporterSender()
	{
		ELECTRICITY_LIMIT = 100000;
		ELECTRICITY_REQUIRED = 200;
		voltage = 120;
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
	}
	
	public boolean isReadyToTeleport()
	{
		return this.electricityStored == this.ELECTRICITY_REQUIRED && this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) && this.coords != null;
	}
	
	public void teleportEntity(Entity entity)
	{
		HMTeleportationHelper.instance().teleportEntity(entity, this.coords);
		this.electricityStored = 0;
		
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side.ordinal() == 0;
	}
	
}

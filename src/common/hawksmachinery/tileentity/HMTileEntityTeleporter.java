
package hawksmachinery.tileentity;

import hawksmachinery.api.HMEndiumTeleporterCoords;
import hawksmachinery.api.HMTeleportationHelper;
import net.minecraft.src.Entity;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityTeleporter extends HMTileEntityMachine
{
	public HMEndiumTeleporterCoords coords;
	
	public int[] coordArray = new int[3];
	
	public HMTileEntityTeleporter()
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
		return this.electricityStored == this.ELECTRICITY_REQUIRED && this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) && this.coords != null && this.blockMetadata == 0;
	}
	
	public void teleportEntity(Entity entity)
	{
		HMTeleportationHelper.instance().teleportEntity(entity, this.coords);
		this.electricityStored = 0;
		
	}
	
	public void updateCoords(int newCoord)
	{
		this.coordArray[0] = this.coordArray[1];
		this.coordArray[1] = this.coordArray[2];
		this.coordArray[2] = newCoord;
		
		if (this.coordArray[0] != 0 && this.coordArray[1] != 0 && this.coordArray[2] != 0)
		{
			this.coords = new HMEndiumTeleporterCoords(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getWorldInfo().getDimension(), this.coordArray[0], this.coordArray[1], this.coordArray[2]);
			
		}
		
	}
	
	public void wipeCoords()
	{
		this.coordArray[0] = 0;
		this.coordArray[1] = 0;
		this.coordArray[2] = 0;
		
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side.ordinal() == 0;
	}
	
}

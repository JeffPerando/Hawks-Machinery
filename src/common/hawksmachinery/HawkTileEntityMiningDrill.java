
package hawksmachinery;

import net.minecraftforge.common.ForgeDirection;
import universalelectricity.electricity.ElectricityManager;
import universalelectricity.electricity.TileEntityElectricUnit;
import universalelectricity.extend.ITier;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkTileEntityMiningDrill extends TileEntityElectricUnit implements ITier
{
	
	public HawkTileEntityMiningDrill()
	{
		ElectricityManager.registerElectricUnit(this);
	}
	
	@Override
	public void onUpdate(float watts, float voltage, ForgeDirection side)
	{
		super.onUpdate(watts, voltage, side);
	}
	
	@Override
	public float electricityRequest()
	{
		return 0;
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side != ForgeDirection.DOWN;
	}
	
	@Override
	public int getTier()
	{
		return this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) + 1;
	}
	
	@Override
	public void setTier(int tier){}
	
}

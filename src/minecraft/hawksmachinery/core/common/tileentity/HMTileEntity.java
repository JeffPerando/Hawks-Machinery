
package hawksmachinery.core.common.tileentity;

import hawksmachinery.core.common.api.HMVector;
import universalelectricity.prefab.tile.TileEntityAdvanced;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntity extends TileEntityAdvanced
{
	protected HMVector selfVec;
	
	@Override
	public void initiate()
	{
		this.selfVec = new HMVector(this);
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
	}
	
}
